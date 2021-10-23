package com.poc.exception.handler;

import java.util.List;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class AppExceptionHandler extends ResponseEntityExceptionHandler {


  @ExceptionHandler({AppException.class})
  protected ResponseEntity<Object> handleAppExceptions(AppException exception,
      WebRequest request) {
    log.error(exception.getMessage(), exception);
    HttpHeaders headers = this.getRequiredHeaders();
    return this.handleExceptionInternal(exception, new ErrorDetail(exception), headers,
        exception.getHttpStatus(),
        request);
  }
 
  @ExceptionHandler({Exception.class})
  protected ResponseEntity<Object> handleBaseExceptions(Exception exception,
      WebRequest request) {
    log.error(exception.getMessage(), exception);
    HttpHeaders headers = this.getRequiredHeaders();
    return this.handleExceptionInternal(exception, new ErrorDetail(exception), headers,
        HttpStatus.INTERNAL_SERVER_ERROR,
        request);
  }

  private HttpHeaders getRequiredHeaders() {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    return headers;
  }

  @Setter
  @Getter
  public static class ErrorDetail {

    private String code;
    private String message;
    private List<FieldErrorDetail> detail;


    @Setter
    @Getter
    @AllArgsConstructor
    class FieldErrorDetail {
      private String field;
      private String message;
    }

    public ErrorDetail(AppException e) {
      this.code = e.getCode().name();
      this.message = e.getMessage();
    }

    ErrorDetail(Exception e) {
      this.code = "UNEXPECTED_ERROR";
      this.message = "Unexpected error";
    }
  }

}
