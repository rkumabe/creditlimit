package com.zagwork.creditlimit.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Risk {
	A('A'),B('B'),C('C'),NOVALUE(null);
	
	private Character code;

	Risk(Character code){
		this.code = code;
	}
	
	public Character getCode(){
		return this.code;
	}
	
	@JsonCreator
	public static Risk forValue(Character value) {
		switch(value) {
			case 'A':
				return Risk.A;
			case 'B':
				return Risk.B;
			case 'C':
				return Risk.C;
			default: 
				return Risk.NOVALUE;
		}
	}
	
	@JsonValue
    public Character toValue() {
		return this.getCode();
    }
}
