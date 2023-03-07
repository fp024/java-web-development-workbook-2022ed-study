package org.fp024.jdbcex.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.fp024.jdbcex.dto.TodoDTO;
import org.fp024.jdbcex.service.TodoService;

@Slf4j
@WebServlet(name = "todoListController", value = "/todo/list")
public class TodoListController extends HttpServlet {

  private TodoService todoService = TodoService.INSTANCE;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    LOGGER.info("todo list....................");

    try {
      List<TodoDTO> dtoList = todoService.listAll();
      request.setAttribute("dtoList", dtoList);
      request.getRequestDispatcher("/WEB-INF/todo/list.jsp") //
          .forward(request, response);
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      throw new ServletException("list error");
    }


  }
}
