package org.fp024.jdbcex.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.fp024.common.Constants.TODO_VIEW_ROOT;
import static org.fp024.common.DBUtils.resetDB;

import org.fp024.jdbcex.dto.TodoDTO;
import org.fp024.test.support.MockHttpServletTestSupport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class TodoModifyControllerTest extends MockHttpServletTestSupport<TodoModifyController> {

  @BeforeEach
  void beforeEach() {
    resetDB();
  }


  @Test
  void testDoGet() throws Exception {
    runGivenWhenThen(
        () -> request.setParameter("tno", "1"),
        () -> servlet.doGet(request, response),
        () -> {
          assertThat(response.getStatus()) //
              .isEqualTo(HttpStatus.OK.value());
          assertThat(response.getForwardedUrl()) //
              .isEqualTo(TODO_VIEW_ROOT.concat("/modify.jsp"));
          Object dto = request.getAttribute("dto");
          assertThat(dto) //
              .isNotNull();
          assertThat((TodoDTO) dto).hasFieldOrPropertyWithValue("tno", 1L);
        }
    );
  }

  @Test
  void testDoPost() throws Exception {
    runGivenWhenThen(
        () -> {
          request.setParameter("tno", "1");
          request.setParameter("title", "제목 수정");
          request.setParameter("dueDate", "2023-03-07");
          request.setParameter("finished", "on");
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
  protected Class<TodoModifyController> getServletClass() {
    return TodoModifyController.class;
  }

  @Override
  protected String getServletPath() {
    return "/todo/modify";
  }
}