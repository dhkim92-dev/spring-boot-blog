package kr.dohoonkim.blog.common.errors;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends ServiceRuntimeException{
  private final String message = "error.";
  private final HttpStatus status = HttpStatus.UNAUTHORIZED
}
