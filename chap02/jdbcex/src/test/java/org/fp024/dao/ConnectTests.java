package org.fp024.dao;

import static org.assertj.core.api.Assertions.assertThat;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class ConnectTests {

  @Test
  void test1() {
    int v1 = 10;
    int v2 = 10;
    assertThat(v1).isEqualTo(v2);
  }


  @Test
  void testConnection() throws Exception {

    Class.forName(org.mariadb.jdbc.Driver.class.getCanonicalName());

    Connection connection = DriverManager.getConnection(
        "jdbc:mariadb://localvmdb.mariadb_10:13306/webdb", //
        "webuser",
        "webuser");

    assertThat(connection).isNotNull();

    connection.close();
  }


  @Test
  void testHikariCP() throws Exception {
    HikariConfig config = new HikariConfig();

    config.setDriverClassName(org.mariadb.jdbc.Driver.class.getCanonicalName());
    config.setJdbcUrl("jdbc:mariadb://localvmdb.mariadb_10:13306/webdb");
    config.setUsername("webuser");
    config.setPassword("webuser");

    config.addDataSourceProperty("cachePrepStmts", "true");
    config.addDataSourceProperty("prepStmtCacheSize", "250");
    config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

    HikariDataSource ds = new HikariDataSource(config);
    Connection connection = ds.getConnection();

    LOGGER.info("connection: {}", connection);

    assertThat(connection).isNotNull();

    connection.close();
  }

}
