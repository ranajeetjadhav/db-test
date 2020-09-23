package com.tradestore.app.exception;

public class ExceptionResponse {

	private String errorCode;
	private String errorDescription;
	
	public ExceptionResponse() {
		super();
	}

	public ExceptionResponse(String errorCode, String errorDescription) {
		super();
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}
}
