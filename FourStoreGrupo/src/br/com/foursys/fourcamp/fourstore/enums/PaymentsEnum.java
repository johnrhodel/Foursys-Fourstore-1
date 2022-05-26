package br.com.foursys.fourcamp.fourstore.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum PaymentsEnum {
	
	DEBIT(" D�bito"), 
	CREDIT(" Cr�dito"), 
	PIX(" PIX"), 
	MONEY(" Dinheiro");



	public String key;



	public String getKey() {
	return key;
	}



	private PaymentsEnum(String key) {
	this.key = key;
	}



	public static Map<String, PaymentsEnum> lookup = new HashMap<String, PaymentsEnum>();



	static {
	for (PaymentsEnum p : EnumSet.allOf(PaymentsEnum.class))
	lookup.put(p.getKey(), p);
	}



	public static PaymentsEnum get(String key) {
	return lookup.get(key);
	}

}
