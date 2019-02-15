package com.zagwork.creditlimit.exceptions;

import com.zagwork.creditlimit.enums.ErrorType;

public class ApplicationException extends Exception{

    private static final long serialVersionUID = 1L;
    
    private final Integer responseCode;
    
    private final ErrorType errorType;
    
    public ApplicationException(String message) {
        super(message);
         this.responseCode = null;
         this.errorType = null;
    }
    
    public ApplicationException(String message, Integer responseCode, ErrorType errorType) {
        super(message);
        this.responseCode = responseCode;
        this.errorType = errorType;
    }

	public Integer getResponseCode() {
		return this.responseCode;
	}

	public ErrorType getErrorType() {
		return errorType;
	}
	
}
