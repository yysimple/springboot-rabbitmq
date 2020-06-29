package com.jxkj.handler;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 功能描述：
 *
 * @author wcx
 * @version 1.0
 */
@ControllerAdvice
public class HandleControllerException {

    @ExceptionHandler(RuntimeException.class)
    public String exceptionHandler(RuntimeException e){
        if (e instanceof AccessDeniedException) {
            return "redirect:/403.jsp";
        }
        return "redirect:/500.jsp";
    }
}
