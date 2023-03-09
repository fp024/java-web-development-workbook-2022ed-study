package org.fp024.w2.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.fp024.common.DBUtils.resetDB;

import org.fp024.w2.dto.MemberDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MemberServiceTest {

  private MemberService service;

  @BeforeEach
  void beforeEach() {
    resetDB();
    service = MemberService.INSTANCE;
  }

  @Test
  void testLogon() throws Exception {
    String mid = "user00";
    String mpw = "1111";
    MemberDTO memberDTO = service.login(mid, mpw);

    assertThat(memberDTO).isNotNull() //
        .hasFieldOrPropertyWithValue("mid", mid)
        .hasFieldOrPropertyWithValue("mpw", mpw)
        .hasFieldOrPropertyWithValue("mname", "사용자0");

  }

}