package com.rabobank.service.Exception;

import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author 738575
 *
 */
@RestControllerAdvice
public class GlobalControllerAdvice {

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(GlobalControllerAdvice.class);

	@ExceptionHandler(Exception.class)
	public ExceptionResponseMsg handleException(Exception ex) {
		logger.error("Excpetion Occured while processing the request");
		ExceptionResponseMsg responseMsg = new ExceptionResponseMsg(ex.getMessage());
		return responseMsg;
	}
}