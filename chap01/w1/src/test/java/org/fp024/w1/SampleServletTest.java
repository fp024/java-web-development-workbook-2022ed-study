package org.fp024.w1;

import static org.assertj.core.api.Assertions.assertThat;

import org.fp024.test.support.MockHttpServletTestSupport;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class SampleServletTest extends MockHttpServletTestSupport<SampleServlet> {

  @Test
  void testDoGet() throws Exception {
    runGivenWhenThen(
        () -> {
        },
        () -> servlet.doGet(request, response),
        () -> {
          assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
          assertThat(response.getContentAsString()).contains(
              String.format("<h4>현재 서블릿 클래스의 this: %s<h4>", servlet));
        });
  }

  @Override
  protected Class<SampleServlet> getServletClass() {
    return SampleServlet.class;
  }

  @Override
  protected String getServletPath() {
    return "/sample";
  }
}
