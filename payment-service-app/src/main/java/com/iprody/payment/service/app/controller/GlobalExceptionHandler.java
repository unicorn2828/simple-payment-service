package com.iprody.payment.service.app.controller;

import com.iprody.payment.service.app.exception.ExceptionMessageModel;
import com.iprody.payment.service.app.exception.ServiceException;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ExceptionMessageModel> handleException(ServiceException ex) {
        ExceptionMessageModel errorMessage = new ExceptionMessageModel(ex.getMessage());
        logger.error(ex.getMessage());
        return new ResponseEntity<>(errorMessage, ex.getStatus());
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<ExceptionMessageModel> handleException(AuthorizationDeniedException ex) {
        ExceptionMessageModel errorMessage = new ExceptionMessageModel(ex.getMessage());
        logger.error(ex.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionMessageModel> handleException(Exception ex) {
        ExceptionMessageModel errorMessage = new ExceptionMessageModel(ex.getMessage());
        logger.error(ex.getMessage());
        logger.error(ex.getStackTrace());
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

}
