package org.fp024.w1;

import static org.assertj.core.api.Assertions.assertThat;

import org.fp024.test.support.MockHttpServletTestSupport;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class MyServletTest extends MockHttpServletTestSupport<MyServlet> {

  @Test
  void testDoGet() throws Exception {
    runGivenWhenThen(
        () -> {},
        () -> servlet.doGet(request, response),
        () -> {
          assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
          assertThat(response.getContentAsString()).contains("<h1>MyServlet</h1>");
        });
  }

  @Override
  protected Class<MyServlet> getServletClass() {
    return MyServlet.class;
  }

  @Override
  protected String getServletPath() {
    return "/my";
  }
}
