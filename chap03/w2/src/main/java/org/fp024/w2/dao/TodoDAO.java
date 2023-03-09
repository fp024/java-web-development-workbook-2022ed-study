package org.fp024.w2.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.fp024.w2.domain.TodoVO;

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


  public List<TodoVO> selectAll() throws Exception {
    String sql = "SELECT tno, title, dueDate, finished FROM tbl_todo";

    @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
    @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

    @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

    List<TodoVO> list = new ArrayList<>();

    while (resultSet.next()) {
      TodoVO vo = TodoVO.builder() //
          .tno(resultSet.getLong("tno")) //
          .title(resultSet.getString("title")).dueDate(resultSet.getDate("dueDate").toLocalDate())
          .finished(resultSet.getBoolean("finished")).build();
      list.add(vo);
    }
    return list;
  }


  public TodoVO selectOne(Long tno) throws Exception {
    String sql = "SELECT tno, title, dueDate, finished FROM tbl_todo WHERE tno = ?";

    @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
    @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

    preparedStatement.setLong(1, tno);

    @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

    resultSet.next();
    TodoVO vo = TodoVO.builder() //
        .tno(resultSet.getLong(1)).title(resultSet.getString(2)) //
        .dueDate(resultSet.getDate(3).toLocalDate()).finished(resultSet.getBoolean(4)).build();
    return vo;
  }


  public void deleteOne(Long tno) throws Exception {
    String sql = """
        DELETE
          FROM tbl_todo
         WHERE tno = ?
        """;
    @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
    @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

    preparedStatement.setLong(1, tno);

    preparedStatement.executeUpdate();
  }


  public void updateOne(TodoVO vo) throws Exception {
    String sql = """
        UPDATE tbl_todo
           SET title = ?,
               dueDate = ?,
               finished = ?
         WHERE tno = ?
        """;

    @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
    @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

    preparedStatement.setString(1, vo.getTitle());
    preparedStatement.setDate(2, Date.valueOf(vo.getDueDate()));
    preparedStatement.setBoolean(3, vo.isFinished());
    preparedStatement.setLong(4, vo.getTno());

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
