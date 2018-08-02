package cn.wenqi.oauth2.web.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author wenqi
 * @since v
 */
@RestControllerAdvice
@Slf4j
public class ExHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> doEx(Exception e){
        log.error("出现异常：{}",e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
}
