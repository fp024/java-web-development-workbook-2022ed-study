package org.fp024.common;

import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {

  public static final String PROJECT_ENCODING = StandardCharsets.UTF_8.name();

  public static final String TODO_VIEW_ROOT = "/WEB-INF/todo";

  public static final DateTimeFormatter DATE_FORMAT_YYYY_MM_DD =
      DateTimeFormatter.ofPattern("yyyy-MM-dd");

  public static final String HTML_CONTENT_TYPE =
      String.format("text/html;charset=%s", PROJECT_ENCODING);
}
