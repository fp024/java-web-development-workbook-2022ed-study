package org.fp024.springex.mapper;

import static org.junit.jupiter.api.Assertions.*;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@Slf4j
@SpringJUnitConfig(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
class TimeMapperTests {

  @Autowired(required = false) private TimeMapper timeMapper;

  @Test
  void testGetTime() {
    LOGGER.info("{}", timeMapper.getTime());
  }
}
