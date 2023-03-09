package org.fp024.w2.controller;

import static org.fp024.common.Constants.DATE_FORMAT_YYYY_MM_DD;
import static org.fp024.common.Constants.TODO_VIEW_ROOT;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import lombok.extern.slf4j.Slf4j;
import org.fp024.w2.dto.TodoDTO;
import org.fp024.w2.service.TodoService;

@Slf4j
@WebServlet(name = "todoRegisterController", value = "/todo/register")
public class TodoRegisterController extends HttpServlet {

  private final TodoService todoService = TodoService.INSTANCE;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    LOGGER.info("/todo/register GET .....");
    request.getRequestDispatcher(TODO_VIEW_ROOT.concat("/register.jsp")) //
        .forward(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    TodoDTO todoDTO = TodoDTO.builder() //
        .title(request.getParameter("title"))
        .dueDate(LocalDate.parse(request.getParameter("dueDate"), DATE_FORMAT_YYYY_MM_DD)).build();

    LOGGER.info("/todo/register POST ...");
    LOGGER.info(todoDTO.toString());

    try {
      todoService.register(todoDTO);
    } catch (Exception e) {
      LOGGER.info(e.getMessage(), e);
    }
    response.sendRedirect("/todo/list");
  }
}
