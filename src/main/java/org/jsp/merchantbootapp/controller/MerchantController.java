package org.jsp.merchantbootapp.controller;

import java.util.List;
import org.jsp.merchantbootapp.dto.Merchant;
import org.jsp.merchantbootapp.dto.ResponseStrcture;
import org.jsp.merchantbootapp.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/merchants")
public class MerchantController{
	@Autowired
	private MerchantService merchantService;

	@PostMapping
	public ResponseEntity<ResponseStrcture<Merchant>> saveMerchant(@RequestBody Merchant merchant) {
		return merchantService.saveMerchant(merchant);
	}

	@PutMapping
	public ResponseEntity<ResponseStrcture<Merchant>> updateMerchant(@RequestBody Merchant merchant) {
		return merchantService.updateMerchant(merchant);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseStrcture<Merchant>> findByMerchantId(@PathVariable(name = "id") Integer id) {
		return merchantService.findByMerchantId(id);
	}

	@GetMapping
	public ResponseEntity<ResponseStrcture<List<Merchant>>> findAllMerchant() {
		return merchantService.findAllMerchant();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStrcture<Merchant>> deleteMerchant(@PathVariable(name = "id") Integer id) {
		return merchantService.deleteMerchantDetail(id);
	}

	@GetMapping("/find-by-name/{name}")
	public ResponseEntity<ResponseStrcture<List<Merchant>>> findByName(@PathVariable(name = "name") String name) {
		return merchantService.findByName(name);
	}

	@GetMapping("/find-by-phone/{phone}")
	public ResponseEntity<ResponseStrcture<Merchant>> findByPhone(@PathVariable(name = "phone") Long phone) {
		return merchantService.findByPhone(phone);
	}

	@PostMapping("/verify-by-phone")
	public ResponseEntity<ResponseStrcture<Merchant>> verifyMerchant(@RequestParam Long phone,
			@RequestParam String password) {
		return merchantService.verifyMerchant(phone, password);
	}

	@PostMapping("/verify-by-email")
	public ResponseEntity<ResponseStrcture<Merchant>> verifyMerchant(@RequestParam String email,
			@RequestParam String password) {
		return merchantService.verifyMerchant(email, password);

	}

	@PostMapping("/verify-by-gstno")
	public ResponseEntity<ResponseStrcture<Merchant>> verifyMerchant1(@RequestParam String gst_number,
			@RequestParam String password) {
		return merchantService.verifyMerchant1(gst_number, password);
	}

	@GetMapping("/find-by-gstno/{gst_number}")
	public ResponseEntity<ResponseStrcture<Merchant>> findByGst_number(
			@PathVariable(name = "gst_number") String gst_number) {
		return merchantService.findByGst_number(gst_number);
	}

	@GetMapping("/find-all-names")
	public ResponseEntity<ResponseStrcture<List<String>>> findAllMerchantNames() {
		return merchantService.findAllMerchantNames();
	}

	@GetMapping("/find-all-id")
	public ResponseEntity<ResponseStrcture<List<Integer>>> findAllMerchantIds() {
		return merchantService.findAllMerchantIds();
	}

	@GetMapping("/find-all-phonenumbers")
	public ResponseEntity<ResponseStrcture<List<Long>>> findAllMerchantPhoneNumbers() {
		return (merchantService.findAllMerchantPhoneNumbers());
	}

	@GetMapping("/find-all-emails")
	public ResponseEntity<ResponseStrcture<List<String>>> findAllMerchantEmails() {
		return merchantService.findAllMerchantEmails();
	}

	@GetMapping("/find-id-by-name/{name}")
	public ResponseEntity<Integer> findIdByName(@PathVariable String name) {
		return merchantService.findIdByName(name);

	}

}
