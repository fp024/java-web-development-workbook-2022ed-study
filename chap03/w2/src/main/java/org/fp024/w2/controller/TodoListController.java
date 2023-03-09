package org.fp024.w2.controller;

import static org.fp024.common.Constants.TODO_VIEW_ROOT;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.fp024.w2.dto.TodoDTO;
import org.fp024.w2.service.TodoService;

@Slf4j
@WebServlet(name = "todoListController", value = "/todo/list")
public class TodoListController extends HttpServlet {

  private final TodoService todoService = TodoService.INSTANCE;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException {

    LOGGER.info("todo list....................");

    ServletContext servletContext = request.getServletContext();
    LOGGER.info("appName: {}", servletContext.getAttribute("appName"));

    try {
      List<TodoDTO> dtoList = todoService.listAll();
      request.setAttribute("dtoList", dtoList);
      request.getRequestDispatcher(TODO_VIEW_ROOT.concat("/list.jsp")) //
          .forward(request, response);
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      throw new ServletException("list error");
    }
  }
}
