package org.fp024.jdbcex.controller;

import static org.fp024.common.Constants.TODO_VIEW_ROOT;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.fp024.jdbcex.dto.TodoDTO;
import org.fp024.jdbcex.service.TodoService;

@Slf4j
@WebServlet(name = "todoReadController", value = "/todo/read")
public class TodoReadController extends HttpServlet {

  private TodoService todoService = TodoService.INSTANCE;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      Long tno = Long.parseLong(request.getParameter("tno"));
      TodoDTO todoDTO = todoService.get(tno);

      // 데이터 담기
      request.setAttribute("dto", todoDTO);

      request.getRequestDispatcher(TODO_VIEW_ROOT + "/read.jsp") //
          .forward(request, response);
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      throw new ServletException("read error");
    }
  }
}
