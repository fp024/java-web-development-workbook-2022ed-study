package org.fp024.w1;

import static org.assertj.core.api.Assertions.assertThat;

import org.fp024.test.support.MockHttpServletTestSupport;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class HelloServletTest extends MockHttpServletTestSupport<HelloServlet> {

  @Test
  void testDoGet() throws Exception {
    runGivenWhenThen(
        () -> servlet.init(), // Mock Test에서 servlet의 init()을 자동실행시켜주진 않음, 수동으로 실행해줘야함.
        () -> servlet.doGet(request, response),
        () -> {
          assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
          assertThat(response.getContentAsString()).contains("<h1>Hello world!</h1>");
        });
  }

  @Override
  protected Class<HelloServlet> getServletClass() {
    return HelloServlet.class;
  }

  @Override
  protected String getServletPath() {
    return "/hello-servlet";
  }
}
