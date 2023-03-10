package org.fp024.w2.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.fp024.common.CommonUtils.getCookie;
import static org.fp024.common.Constants.COOKIE_NAME_REMEMBER_ME;
import static org.fp024.common.Constants.TODO_VIEW_ROOT;
import static org.fp024.common.DBUtils.resetDB;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpSession;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.fp024.test.support.MockHttpServletTestSupport;
import org.fp024.w2.dto.MemberDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

@Slf4j
class LoginControllerTest extends MockHttpServletTestSupport<LoginController> {

  @BeforeEach
  void beforeEach() {
    resetDB();
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
  void testLoginSuccess() throws Exception {
    runGivenWhenThen(
        () -> {
          request.setParameter("mid", "user00");
          request.setParameter("mpw", "1111");
        },
        () -> servlet.doPost(request, response),
        () -> {
          assertThat(response.getStatus()) //
              .isEqualTo(HttpStatus.FOUND.value());

          HttpSession session = request.getSession(false);
          MemberDTO loginInfo = (MemberDTO) session.getAttribute("loginInfo");
          assertThat(loginInfo).isEqualTo(
              MemberDTO.builder().mid("user00").mpw("1111").mname("사용자0").build());
          assertThat(response.getRedirectedUrl()) //
              .isEqualTo("/todo/list");
        }
    );
  }

  @Test
  void testLoginSuccess_newRememberMe() throws Exception {
    runGivenWhenThen(
        () -> {
          request.setParameter("mid", "user00");
          request.setParameter("mpw", "1111");
          request.setParameter("auto", "on");
        },
        () -> servlet.doPost(request, response),
        () -> {
          assertThat(response.getStatus()) //
              .isEqualTo(HttpStatus.FOUND.value());

          Optional<Cookie> optCookie = getCookie(response.getCookies(), COOKIE_NAME_REMEMBER_ME);
          assertThat(optCookie.isPresent()).isTrue();
          assertThat(optCookie.get()).isNotNull();

          HttpSession session = request.getSession(false);
          MemberDTO loginInfo = (MemberDTO) session.getAttribute("loginInfo");
          assertThat(loginInfo).isEqualTo(
              MemberDTO.builder().mid("user00").mpw("1111").mname("사용자0").build());
          assertThat(response.getRedirectedUrl()) //
              .isEqualTo("/todo/list");
        }
    );
  }

  @Test
  void testLoginFailure() throws Exception {
    runGivenWhenThen(
        () -> {
          request.setParameter("mid", "user00");
          request.setParameter("mpw", "1234");
        },
        () -> servlet.doPost(request, response),
        () -> {
          assertThat(response.getStatus()) //
              .isEqualTo(HttpStatus.FOUND.value());

          HttpSession session = request.getSession(false);
          assertThat(session).isNull(); // 조회 결과가 없을 경우 DAO에서 이미 예외가 발생하서 Session 생성도 없다.
          assertThat(response.getRedirectedUrl()) //
              .isEqualTo("/login?result=error");
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