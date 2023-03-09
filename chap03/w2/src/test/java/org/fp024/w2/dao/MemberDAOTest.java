package org.fp024.w2.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.fp024.common.DBUtils.resetDB;

import org.fp024.w2.domain.MemberVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MemberDAOTest {

  private MemberDAO dao;

  @BeforeEach
  void beforeEach() {
    resetDB();
    dao = new MemberDAO();
  }

  @Test
  void testGetWithPassword() throws Exception {
    String mid = "user00";
    String mpw = "1111";

    MemberVO memberVO = dao.getWithPassword(mid, mpw);

    assertThat(memberVO) //
        .isNotNull() //
        .hasFieldOrPropertyWithValue("mid", "user00")
        .hasFieldOrPropertyWithValue("mpw", "1111")
        .hasFieldOrPropertyWithValue("mname", "사용자0");
  }
}