package org.fp024.w2.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.fp024.common.Constants.TODO_VIEW_ROOT;
import static org.fp024.common.DBUtils.resetDB;

import java.util.Map;
import org.fp024.test.support.MockHttpServletTestSupport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpSession;

public class TodoRegisterControllerTest extends MockHttpServletTestSupport<TodoRegisterController> {

  @BeforeEach
  void beforeEach() {
  }

  @Test
  void testDoGet_HasSessionAndLoginInfo() throws Exception {
    runGivenWhenThen(
        () -> {
          MockHttpSession session = new MockHttpSession(servletContext);
          session.setAttribute("loginInfo", Map.of("id", "kim"));
          request.setSession(session);
        },
        () -> servlet.doGet(request, response),
        () -> {
          assertThat(response.getStatus()) //
              .isEqualTo(HttpStatus.OK.value());
          assertThat(response.getForwardedUrl()) //
              .isEqualTo(TODO_VIEW_ROOT.concat("/register.jsp"));
        }
    );
  }

  @Test
  void testDoGet_WithoutSessionAndLoginInfo() throws Exception {
    runGivenWhenThen(
        () -> {
        },
        () -> servlet.doGet(request, response),
        () -> {
          assertThat(response.getStatus()) //
              .isEqualTo(HttpStatus.FOUND.value());
          assertThat(response.getRedirectedUrl()) //
              .isEqualTo("/login");
        }
    );
  }

  @Test
  void testDoGet_WithoutLoginInfo() throws Exception {
    runGivenWhenThen(
        () -> {
          MockHttpSession session = new MockHttpSession(servletContext);
          request.setSession(session);
        },
        () -> servlet.doGet(request, response),
        () -> {
          assertThat(response.getStatus()) //
              .isEqualTo(HttpStatus.FOUND.value());
          assertThat(response.getRedirectedUrl()) //
              .isEqualTo("/login");
        }
    );
  }


  @Test
  void testDoPost() throws Exception {
    resetDB();
    runGivenWhenThen(
        () -> {
          request.setParameter("title", "?????? ??????");
          request.setParameter("dueDate", "2023-03-06");
        },
        () -> servlet.doPost(request, response),
        () -> {
          assertThat(response.getStatus()) //
              .isEqualTo(HttpStatus.FOUND.value());
          assertThat(response.getRedirectedUrl()) //
              .isEqualTo("/todo/list");
        }
    );
  }

  @Override
  protected Class<TodoRegisterController> getServletClass() {
    return TodoRegisterController.class;
  }

  @Override
  protected String getServletPath() {
    return "/todo/register";
  }
}