package kr.dohoonkim.blog.common.errors;

import org.springframework.http.HttpStatus;

public class ConflictException extends ServiceRuntimeException{
  static final String message = "error.conflict";
  static final HttpStatus status = HttpStatus.CONFLICT;

  public ConflictException(String message){
    super(message, status);
  }

}
