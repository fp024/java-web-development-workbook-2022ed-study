package org.fp024.jdbcex.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;

public enum MapperUtil {
  INSTANCE;

  private final ModelMapper modelMapper;

  MapperUtil() {
    this.modelMapper = new ModelMapper();
    this.modelMapper.getConfiguration() //
        .setFieldMatchingEnabled(true) //
        .setFieldAccessLevel(AccessLevel.PRIVATE)
        .setMatchingStrategy(MatchingStrategies.STRICT);
  }

  public ModelMapper get() {
    return modelMapper;
  }
}
