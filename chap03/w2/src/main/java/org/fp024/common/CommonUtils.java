package org.fp024.common;

import jakarta.servlet.http.Cookie;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommonUtils {

  public static String ifNullToNullString(String string) {
    return Objects.requireNonNullElse(string, "null");
  }

  public static Optional<Cookie> getCookie(Cookie[] cookies, String key) {
    if (cookies == null || cookies.length == 0) {
      return Optional.empty();
    }
    return Arrays.stream(cookies).filter(c -> key.equals(c.getName())).findAny();
  }
}
