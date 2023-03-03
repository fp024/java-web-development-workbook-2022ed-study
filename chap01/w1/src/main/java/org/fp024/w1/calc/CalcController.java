package org.fp024.w1.calc;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet(name = "calcController", value = "/calc/makeResult")
public class CalcController extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String num1 = request.getParameter("num1");
    String num2 = request.getParameter("num2");

    LOGGER.info("num1: {}", num1);
    LOGGER.info("num2: {}", num2);

    response.sendRedirect("/index.jsp");
  }
}
