package org.fp024.w2.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.fp024.common.Constants.TODO_VIEW_ROOT;

import jakarta.servlet.http.HttpSession;
import org.fp024.test.support.MockHttpServletTestSupport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class LoginControllerTest extends MockHttpServletTestSupport<LoginController> {

  @BeforeEach
  void beforeEach() {

  }

  @Test
  void testLoginView() throws Exception {
    runGivenWhenThen(
        () -> {
        },
        () -> servlet.doGet(request, response),
        () -> {
          assertThat(response.getStatus()) //
              .isEqualTo(HttpStatus.OK.value());
          assertThat(response.getForwardedUrl()) //
              .isEqualTo(TODO_VIEW_ROOT.concat("/login.jsp"));
        }
    );
  }


  @Test
  void testLoginPost() throws Exception {
    runGivenWhenThen(
        () -> {
          request.setParameter("mid", "choi");
          request.setParameter("mpw", "1234");
        },
        () -> servlet.doPost(request, response),
        () -> {
          assertThat(response.getStatus()) //
              .isEqualTo(HttpStatus.FOUND.value());

          HttpSession session = request.getSession(false);
          String loginInfo = (String) session.getAttribute("loginInfo");
          assertThat(loginInfo).isEqualTo("choi1234");
          assertThat(response.getRedirectedUrl()) //
              .isEqualTo("/todo/list");
        }
    );
  }


  @Override
  protected Class<LoginController> getServletClass() {
    return LoginController.class;
  }

  @Override
  protected String getServletPath() {
    return "/login";
  }
}