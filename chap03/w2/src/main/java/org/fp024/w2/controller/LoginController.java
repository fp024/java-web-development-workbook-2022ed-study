package org.fp024.w2.controller;

import static org.fp024.common.Constants.TODO_VIEW_ROOT;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet("/login")
public class LoginController extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    LOGGER.info("login get .....");

    request.getRequestDispatcher(TODO_VIEW_ROOT.concat("/login.jsp")) //
        .forward(request, response);
  }


  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    LOGGER.info("login post .....");

    String mid = request.getParameter("mid");
    String mpw = request.getParameter("mpw");

    String str = mid + mpw;

    HttpSession session = request.getSession();

    session.setAttribute("loginInfo", str);

    response.sendRedirect("/todo/list");
  }
}
