package org.jsp.merchantbootapp.exception;

@SuppressWarnings("serial")
public class MerchantNotFoundException extends RuntimeException {

	public MerchantNotFoundException(String message) {
		super(message);
	}
}
