package org.fp024.w1.todo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.fp024.w1.todo.dto.TodoDTO;
import org.fp024.w1.todo.service.TodoService;

@Slf4j
@WebServlet(name = "todoReadController", value = "/todo/read")
public class TodoReadController extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    LOGGER.info("/todo/read");

    // /todo/read?tno=123
    Long tno = Long.parseLong(request.getParameter("tno"));

    TodoDTO dto = TodoService.INSTANCE.get(tno);

    request.setAttribute("dto", dto);

    request.getRequestDispatcher("/WEB-INF/todo/read.jsp") //
        .forward(request, response);
  }


}
