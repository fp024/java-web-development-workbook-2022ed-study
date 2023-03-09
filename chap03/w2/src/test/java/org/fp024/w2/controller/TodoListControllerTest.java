package org.fp024.w2.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.fp024.common.Constants.TODO_VIEW_ROOT;
import static org.fp024.common.DBUtils.resetDB;

import org.fp024.test.support.MockHttpServletTestSupport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class TodoListControllerTest extends MockHttpServletTestSupport<TodoListController> {

  @BeforeEach
  void beforeEach() {
    resetDB();
  }

  @Test
  void testDoGet() throws Exception {
    runGivenWhenThen(
        () -> {
        },
        () -> servlet.doGet(request, response),
        () -> {
          assertThat(response.getStatus()) //
              .isEqualTo(HttpStatus.OK.value());
          assertThat(response.getForwardedUrl()) //
              .isEqualTo(TODO_VIEW_ROOT.concat("/list.jsp"));
          assertThat(request.getAttribute("dtoList")) //
              .isNotNull();
        }
    );
  }

  @Override
  protected Class<TodoListController> getServletClass() {
    return TodoListController.class;
  }

  @Override
  protected String getServletPath() {
    return "/todo/list";
  }
}