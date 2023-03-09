package org.fp024.w2.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.fp024.w2.service.TodoService;

@Slf4j
@WebServlet(name = "todoRemoveController", value = "/todo/remove")
public class TodoRemoveController extends HttpServlet {

  private final TodoService todoService = TodoService.INSTANCE;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException {

    Long tno = Long.parseLong(request.getParameter("tno"));
    LOGGER.info("tno: {}", tno);

    try {
      todoService.remove(tno);
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
    }
    response.sendRedirect("/todo/list");
  }
}
