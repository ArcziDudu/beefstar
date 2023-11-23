package com.beefstar.beefstar.controller.exceptionHandler;

import com.beefstar.beefstar.domain.exception.UserNotFoundException;
import com.beefstar.beefstar.domain.exception.UserWithThatUsernameExists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice(annotations = RestController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionRestHandler extends ResponseEntityExceptionHandler {

    //
//    @ExceptionHandler(InvalidDifficultyException.class)
//    public ResponseEntity<String> handleInvalidDifficultyException(InvalidDifficultyException ex) {
//        String errorMessage = ex.getMessage();
//        String responseBody = "{\"statusCode\": \"BadRequest\", \"error\": \"" + errorMessage + "\"}";
//        return ResponseEntity
//                .status(HttpStatus.BAD_REQUEST)
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(responseBody);
//    }
//
//    @ExceptionHandler(InvalidCategoriesException.class)
//    public ResponseEntity<String> handleInvalidCategoryException(InvalidCategoriesException ex) {
//        String errorMessage = ex.getMessage();
//        String responseBody = "{\"statusCode\": \"BadRequest\", \"error\": \"" + errorMessage + "\"}";
//        return ResponseEntity
//                .status(HttpStatus.BAD_REQUEST)
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(responseBody);
//    }IllegalArgumentException


    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUsernameNotFoundException(UsernameNotFoundException ex) {
        String errorMessage = ex.getMessage();
        String responseBody = "{\"statusCode\": \"NotFound\", \"error\": \"" + errorMessage + "\"}";
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(responseBody);
    }

    @ExceptionHandler(UserWithThatUsernameExists.class)
    public ResponseEntity<String> handleUsernameExists(UserWithThatUsernameExists ex) {
        String errorMessage = ex.getMessage();
        String responseBody = "{\"statusCode\": \"BadRequest\", \"error\": \"" + errorMessage + "\"}";
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(responseBody);
    }
}
