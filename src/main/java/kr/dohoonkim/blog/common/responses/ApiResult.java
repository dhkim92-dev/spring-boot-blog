package kr.dohoonkim.blog.common.responses;

import lombok.AllArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@ToString
public class ApiResult<T> {
  private final boolean success;

  private final T response;

  private final ApiError error;

  public static <T> ApiResult<T> OK(T response){
    return new ApiResult<T>(true, response, null);
  }

  public static ApiResult<?> ERROR(Throwable throwable, HttpStatus status){
    return new ApiResult<>(false, null, new ApiError(throwable, status));
  }

  public static ApiResult<?> ERROR(String message, HttpStatus status){
    return new ApiResult<>(false, null, new ApiError(message, status));
  }

  public boolean isSuccess(){
    return success;
  }

  public ApiError getError(){
    return error;
  }

  public T getResponse() {
    return response;
  }
}
