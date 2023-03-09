package org.fp024.w2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import lombok.Cleanup;
import org.fp024.w2.domain.MemberVO;

public class MemberDAO {

  public MemberVO getWithPassword(String mid, String mpw) throws Exception {

    String query = """
        SELECT mid
             , mpw
             , mname
             , uuid
          FROM tbl_member
         WHERE mid = ?
           AND mpw = ?
        """;

    MemberVO memberVO;

    @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
    @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(query);

    preparedStatement.setString(1, mid);
    preparedStatement.setString(2, mpw);

    ResultSet resultSet = preparedStatement.executeQuery();
    resultSet.next();

    memberVO = MemberVO.builder()
        .mid(resultSet.getString(1))
        .mpw(resultSet.getString(2))
        .mname(resultSet.getString(3))
        .uuid(resultSet.getString(4))
        .build();

    return memberVO;
  }

  public void updateUuid(String mid, String uuid) throws Exception {
    String sql = """
        UPDATE tbl_member
           SET uuid = ?
         WHERE mid = ?
        """;
    @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
    @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
    preparedStatement.setString(1, uuid);
    preparedStatement.setString(2, mid);
    preparedStatement.executeUpdate();
  }


  public MemberVO selectUUID(String uuid) throws Exception {
    String query = """
        SELECT mid
             , mpw
             , mname
             , uuid
          FROM tbl_member
         WHERE uuid = ?
        """;

    MemberVO memberVO = null;

    @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
    @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(query);

    preparedStatement.setString(1, uuid);

    ResultSet resultSet = preparedStatement.executeQuery();
    if (resultSet.next()) {
      memberVO = MemberVO.builder()
          .mid(resultSet.getString(1))
          .mpw(resultSet.getString(2))
          .mname(resultSet.getString(3))
          .uuid(resultSet.getString(4))
          .build();
    }
    return memberVO;
  }
}
