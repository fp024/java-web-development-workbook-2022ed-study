
package org.fp024.w1;

import static org.fp024.common.Constants.HTML_CONTENT_TYPE;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet(name = "sampleServlet", value = "/sample")
public class SampleServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    LOGGER.info("doGet... {}", this);
    response.setContentType(HTML_CONTENT_TYPE);
    PrintWriter out = response.getWriter();
    out.printf("<h4>현재 서블릿 클래스의 this: %s<h4>%n", this);
  }

  @Override
  public void destroy() {
    LOGGER.info("destroy..............................");
  }

  @Override
  public void init() throws ServletException {
    LOGGER.info("init(ServletConfig)..................");
  }
}
