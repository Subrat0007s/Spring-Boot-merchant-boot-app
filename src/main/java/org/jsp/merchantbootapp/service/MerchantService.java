package org.jsp.merchantbootapp.service;

import java.util.List;
import java.util.Optional;

import org.jsp.merchantbootapp.dao.MerchantDao;
import org.jsp.merchantbootapp.dto.Merchant;
import org.jsp.merchantbootapp.dto.ResponseStrcture;
import org.jsp.merchantbootapp.exception.MerchantNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MerchantService {

	@Autowired
	private MerchantDao merchantDao;

	public ResponseEntity<ResponseStrcture<Merchant>> saveMerchant(Merchant merchant) {
		ResponseStrcture<Merchant> strcture = new ResponseStrcture<>();
		strcture.setMessage("Merchant Saved");
		strcture.setData(merchantDao.saveMerchant(merchant));
		strcture.setStatus(HttpStatus.CREATED.value());
		return ResponseEntity.status(HttpStatus.CREATED).body(strcture);
	}

	public ResponseEntity<ResponseStrcture<Merchant>> updateMerchant(Merchant merchant) {
		ResponseStrcture<Merchant> strcture = new ResponseStrcture<>();
		Optional<Merchant> optional = merchantDao.findByMerchantId(merchant.getId());
		if (optional.isPresent()) {
			Merchant dbMerchant = optional.get();
			dbMerchant.setName(merchant.getName());
			dbMerchant.setPhone(merchant.getPhone());
			dbMerchant.setEmail(merchant.getEmail());
			dbMerchant.setGst_number(merchant.getGst_number());
			dbMerchant.setPassword(merchant.getPassword());
			strcture.setMessage("Merchant Updated");
			strcture.setStatus(HttpStatus.ACCEPTED.value());
			strcture.setData(merchantDao.saveMerchant(dbMerchant));
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(strcture);
		}
		throw new MerchantNotFoundException("Invalid Id");
	}

	public ResponseEntity<ResponseStrcture<Merchant>> findByMerchantId(Integer id) {
		ResponseStrcture<Merchant> strcture = new ResponseStrcture<>();
		Optional<Merchant> optional = merchantDao.findByMerchantId(id);
		if (optional.isPresent()) {
			strcture.setMessage("Id Found");
			strcture.setData(optional.get());
			strcture.setStatus(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(strcture);
		}
		throw new MerchantNotFoundException("Invalid Id");
	}

	public ResponseEntity<ResponseStrcture<List<Merchant>>> findAllMerchant() {
		ResponseStrcture<List<Merchant>> strcture = new ResponseStrcture<>();
		strcture.setData(merchantDao.findAllMerchant());
		strcture.setMessage("Merchant Lists");
		strcture.setStatus(HttpStatus.OK.value());
		return ResponseEntity.status(HttpStatus.OK).body(strcture);
	}

	public ResponseEntity<ResponseStrcture<Merchant>> deleteMerchantDetail(Integer id) {
		ResponseStrcture<Merchant> strcture = new ResponseStrcture<>();
		Optional<Merchant> optional = merchantDao.findByMerchantId(id);
		if (optional.isPresent()) {
			strcture.setMessage("Merchant Deleted");
			strcture.setData(null);
			merchantDao.deleteMerchant(id);
			strcture.setStatus(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(strcture);
		}
		throw new MerchantNotFoundException("Invalid Id, Merchant can't be deleted.");
	}

	public ResponseEntity<ResponseStrcture<List<Merchant>>> findByName(String name) {
		ResponseStrcture<List<Merchant>> strcture = new ResponseStrcture<>();
		List<Merchant> merchants = merchantDao.findMerchant(name);
		strcture.setData(merchants);
		if (merchants.isEmpty()) {
			throw new MerchantNotFoundException("Invalid Name");
		}
		strcture.setMessage("List of Names");
		strcture.setStatus(HttpStatus.OK.value());
		return ResponseEntity.status(HttpStatus.OK).body(strcture);
	}

	public ResponseEntity<ResponseStrcture<Merchant>> findByPhone(Long phone) {
		ResponseStrcture<Merchant> strcture = new ResponseStrcture<>();
		Optional<Merchant> dbmerchant = merchantDao.findMerchant(phone);
		if (dbmerchant.isPresent()) {
			strcture.setMessage("Merchant Found");
			strcture.setData(dbmerchant.get());
			strcture.setStatus(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(strcture);
		}
		throw new MerchantNotFoundException("Invalid Phone Number Try again");
	}

	public ResponseEntity<ResponseStrcture<Merchant>> verifyMerchant(Long phone, String password) {
		ResponseStrcture<Merchant> strcture = new ResponseStrcture<>();
		Optional<Merchant> dbmerchant = merchantDao.verifyMerchant(phone, password);
		if (dbmerchant.isPresent()) {
			strcture.setMessage("Merchant Verified!.");
			strcture.setData(dbmerchant.get());
			strcture.setStatus(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(strcture);
		}
		throw new MerchantNotFoundException("Invalid Credential");
	}

	public ResponseEntity<ResponseStrcture<Merchant>> verifyMerchant(String email, String password) {
		ResponseStrcture<Merchant> strcture = new ResponseStrcture<>();
		Optional<Merchant> dbmerchant = merchantDao.verifyMerchant(email, password);
		if (dbmerchant.isPresent()) {
			strcture.setMessage("Merchant Verified!.");
			strcture.setData(dbmerchant.get());
			strcture.setStatus(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(strcture);
		}
		throw new MerchantNotFoundException("Invalid Credential");
	}

	public ResponseEntity<ResponseStrcture<Merchant>> verifyMerchant1(String gst_number, String password) {
		ResponseStrcture<Merchant> strcture = new ResponseStrcture<>();
		Optional<Merchant> dbmerchant = merchantDao.verifyMerchant1(gst_number, password);
		if (dbmerchant.isPresent()) {
			strcture.setMessage("Merchant Verified!.");
			strcture.setData(dbmerchant.get());
			strcture.setStatus(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(strcture);
		}
		throw new MerchantNotFoundException("Invalid Credential");
	}

	public ResponseEntity<ResponseStrcture<Merchant>> findByGst_number(String gst_number) {
		ResponseStrcture<Merchant> strcture = new ResponseStrcture<>();
		Optional<Merchant> dbmerchant = merchantDao.findbyGst_number(gst_number);
		if (dbmerchant.isPresent()) {
			strcture.setMessage("Merchant Verified!.");
			strcture.setData(dbmerchant.get());
			strcture.setStatus(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(strcture);
		}
		throw new MerchantNotFoundException("Invalid GST_Number");
	}

	public ResponseEntity<ResponseStrcture<List<String>>> findAllMerchantNames() {
		ResponseStrcture<List<String>> strcture = new ResponseStrcture<>();
		strcture.setData(merchantDao.findAllNames());
		strcture.setMessage("Merchant Lists");
		strcture.setStatus(HttpStatus.OK.value());
		return ResponseEntity.status(HttpStatus.OK).body(strcture);
	}

	public ResponseEntity<ResponseStrcture<List<Integer>>> findAllMerchantIds() {
		ResponseStrcture<List<Integer>> strcture = new ResponseStrcture<>();
		strcture.setData(merchantDao.findAllIds());
		strcture.setMessage("Merchant Lists");
		strcture.setStatus(HttpStatus.OK.value());
		return ResponseEntity.status(HttpStatus.OK).body(strcture);
	}

	public ResponseEntity<ResponseStrcture<List<Long>>> findAllMerchantPhoneNumbers() {
		ResponseStrcture<List<Long>> strcture = new ResponseStrcture<>();
		strcture.setData(merchantDao.findAllPhoneNumbers());
		strcture.setMessage("Merchant Lists");
		strcture.setStatus(HttpStatus.OK.value());
		return ResponseEntity.status(HttpStatus.OK).body(strcture);
	}

	public ResponseEntity<ResponseStrcture<List<String>>> findAllMerchantEmails() {
		ResponseStrcture<List<String>> strcture = new ResponseStrcture<>();
		strcture.setData(merchantDao.findAllMerchantEmails());
		strcture.setMessage("Merchant Lists");
		strcture.setStatus(HttpStatus.OK.value());
		return ResponseEntity.status(HttpStatus.OK).body(strcture);
	}

	public ResponseEntity<Integer> findIdByName(String name) {
		Optional<Integer> merchant = merchantDao.findIdByName(name);
		if (merchant.isPresent()) {
			return new ResponseEntity<>(merchant.get(), HttpStatus.OK);
		} else {
			throw new MerchantNotFoundException("Invalid Credential");
		}
	}
}
