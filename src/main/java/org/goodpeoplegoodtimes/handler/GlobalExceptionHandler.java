package org.goodpeoplegoodtimes.handler;


import lombok.extern.slf4j.Slf4j;
import org.goodpeoplegoodtimes.exception.EmailAlreadyExistsException;
import org.goodpeoplegoodtimes.exception.NicknameAlreadyExistsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public void EmailAlreadyExistsExceptionHandler(EmailAlreadyExistsException e) {
        log.error(e.getMessage());
    }

    @ExceptionHandler(NicknameAlreadyExistsException.class)
    public void NicknameAlreadyExistsExceptionHandler(NicknameAlreadyExistsException e) {
        log.error(e.getMessage());
    }

}
