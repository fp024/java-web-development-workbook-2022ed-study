package org.fp024.w2.filter;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.servlet.ServletException;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;

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
  void testDoFilter_Login() throws ServletException, IOException {
    // given
    MockHttpSession session = new MockHttpSession();
    session.setAttribute("loginInfo", "park1234");
    request.setSession(session);
    // when
    filter.doFilter(request, response, chain);
    // then
    assertThat(response.getStatus()) //
        .isNotEqualTo(HttpStatus.FOUND.value());
  }

}