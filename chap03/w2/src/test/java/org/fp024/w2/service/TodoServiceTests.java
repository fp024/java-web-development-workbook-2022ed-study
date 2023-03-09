package org.fp024.w2.service;

import static org.fp024.common.DBUtils.resetDB;

import java.time.LocalDate;
import org.fp024.w2.dto.TodoDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TodoServiceTests {

  private TodoService todoService;

  @BeforeEach
  public void ready() {
    resetDB();
    todoService = TodoService.INSTANCE;
  }


  @Test
  public void testRegister() throws Exception {
    TodoDTO todoDTO = TodoDTO.builder() //
        .title("JDBC Test Title") //
        .dueDate(LocalDate.now()).build();

    todoService.register(todoDTO);
  }


}