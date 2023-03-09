package org.fp024.w2.listener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebListener
public class LoginListener implements HttpSessionAttributeListener {

  @Override
  public void attributeAdded(HttpSessionBindingEvent event) {
    String name = event.getName();
    Object obj = event.getValue();

    if (name.equals("loginInfo")) {
      LOGGER.info("A user logined .....");
      LOGGER.info(obj.toString());
    }
  }
}
