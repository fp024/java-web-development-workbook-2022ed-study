package org.fp024.jdbcex.dao;

import java.time.LocalDate;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.fp024.jdbcex.TodoVO;
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
  void testInsert() throws Exception {
    TodoVO todoVO = TodoVO.builder()
        .title("Sample TItle...")
        .dueDate(LocalDate.of(2021, 12, 31))
        .build();

    todoDAO.insert(todoVO);
  }

  @Test
  void testList() throws Exception {
    List<TodoVO> list = todoDAO.selectAll();

    list.forEach(vo -> LOGGER.info(vo.toString()));
  }

  @Test
  void testSelectOne() throws Exception {
    Long tno = 2L;
    TodoVO vo = todoDAO.selectOne(tno);

    LOGGER.info("vo: {}", vo);
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