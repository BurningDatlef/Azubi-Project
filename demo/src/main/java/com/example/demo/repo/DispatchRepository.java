package com.example.demo.repo;

import com.example.demo.entity.Dispatch;
import com.example.demo.entity.DispatchStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface DispatchRepository extends JpaRepository<Dispatch, Long> {

	Optional<Dispatch> findByDispatchId(String dispatchId);

	List<Dispatch> findByStatus(DispatchStatus status);

	@Query("""
       select count(d) > 0
       from Dispatch d
       join d.prescription p
       where d.customerNumber = :customerNumber
       and p.documentId = :documentId
       """)
	boolean existsByCustomerNumberAndDocumentId(
			@Param("customerNumber") Integer customerNumber,
			@Param("documentId") String documentId
	);

	List<Dispatch> findByStatusAndSubmittedAtBefore(
			DispatchStatus status,
			LocalDateTime submittedAt
	);
}