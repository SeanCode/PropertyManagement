package cn.edu.cqupt.wyglzx.controller.exception;

import cn.edu.cqupt.wyglzx.common.ErrorResponse;
import cn.edu.cqupt.wyglzx.exception.BaseException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(value = {Exception.class, RuntimeException.class, Throwable.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ErrorResponse defaultErrorHandler(HttpServletRequest request, Exception e, Throwable ex) {
        ErrorResponse errorResponse = new ErrorResponse(BaseException.ERROR_IN_INTERCEPTOR, "an error occurred");

        if (ex != null) {
            String message = ExceptionUtils.getMessage(ex);
            errorResponse.setMessage(message);
        }

        if (e != null) {
            String message = ExceptionUtils.getStackTrace(e);
            errorResponse.setMessage(message);

            if (e instanceof MissingServletRequestParameterException) {
                MissingServletRequestParameterException missingServletRequestParameterException = (MissingServletRequestParameterException) e;
                errorResponse.setCode(BaseException.ERROR_PARAM_NOT_SET);
                errorResponse.setMessage(String.format("%s not set", missingServletRequestParameterException.getParameterName()));
            }

            if (e instanceof BaseException) {
                BaseException baseException = (BaseException) e;
                errorResponse.setCode(baseException.getCode());
                errorResponse.setMessage(baseException.getMessage());
            }

            e.printStackTrace();
        }

        return errorResponse;
    }
}