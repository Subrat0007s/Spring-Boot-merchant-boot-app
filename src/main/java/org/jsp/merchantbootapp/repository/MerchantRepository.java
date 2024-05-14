package org.jsp.merchantbootapp.repository;

import java.util.List;
import java.util.Optional;

import org.jsp.merchantbootapp.dto.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MerchantRepository extends JpaRepository<Merchant, Integer> {

	public List<Merchant> findByName(String name);

	@Query("select m.name from Merchant m")
	public List<String> findAllNames();

	@Query("select m.id from Merchant m")
	public List<Integer> findAllId();

	@Query("select m.phone from Merchant m")
	public List<Long> findAllPhoneNumbers();

	@Query("select m.email from Merchant m")
	public List<String> findAllEmails();

	@Query("select m.id from Merchant m where m.name=?1")
	Optional<Integer> findIdByName(String name);

	public Optional<Merchant> findByPhone(Long phone);

	@Query("select m from Merchant m where m.gst_number=?1")
	public Optional<Merchant> findBygst_number(String gst_no);

	public Optional<Merchant> findByPhoneAndPassword(Long phone, String password);

	@Query("select m from Merchant m where m.email=?1 and m.password=?2")
	public Optional<Merchant> verifyByEmailAndPassword(String email, String password);

	public Optional<Merchant> findByIdAndPassword(Integer id, String password);

	@Query("SELECT m FROM Merchant m WHERE m.gst_number = ?1 AND m.password = ?2")
	public Optional<Merchant> findByGstNumberAndPassword(String gstNumber, String password);

}
