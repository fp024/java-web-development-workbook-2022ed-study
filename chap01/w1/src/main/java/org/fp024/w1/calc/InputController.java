package org.fp024.w1.calc;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet(name = "inputController", value = "/calc/input")
public class InputController extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    LOGGER.info("{}... dGet...", getClass().getSimpleName());

    RequestDispatcher dispatcher = request.getRequestDispatcher(
        "/WEB-INF/calc/input.jsp");

    dispatcher.forward(request, response);

  }
}
