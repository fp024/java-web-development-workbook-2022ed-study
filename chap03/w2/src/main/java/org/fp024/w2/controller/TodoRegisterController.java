package org.fp024.w2.controller;

import static org.fp024.common.Constants.DATE_FORMAT_YYYY_MM_DD;
import static org.fp024.common.Constants.TODO_VIEW_ROOT;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
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

    HttpSession session = request.getSession();

    // 기존에 JSESSIONID가 없는 새로운 사용자
    if (session.isNew()) {
      LOGGER.info("JSESSIONID 쿠키가 새로 만들어진 사용자");
      response.sendRedirect("/login");
      return;
    }

    // JSESSIONID는 있지만 해당 세션 컨텍스트에 loginInfo라는 이름으로 저장된 객체는 없는 경우.
    if (session.getAttribute("loginInfo") == null) {
      LOGGER.info("로그인 정보가 없는 사용자");
      response.sendRedirect("/login");
      return;
    }

    // 정상적이라면 리다이렉트
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
