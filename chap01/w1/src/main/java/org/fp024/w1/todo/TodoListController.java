package org.fp024.w1.todo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.fp024.w1.todo.dto.TodoDTO;
import org.fp024.w1.todo.service.TodoService;

@Slf4j
@WebServlet(name = "todoListController", value = "/todo/list")
public class TodoListController extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    LOGGER.info("/todo/list");

    List<TodoDTO> dtoList = TodoService.INSTANCE.getList();

    request.setAttribute("list", dtoList);

    request.getRequestDispatcher("/WEB-INF/todo/list.jsp") //
        .forward(request, response);
  }
}
