package org.fp024.jdbcex.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import lombok.Getter;

public enum ConnectionUtil {
  INSTANCE;

  @Getter
  private final HikariDataSource dataSource;

  ConnectionUtil() {

    HikariConfig config = new HikariConfig();

    config.setDriverClassName(org.mariadb.jdbc.Driver.class.getCanonicalName());
    config.setJdbcUrl("jdbc:mariadb://localvmdb.mariadb_10:13306/webdb");
    config.setUsername("webuser");
    config.setPassword("webuser");

    config.addDataSourceProperty("cachePrepStmts", "true");
    config.addDataSourceProperty("prepStmtCacheSize", "250");
    config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

    dataSource = new HikariDataSource(config);
  }

  public Connection getConnection() throws Exception {
    return dataSource.getConnection();
  }

}
