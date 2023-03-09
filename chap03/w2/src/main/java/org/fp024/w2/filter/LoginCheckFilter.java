package org.fp024.w2.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebFilter(urlPatterns = {"/todo/*"})
public class LoginCheckFilter implements Filter {

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    LOGGER.info("Login check filter ...");

    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse resp = (HttpServletResponse) response;

    HttpSession session = req.getSession();

    if (session.getAttribute("loginInfo") == null) {
      resp.sendRedirect("/login");
      return; // 끝내는것이 중요. 다음 필터로 넘기면 안 됨.
    }

    chain.doFilter(request, response);
  }
}
