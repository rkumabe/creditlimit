package com.zagwork.creditlimit.exceptions;

import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

public class BusinessException extends ApplicationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1456173869553063034L;

	public BusinessException(String message) {
    	super(message);
    }

    public BusinessException(BindingResult result) {
        super(buildMessage(result.getAllErrors()));
    }
    
    private static String buildMessage(List<ObjectError> errors) {
        StringBuilder stringBuilder = new StringBuilder();
        for(ObjectError error : errors){
            stringBuilder.append(error.getDefaultMessage());
            stringBuilder.append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }
}
