package org.fp024.w2.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.fp024.common.Constants.COOKIE_NAME_REMEMBER_ME;

import jakarta.servlet.http.Cookie;
import org.fp024.test.support.MockHttpServletTestSupport;
import org.fp024.w2.dto.MemberDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpSession;

class LogoutControllerTest extends MockHttpServletTestSupport<LogoutController> {

  @BeforeEach
  void beforeEach() {

  }

  @Test
  void testLogout() throws Exception {
    runGivenWhenThen(
        () -> {
          MockHttpSession session = new MockHttpSession(servletContext);
          session.setAttribute("loginInfo",
              MemberDTO.builder().mid("user00").uuid("UUID-....").build());

          Cookie cookie = new Cookie(COOKIE_NAME_REMEMBER_ME, "UUID-....");
          request.setCookies(cookie);
          request.setSession(session);
        },
        () -> servlet.doPost(request, response),
        () -> {
          assertThat(response.getCookie(COOKIE_NAME_REMEMBER_ME)) //
              .hasFieldOrPropertyWithValue("maxAge", 0);
          assertThat(request.getSession(false)) //
              .isNull();
          assertThat(response.getStatus()) //
              .isEqualTo(HttpStatus.FOUND.value());
          assertThat(response.getRedirectedUrl()) //
              .isEqualTo("/");
        }
    );
  }

  @Override
  protected Class<LogoutController> getServletClass() {
    return LogoutController.class;
  }

  @Override
  protected String getServletPath() {
    return "/logout";
  }
}