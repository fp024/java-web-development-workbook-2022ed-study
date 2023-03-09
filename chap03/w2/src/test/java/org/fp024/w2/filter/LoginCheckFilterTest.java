package org.fp024.w2.filter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.fp024.common.Constants.COOKIE_NAME_REMEMBER_ME;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.UUID;
import org.fp024.w2.dto.MemberDTO;
import org.fp024.w2.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

class LoginCheckFilterTest {

  private MockHttpServletRequest request;
  private MockHttpServletResponse response;

  private MockFilterChain chain;

  private LoginCheckFilter filter;

  @BeforeEach
  void beforeEach() {
    request = new MockHttpServletRequest();
    response = new MockHttpServletResponse();
    chain = new MockFilterChain();
    filter = new LoginCheckFilter();
  }

  @Test
  void testDoFilter_NoLogin() throws ServletException, IOException {
    // given
    // // 초기 준비 설정 없음
    // when
    filter.doFilter(request, response, chain);
    // then
    assertThat(response.getStatus()) //
        .isEqualTo(HttpStatus.FOUND.value());
    assertThat(response.getRedirectedUrl()) //
        .isEqualTo("/login");
  }

  @Test
  void testDoFilter_SessionLogin() throws Exception {
    // given
    // 서버측에도 자동 로그인 UUID정보를 미리 넣어주자.
    final String testUUID = UUID.randomUUID().toString();
    MemberService.INSTANCE.updateUuid("user00", testUUID);

    Cookie cookie = new Cookie(COOKIE_NAME_REMEMBER_ME, testUUID);
    request.setCookies(cookie);

    // when
    filter.doFilter(request, response, chain);

    // then
    HttpSession session = request.getSession(false);

    assertThat(session).isNotNull();

    MemberDTO memberDTO = (MemberDTO) session.getAttribute("loginInfo");

    assertThat(memberDTO) //
        .hasFieldOrPropertyWithValue("mid", "user00")
        .hasFieldOrPropertyWithValue("uuid", testUUID);

    assertThat(session.getAttribute("loginInfo"));

    assertThat(response.getStatus()) //
        .isNotEqualTo(HttpStatus.FOUND.value());
  }

}