package org.fp024.common;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collections;
import lombok.extern.slf4j.Slf4j;
import org.fp024.jdbcex.dao.ConnectionUtil;

@Slf4j
@WebListener("서버 종료시 데이터소스 Close & DB 드라이버 등록 해제")
public class JDBCDriverCleaner implements ServletContextListener {

  protected void deregisterJdbcDrivers(ServletContext servletContext) {
    Collections.list(DriverManager.getDrivers())
        .forEach(
            driver -> {
              LOGGER.trace(
                  "### {} 드라이버의 클래스 로더: {}",
                  driver.getClass().getCanonicalName(),
                  driver.getClass().getClassLoader().getClass().getCanonicalName());
              LOGGER.trace(
                  "### 현재 서블릿 컨텍스트의 클래스 로더: {}",
                  servletContext.getClassLoader().getClass().getCanonicalName());
              LOGGER.trace(
                  "### 현재 서블릿 컨텍스트의 부모 클래스 로더: {}",
                  servletContext.getClassLoader().getParent().getClass().getCanonicalName());

              // Gretty 환경에서는...
              // driver.getClass().getClassLoader(): java.net.URLClassLoader
              // servletContext.getClassLoader(): TomcatEmbeddedWebappClassLoader 가 됨.

              // Tomcat에서 데이터소스를 생성하지 않고, 웹애플리케이션 코드내에서 생성했으면
              // 아래 조건이 맞아서 잘 되었을지는 모르겠는데... 이 프로젝트는 이렇게 유지해보자.
              if (driver.getClass().getClassLoader() == servletContext.getClassLoader()
                  || driver.getClass().getClassLoader()
                  == servletContext.getClassLoader().getParent()) {
                try {
                  DriverManager.deregisterDriver(driver);
                  LOGGER.info("### {} 드라이버 등록 해제", driver.getClass().getCanonicalName());
                } catch (SQLException ex) {
                  LOGGER.warn("### {} 드라이버 등록 해제 실패", driver.getClass().getCanonicalName(), ex);
                }
              }
            });
  }

  @Override
  public void contextDestroyed(ServletContextEvent event) {
    LOGGER.trace("contextDestroyed() 실행...");
    ConnectionUtil.INSTANCE.getDataSource().close();
    deregisterJdbcDrivers(event.getServletContext());
  }
}