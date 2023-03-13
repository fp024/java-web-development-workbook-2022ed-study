package org.fp024.springex.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TimeMapper {
  @Select("SELECT now()")
  String getTime();
}
