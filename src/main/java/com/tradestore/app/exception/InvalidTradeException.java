package com.tradestore.app.exception;

public class InvalidTradeException extends Exception {

	private static final long serialVersionUID = 1L;

	private String errorCode;
	private String errorDescription;
	
	public String getErrorCode() {
		return errorCode;
	}
	
	public InvalidTradeException setErrorCode(String errorCode) {
		this.errorCode = errorCode;
		return this;
	}
	
	public String getErrorDescription() {
		return errorDescription;
	}
	
	public InvalidTradeException setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
		return this;
	}
	
	public InvalidTradeException() {
		super();
	}

	public InvalidTradeException(String errorCode, String errorDescription) {
		super();
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}

	public InvalidTradeException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidTradeException(String message) {
		super(message);
	}

	public InvalidTradeException(Throwable cause) {
		super(cause);
	}
}
