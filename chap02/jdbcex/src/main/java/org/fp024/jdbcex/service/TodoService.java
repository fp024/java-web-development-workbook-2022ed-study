package org.fp024.jdbcex.service;

import lombok.extern.slf4j.Slf4j;
import org.fp024.jdbcex.dao.TodoDAO;
import org.fp024.jdbcex.domain.TodoVO;
import org.fp024.jdbcex.dto.TodoDTO;
import org.fp024.jdbcex.util.MapperUtil;
import org.modelmapper.ModelMapper;

@Slf4j
public enum TodoService {
  INSTANCE;

  private final TodoDAO dao;
  private final ModelMapper modelMapper;

  TodoService() {
    dao = new TodoDAO();
    modelMapper = MapperUtil.INSTANCE.get();
  }

  public void register(TodoDTO todoDTO) throws Exception {
    TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);

    LOGGER.info("todoVO: {}", todoVO);

    dao.insert(todoVO);
  }

}
