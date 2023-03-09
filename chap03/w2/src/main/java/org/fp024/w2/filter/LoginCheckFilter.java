package org.fp024.w2.filter;

import static org.fp024.common.CommonUtils.getCookie;
import static org.fp024.common.Constants.COOKIE_NAME_REMEMBER_ME;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.fp024.w2.dto.MemberDTO;
import org.fp024.w2.service.MemberService;

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

    // 유효한 회원정보가 세션에 있다면 다음으로 바로 진행.
    if (session.getAttribute("loginInfo") != null) {
      chain.doFilter(request, response);
      return;
    }

    // session에 loginInfo 값이 없으면, 쿠키를 체크
    Optional<Cookie> rememberMeCookie = getCookie(
        req.getCookies(), COOKIE_NAME_REMEMBER_ME);

    if (!rememberMeCookie.isPresent()) {
      resp.sendRedirect("/login");
      return;
    }

    LOGGER.info("remember-me 쿠키 사용...");
    try {
      MemberDTO memberDTO = MemberService.INSTANCE.getByUUID(rememberMeCookie.get().getValue());

      if (memberDTO == null) {
        throw new IllegalArgumentException("Cookie value is not Valid");
      }

      session.setAttribute("loginInfo", memberDTO);
      chain.doFilter(request, response);

    } catch (Exception e) {
      resp.sendRedirect("/login");
    }
  }
}
