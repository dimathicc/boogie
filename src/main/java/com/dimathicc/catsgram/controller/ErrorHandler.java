package com.dimathicc.catsgram.controller;

import com.dimathicc.catsgram.exceptions.IncorrectParameterException;
import com.dimathicc.catsgram.exceptions.InvalidEmailException;
import com.dimathicc.catsgram.exceptions.PostNotFoundException;
import com.dimathicc.catsgram.exceptions.UserAlreadyExistsException;
import com.dimathicc.catsgram.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleInvalidEmailException(final InvalidEmailException e) {
        return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handlePostNotFoundException(final PostNotFoundException e) {
        return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleUserAlreadyExistsException(final UserAlreadyExistsException e) {
        return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleIncorrectParameterException(final IncorrectParameterException e) {
        return new ErrorResponse("Ошибка с полем " + e.getParameter());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleThrowableException(final Throwable e) {
        return new ErrorResponse(e.getMessage());
    }

}
