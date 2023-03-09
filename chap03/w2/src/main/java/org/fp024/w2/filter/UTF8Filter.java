package org.fp024.w2.filter;

import static org.fp024.common.Constants.PROJECT_ENCODING;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebFilter(urlPatterns = {"/*"})
public class UTF8Filter implements Filter {

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    LOGGER.info("UTF-8 Filter ..... ");

    HttpServletRequest req = (HttpServletRequest) request;
    req.setCharacterEncoding(PROJECT_ENCODING);
    chain.doFilter(request, response);
  }
}
