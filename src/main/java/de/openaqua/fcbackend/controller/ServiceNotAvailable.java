package de.openaqua.fcbackend.controller;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(org.springframework.http.HttpStatus.NOT_FOUND)
public class ServiceNotAvailable extends RuntimeException {

  /**
   * 
   */
  private static final long serialVersionUID = -8056562956332569355L;

  /**
   * 
   */
  public ServiceNotAvailable() {
    super();
  }

  public ServiceNotAvailable(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  public ServiceNotAvailable(String message, Throwable cause) {
    super(message, cause);
  }

  public ServiceNotAvailable(String message) {
    super(message);
  }

  public ServiceNotAvailable(Throwable cause) {
    super(cause);
  }

}
