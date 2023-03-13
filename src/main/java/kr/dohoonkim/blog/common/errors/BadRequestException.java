package kr.dohoonkim.blog.common.errors;

import org.springframework.http.HttpStatus;
import reactor.util.annotation.Nullable;

public class BadRequestException extends ServiceRuntimeException{
  static final String message = "error.bad_request";
  static final HttpStatus status = HttpStatus.BAD_REQUEST;

  public BadRequestException(String message){
    super(message, status);
  }
}
