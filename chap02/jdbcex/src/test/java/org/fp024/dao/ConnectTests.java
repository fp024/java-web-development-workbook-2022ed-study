package org.fp024.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import java.sql.DriverManager;
import org.junit.jupiter.api.Test;

class ConnectTests {

  @Test
  void test1() {
    int v1 = 10;
    int v2 = 10;
    assertThat(v1).isEqualTo(v2);
  }


  @Test
  void testConnection() throws Exception {

    Class.forName("org.mariadb.jdbc.Driver");

    Connection connection = DriverManager.getConnection(
        "jdbc:mariadb://localvmdb.mariadb_10:13306/webdb", //
        "webuser",
        "webuser");

    assertThat(connection).isNotNull();

    connection.close();
  }
}
