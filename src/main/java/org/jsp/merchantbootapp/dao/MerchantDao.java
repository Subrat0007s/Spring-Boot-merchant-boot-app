package org.jsp.merchantbootapp.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.merchantbootapp.dto.Merchant;
import org.jsp.merchantbootapp.repository.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MerchantDao {
	@Autowired
	private MerchantRepository merchantRepository;

	public Merchant saveMerchant( Merchant merchant) {
		return merchantRepository.save(merchant);
	}

	public Optional<Merchant> findByMerchantId(Integer id) {
		return merchantRepository.findById(id);
	}

	public List<Merchant> findAllMerchant() {
		return merchantRepository.findAll();
	}

	public void deleteMerchant(Integer id) {
		merchantRepository.deleteById(id);
	}

	public List<Merchant> findMerchant(String name) {
		return merchantRepository.findByName(name);

	}

	public Optional<Merchant> findMerchant(Long phone) {
		return merchantRepository.findByPhone(phone);
	}

	public Optional<Merchant> verifyMerchant(Long phone, String password) {
		return merchantRepository.findByPhoneAndPassword(phone, password);
	}

	public Optional<Merchant> verifyMerchant(String email, String password) {
		return merchantRepository.verifyByEmailAndPassword(email, password);
	}

	public Optional<Merchant> verifyMerchant1(String gst_number, String password) {
		return merchantRepository.findByGstNumberAndPassword(gst_number, password);
	}

	public Optional<Merchant> findbyGst_number(String gst_number) {
		return merchantRepository.findBygst_number(gst_number);

	}

	public List<String> findAllNames() {
		return merchantRepository.findAllNames();
	}

	public List<Integer> findAllIds() {
		return merchantRepository.findAllId();
	}

	public List<Long> findAllPhoneNumbers() {
		return merchantRepository.findAllPhoneNumbers();
	}

	public List<String> findAllMerchantEmails() {
		return merchantRepository.findAllEmails();
	}

	public Optional<Integer> findIdByName(String name) {
		return merchantRepository.findIdByName(name);

	}

}
