package org.fp024.w1.todo.dto;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TodoDTO {

  private Long tno;
  private String title;
  private LocalDate dueDate;
  private boolean finished;

}
