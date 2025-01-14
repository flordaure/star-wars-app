package com.conexaproject.star_wars_app.controller;

import com.conexaproject.star_wars_app.exceptions.DataException;
import com.conexaproject.star_wars_app.exceptions.FormatException;
import com.conexaproject.star_wars_app.exceptions.GeneralException;
import com.conexaproject.star_wars_app.exceptions.ServiceException;
import com.conexaproject.star_wars_app.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(DataException.class)
    public ResponseEntity<ErrorResponse> handlerDataException(DataException dataException) {
        return new ResponseEntity<>(new ErrorResponse(dataException.getCode(), dataException.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<ErrorResponse> handlerGeneralException(GeneralException generalException) {
        return new ResponseEntity<>(new ErrorResponse(generalException.getCode(), generalException.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ErrorResponse> handlerServiceException(ServiceException serviceException) {
        return new ResponseEntity<>(new ErrorResponse(serviceException.getCode(), serviceException.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(FormatException.class)
    public ResponseEntity<ErrorResponse> handlerFormatException(FormatException formatException) {
        return new ResponseEntity<>(new ErrorResponse(formatException.getCode(), formatException.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
