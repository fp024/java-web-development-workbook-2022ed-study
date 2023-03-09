package org.fp024.w2.filter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.fp024.common.Constants.PROJECT_ENCODING;

import jakarta.servlet.ServletException;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

class UTF8FilterTest {

  private MockHttpServletRequest request;
  private MockHttpServletResponse response;

  private MockFilterChain chain;

  private UTF8Filter filter;

  @BeforeEach
  void beforeEach() {
    request = new MockHttpServletRequest();
    response = new MockHttpServletResponse();
    chain = new MockFilterChain();
    filter = new UTF8Filter();
  }

  @Test
  void testDoFilter() throws ServletException, IOException {
    // given
    // // 초기 준비 설정 없음
    // when
    filter.doFilter(request, response, chain);
    // then
    assertThat(request.getCharacterEncoding()) //
        .isEqualTo(PROJECT_ENCODING);
  }

}