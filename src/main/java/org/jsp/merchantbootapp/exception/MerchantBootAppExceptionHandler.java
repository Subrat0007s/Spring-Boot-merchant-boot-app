package org.jsp.merchantbootapp.exception;

import org.jsp.merchantbootapp.dto.ResponseStrcture;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class MerchantBootAppExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(MerchantNotFoundException.class)
	public ResponseEntity<ResponseStrcture<String>> handlerMNFE(MerchantNotFoundException e) {
		ResponseStrcture<String> strcture = new ResponseStrcture<>();
		strcture.setData("Merchant Not Found");
		strcture.setMessage(e.getMessage());
		strcture.setStatus(HttpStatus.NOT_FOUND.value());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(strcture);
	}
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<ResponseStrcture<String>> handlerPNFE(ProductNotFoundException e) {
		ResponseStrcture<String> strcture = new ResponseStrcture<>();
		strcture.setData("Merchant Not Found");
		strcture.setMessage(e.getMessage());
		strcture.setStatus(HttpStatus.NOT_FOUND.value());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(strcture);
	}
}
