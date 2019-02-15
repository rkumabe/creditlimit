package com.zagwork.creditlimit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zagwork.creditlimit.models.CreditLimit;

@Repository
public interface CreditLimitRepository extends JpaRepository<CreditLimit, Long> {
	
}
