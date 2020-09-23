package com.tradestore.app.constants;

public enum ErrorEnum {
	INVALIDTRADE("601","Trade has less version than existing trade");
	
	private String errorCode;
	private String errorDescription;
	
	private ErrorEnum(String errorCode, String errorDescription) {
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorDescription() {
		return errorDescription;
	}
}
