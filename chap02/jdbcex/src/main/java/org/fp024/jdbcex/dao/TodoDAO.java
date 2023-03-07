package org.fp024.jdbcex.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.fp024.jdbcex.TodoVO;

@Slf4j
public class TodoDAO {

  public void insert(TodoVO vo) throws Exception {
    String sql = """
        INSERT INTO tbl_todo (title, dueDate, finished)
        VALUE (?, ?, ?)
        """;

    @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
    @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

    preparedStatement.setString(1, vo.getTitle());
    preparedStatement.setDate(2, Date.valueOf(vo.getDueDate()));
    preparedStatement.setBoolean(3, vo.isFinished());

    preparedStatement.executeUpdate();
  }


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
