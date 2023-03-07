package org.fp024.jdbcex.dao;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@Slf4j
class TodoDAOTests {

  private TodoDAO todoDAO;

  @BeforeEach
  void ready() {
    todoDAO = new TodoDAO();
  }

  @Test
  void testTime() {
    LOGGER.info("now: {}", todoDAO.getTime());
  }

  @Test
  void testTime2() throws Exception {
    LOGGER.info("now: {}", todoDAO.getTime2());
  }
}