package org.fp024.jdbcex.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.fp024.common.DBUtils.resetDB;

import org.fp024.test.support.MockHttpServletTestSupport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class TodoRemoveControllerTest extends MockHttpServletTestSupport<TodoRemoveController> {

  @BeforeEach
  void beforeEach() {
  }

  @Test
  void testDoPost() throws Exception {
    resetDB();
    runGivenWhenThen(
        () -> request.setParameter("tno", "1"),
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
  protected Class<TodoRemoveController> getServletClass() {
    return TodoRemoveController.class;
  }

  @Override
  protected String getServletPath() {
    return "/todo/remove";
  }
}