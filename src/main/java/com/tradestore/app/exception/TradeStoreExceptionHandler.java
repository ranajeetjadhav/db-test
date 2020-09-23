package com.tradestore.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class TradeStoreExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(InvalidTradeException.class)
	public ResponseEntity<ExceptionResponse> handleInvalidTradeException(InvalidTradeException ex) {
		 return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(ex.getErrorCode(), ex.getErrorDescription()), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// add remaining exceptions
}
