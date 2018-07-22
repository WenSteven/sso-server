package cn.wenqi.oauth2.service.exception;

import cn.wenqi.oauth2.constant.CommonConstant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author wenqi
 * @since v
 */
@RestControllerAdvice
public class ExHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> doEx(Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(CommonConstant.ERROR);
    }
}
