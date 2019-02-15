package com.zagwork.creditlimit.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.zagwork.creditlimit.enums.Risk;

@Converter
public class RiskConverter implements AttributeConverter<Risk, Character> {
	@Override
	public Character convertToDatabaseColumn(Risk risk) {
		return risk!=null?risk.getCode():null;
	}

	@Override
	public Risk convertToEntityAttribute(Character dbData) {
		if (dbData==null)
			return Risk.NOVALUE;
		switch(dbData) {
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
}
