package org.fp024.w1.todo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;
import org.fp024.w1.todo.dto.TodoDTO;

@Slf4j
public enum TodoService {
  INSTANCE;

  public void register(TodoDTO todoDTO) {
    LOGGER.info("DEBUG........ {}", todoDTO);
  }

  public List<TodoDTO> getList() {
    List<TodoDTO> todoDTOS = IntStream.range(0, 10).mapToObj(i -> {
      TodoDTO dto = new TodoDTO();
      dto.setTno((long) i);
      dto.setTitle("Todo.." + i);
      dto.setDueDate(LocalDate.now());

      return dto;
    }).toList();

    return todoDTOS;
  }
  
  public TodoDTO get(Long tno) {
    TodoDTO dto = new TodoDTO();
    dto.setTno(tno);
    dto.setTitle("Sample Todo");
    dto.setDueDate(LocalDate.now());
    dto.setFinished(true);
    return dto;
  }

}

