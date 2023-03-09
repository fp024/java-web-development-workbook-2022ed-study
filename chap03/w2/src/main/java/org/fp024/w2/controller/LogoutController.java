package org.fp024.w2.controller;

import static org.fp024.common.Constants.COOKIE_NAME_REMEMBER_ME;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.fp024.w2.dto.MemberDTO;
import org.fp024.w2.service.MemberService;

@Slf4j
@WebServlet("/logout")
public class LogoutController extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    LOGGER.info("Log out .....");
    HttpSession session = request.getSession();

    if (session.getAttribute("loginInfo") instanceof MemberDTO memberDTO) {
      try {
        MemberService.INSTANCE.updateUuid(memberDTO.getMid(), null);
        Cookie cookie = new Cookie(COOKIE_NAME_REMEMBER_ME, null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
      } catch (Exception e) {
        LOGGER.error("자동 로그인 uuid 정보 삭제 실패 mid:{}", memberDTO.getMid());
      }
    }

    session.removeAttribute("loginInfo");
    session.invalidate();

    response.sendRedirect("/");
  }
}
