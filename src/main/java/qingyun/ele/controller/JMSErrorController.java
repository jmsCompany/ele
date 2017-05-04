package qingyun.ele.controller;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import qingyun.ele.ErrorResponse;

@RestController
public class JMSErrorController implements ErrorController {

	private static final String PATH = "/error";

	@RequestMapping(value = PATH, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ErrorResponse error(HttpServletRequest request) {

		HttpStatus status = getStatus(request);
		ErrorResponse response = new ErrorResponse();
		response.setStatus("" + status.value());
		response.setPath(request.getRequestURI());
		response.setEleError(status.name());
		String message = (String) request.getAttribute("javax.servlet.error.message");
		response.setMsg(message);
		response.setValid(false);
		response.setTimestamp(new Date());
		return response;
	}

	@Override
	public String getErrorPath() {
		return PATH;
	}

	private HttpStatus getStatus(HttpServletRequest request) {
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		if (statusCode != null) {
			try {
				return HttpStatus.valueOf(statusCode);
			} catch (Exception ex) {
			}
		}
		return HttpStatus.INTERNAL_SERVER_ERROR;
	}

}