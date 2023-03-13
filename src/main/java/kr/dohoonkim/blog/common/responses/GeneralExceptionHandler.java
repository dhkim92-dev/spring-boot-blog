package kr.dohoonkim.blog.common.responses;

import kr.dohoonkim.blog.common.errors.BadRequestException;
import org.hibernate.TypeMismatchException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;
import org.webjars.NotFoundException;

import static kr.dohoonkim.blog.common.responses.ApiResult.ERROR;

@RestControllerAdvice
public class GeneralExceptionHandler {
  private final Logger logger = LoggerFactory.getLogger(getClass());

  private ResponseEntity<ApiResult<?>> newResponse(Throwable throwable, HttpStatus status){
    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-Type", "application/json");
    return new ResponseEntity<>(ERROR(throwable, status), headers, status);
  }

  @ExceptionHandler({
          IllegalStateException.class, IllegalArgumentException.class,
          TypeMismatchException.class, HttpMessageNotReadableException.class,
          MissingServletRequestParameterException.class, MultipartException.class,
          BadRequestException.class, EmptyResultDataAccessException.class})
  public ResponseEntity<?> handleBadRequestException(Exception e){
    logger.debug("Bad request exception occured: {}", e.getMessage(), e);
    return newResponse(e, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<?> handleNotFoundException(Exception e){
    return newResponse(e, HttpStatus.NOT_FOUND);
  }
}
