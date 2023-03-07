package org.fp024.jdbcex.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TodoDAO {

  public String getTime() {
    String now = null;

    try (Connection connection = ConnectionUtil.INSTANCE.getConnection()) {
      PreparedStatement preparedStatement = connection.prepareStatement("SELECT now()");
      ResultSet resultSet = preparedStatement.executeQuery();
      resultSet.next();
      now = resultSet.getString(1);
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
    }

    return now;
  }
  
  public String getTime2() throws Exception {

    @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
    @Cleanup PreparedStatement preparedStatement = connection.prepareStatement("SELECT now()");
    @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

    resultSet.next();
    String now = resultSet.getString(1);

    return now;
  }
}
