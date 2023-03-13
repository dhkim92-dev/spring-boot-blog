package kr.dohoonkim.blog.common.errors;

import org.springframework.http.HttpStatus;

public class ServiceRuntimeException extends RuntimeException{
  private final String message;

  private final int status;

  public ServiceRuntimeException(String message, HttpStatus status){
    super(message);
    this.message = message;
    this.status = status.value();
  }
}
