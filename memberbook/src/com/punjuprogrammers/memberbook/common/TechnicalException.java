package com.punjuprogrammers.memberbook.common;

public class TechnicalException extends RuntimeException{

	private static final long serialVersionUID = -4479263550830962678L;

	private long errorCode;
	
	public TechnicalException() {
		super();
	}

	public TechnicalException(String message, Throwable cause) {
		super(message, cause);
	}

	public TechnicalException(String message) {
		super(message);
	}
	public TechnicalException(String message, int errorCode) {
		super(message);
		this.errorCode=errorCode;
	}

	public TechnicalException(Throwable cause) {
		super(cause);
	}

	public long getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(long errorCode) {
		this.errorCode = errorCode;
	}

}
