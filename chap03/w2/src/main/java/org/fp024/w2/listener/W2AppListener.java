package org.fp024.w2.listener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebListener
public class W2AppListener implements ServletContextListener {

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    LOGGER.info("------------ init -----------------------------");
    LOGGER.info("------------ init -----------------------------");

    ServletContext servletContext = sce.getServletContext();
    servletContext.setAttribute("appName", "W2");
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    LOGGER.info("------------ destroy -----------------------------");
    LOGGER.info("------------ destroy -----------------------------");
    LOGGER.info("------------ destroy -----------------------------");
  }
}
