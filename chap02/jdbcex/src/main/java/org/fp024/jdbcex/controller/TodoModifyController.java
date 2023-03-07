package org.fp024.jdbcex.controller;

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
import org.fp024.jdbcex.dto.TodoDTO;
import org.fp024.jdbcex.service.TodoService;

@Slf4j
@WebServlet(name = "todoModifyController", value = "/todo/modify")
public class TodoModifyController extends HttpServlet {

  private final TodoService todoService = TodoService.INSTANCE;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException {

    try {
      Long tno = Long.parseLong(request.getParameter("tno"));
      TodoDTO todoDTO = todoService.get(tno);
      // 데이터 담기
      request.setAttribute("dto", todoDTO);
      request.getRequestDispatcher(TODO_VIEW_ROOT.concat("/modify.jsp")) //
          .forward(request, response);
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      throw new ServletException("modify get ... error");
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException {

    String finishedStr = request.getParameter("finished");

    TodoDTO todoDTO = TodoDTO.builder() //
        .tno(Long.parseLong(request.getParameter("tno"))) //
        .title(request.getParameter("title"))
        .dueDate(LocalDate.parse(request.getParameter("dueDate"), DATE_FORMAT_YYYY_MM_DD))
        .finished("on".equals(finishedStr)).build();

    LOGGER.info("/todo/modify POST ...");
    LOGGER.info(todoDTO.toString());

    try {
      todoService.modify(todoDTO);
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
    }
    response.sendRedirect("/todo/list");
  }
}
