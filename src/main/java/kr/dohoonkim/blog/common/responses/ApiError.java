package kr.dohoonkim.blog.common.responses;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@ToString
@Getter
public class ApiError {
  private final String message;

  private final int status;

  public ApiError(String message, HttpStatus httpStatus){
    this.message = message;
    this.status = httpStatus.value();
  }

  public ApiError(Throwable throwable, HttpStatus status){
    this(throwable.getMessage(), status);
  }
}
