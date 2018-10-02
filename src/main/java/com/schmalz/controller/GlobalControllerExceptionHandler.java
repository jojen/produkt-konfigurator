package com.schmalz.controller;

import com.schmalz.exception.BackendError;
import com.schmalz.exception.RequestError;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
class GlobalControllerExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)  // 503
    @ExceptionHandler(BackendError.class)
    public void handleBackendError(HttpServletRequest req, Exception ex) {
        log.error(req.getRequestURL().toString(), ex);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)  // 400
    @ExceptionHandler(RequestError.class)
    public void handleRequestError(HttpServletRequest req, Exception ex) {
        log.warn(req.getRequestURL().toString());
    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)  // 500
    @ExceptionHandler(Exception.class)
    public void handleConflict(HttpServletRequest req, Exception ex) {
        log.error(req.getRequestURL().toString(), ex);
    }


}