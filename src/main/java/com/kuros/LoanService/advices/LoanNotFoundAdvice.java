package com.kuros.LoanService.advices;

import com.kuros.LoanService.exceptions.LoanNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class LoanNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(LoanNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String loanNotFoundHandler(LoanNotFoundException ex) {
        return ex.getMessage();
    }
}
