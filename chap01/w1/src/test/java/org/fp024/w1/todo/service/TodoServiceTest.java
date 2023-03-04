package org.fp024.w1.todo.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.fp024.w1.todo.dto.TodoDTO;
import org.junit.jupiter.api.Test;

@Slf4j
class TodoServiceTest {

  @Test
  void getList() {
    List<TodoDTO> list = TodoService.INSTANCE.getList();
    list.forEach(t -> LOGGER.info("{}", t));

    assertThat(list).isNotEmpty();
  }

  @Test
  void testInstance() {
    assertThat(TodoService.INSTANCE) //
        .isSameAs(TodoService.INSTANCE);
  }
}