package qingyun.ele;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class ErrorHandler {

    //todo email to developer, or report  to jira
    @ExceptionHandler(value = {Exception.class, RuntimeException.class})
    public ErrorResponse defaultErrorHandler(HttpServletRequest request, Exception e) {
    	e.printStackTrace();
    	ErrorResponse response = new ErrorResponse();
    	response.setStatus("10000");
    	response.setPath(request.getRequestURI());
    	response.setEleError(e.getClass().getName());
    	response.setMsg(e.getLocalizedMessage());
    	response.setValid(false);
    	response.setTimestamp(new Date());
        return response;         
    }
}