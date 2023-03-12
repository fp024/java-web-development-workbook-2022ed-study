package org.fp024.sample;

import static org.assertj.core.api.Assertions.assertThat;

import lombok.extern.slf4j.Slf4j;
import org.fp024.springex.sample.SampleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@Slf4j
@SpringJUnitConfig(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
public class SampleTests {

  @Autowired private SampleService sampleService;

  @Test
  void testService1() {
    LOGGER.info(sampleService.toString());
    assertThat(sampleService).isNotNull();
  }
}
