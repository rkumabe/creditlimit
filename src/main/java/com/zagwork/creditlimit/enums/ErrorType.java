package com.zagwork.creditlimit.enums;

public enum ErrorType {
	NOT_FOUND("Resource not found.");
	
	private String desc;
    
    private ErrorType(String desc) {
        this.desc = desc;
    }
    
    public String getDesc() {
        return desc;
    }
}
