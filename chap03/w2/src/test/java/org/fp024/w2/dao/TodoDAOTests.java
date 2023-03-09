package org.fp024.w2.dao;

import java.time.LocalDate;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.fp024.common.DBUtils;
import org.fp024.w2.domain.TodoVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@Slf4j
class TodoDAOTests {

  private TodoDAO todoDAO;

  @BeforeEach
  void ready() {
    DBUtils.resetDB();
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
    Long tno = 1L;
    TodoVO vo = todoDAO.selectOne(tno);

    LOGGER.info("vo: {}", vo);
  }

  @Test
  void testDeleteOne() throws Exception {
    todoDAO.deleteOne(1L);
  }

  @Test
  void testUpdateOne() throws Exception {
    TodoVO vo = TodoVO.builder()
        .tno(1L)
        .title("Sample Title...")
        .dueDate(LocalDate.of(2023, 3, 7))
        .finished(true)
        .build();

    todoDAO.updateOne(vo);
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