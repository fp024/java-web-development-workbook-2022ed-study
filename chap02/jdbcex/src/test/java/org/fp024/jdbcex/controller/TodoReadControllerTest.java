package org.fp024.jdbcex.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.fp024.common.Constants.TODO_VIEW_ROOT;
import static org.fp024.common.DBUtils.resetDB;

import org.fp024.jdbcex.dto.TodoDTO;
import org.fp024.test.support.MockHttpServletTestSupport;
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