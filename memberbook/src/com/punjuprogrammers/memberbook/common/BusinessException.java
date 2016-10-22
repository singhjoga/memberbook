package com.punjuprogrammers.memberbook.common;

public class BusinessException extends Exception{

	private static final long serialVersionUID = -4479263550830962678L;

	private long errorCode;
	
	public BusinessException() {
		super();
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public BusinessException(String message) {
		super(message);
	}
	public BusinessException(String message, int errorCode) {
		super(message);
		this.errorCode=errorCode;
	}

	public BusinessException(Throwable cause) {
		super(cause);
	}

	public long getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(long errorCode) {
		this.errorCode = errorCode;
	}

}
