package org.fp024.w2.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.fp024.common.CommonUtils.getCookie;
import static org.fp024.common.Constants.COOKIE_NAME_VIEW_TODOS;
import static org.fp024.common.Constants.TODO_VIEW_ROOT;
import static org.fp024.common.DBUtils.resetDB;

import jakarta.servlet.http.Cookie;
import java.util.Optional;
import org.fp024.test.support.MockHttpServletTestSupport;
import org.fp024.w2.dto.TodoDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class TodoReadControllerTest extends MockHttpServletTestSupport<TodoReadController> {

  @BeforeEach
  void beforeEach() {
    resetDB();
  }

  @Test
  void testDoGet() throws Exception {
    runGivenWhenThen(
        () -> request.setParameter("tno", "2"),
        () -> servlet.doGet(request, response),
        () -> {
          assertThat(response.getStatus()) //
              .isEqualTo(HttpStatus.OK.value());
          assertThat(response.getForwardedUrl()) //
              .isEqualTo(TODO_VIEW_ROOT.concat("/read.jsp"));
          assertThat(request.getAttribute("dto")) //
              .isNotNull();

          Object dtoObj = request.getAttribute("dto");
          assertThat((TodoDTO) dtoObj).hasFieldOrPropertyWithValue("tno", 2L);

          Optional<Cookie> optCookie = getCookie(response.getCookies(), COOKIE_NAME_VIEW_TODOS);

          assertThat(optCookie.isPresent()).isTrue();
          assertThat(optCookie.get()).hasFieldOrPropertyWithValue("value", "2-");
        }
    );
  }

  @Test
  void testDoGet_hasRequestViewTodoCookie() throws Exception {
    runGivenWhenThen(
        () -> {
          request.setParameter("tno", "2");
          request.setCookies(new Cookie(COOKIE_NAME_VIEW_TODOS, "1-"));
        },
        () -> servlet.doGet(request, response),
        () -> {
          assertThat(response.getStatus()) //
              .isEqualTo(HttpStatus.OK.value());
          assertThat(response.getForwardedUrl()) //
              .isEqualTo(TODO_VIEW_ROOT.concat("/read.jsp"));
          assertThat(request.getAttribute("dto")) //
              .isNotNull();

          Object dtoObj = request.getAttribute("dto");
          assertThat((TodoDTO) dtoObj).hasFieldOrPropertyWithValue("tno", 2L);

          Optional<Cookie> optCookie = getCookie(response.getCookies(), COOKIE_NAME_VIEW_TODOS);

          assertThat(optCookie.isPresent()).isTrue();
          assertThat(optCookie.get()).hasFieldOrPropertyWithValue("value", "1-2-");

        }
    );
  }


  @Override
  protected Class<TodoReadController> getServletClass() {
    return TodoReadController.class;
  }

  @Override
  protected String getServletPath() {
    return "/todo/read";
  }
}