package com.zagwork.creditlimit.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zagwork.creditlimit.exceptions.BusinessException;
import com.zagwork.creditlimit.models.CreditLimit;
import com.zagwork.creditlimit.repositories.CreditLimitRepository;

@Service
public class CreditLimitService {
	@Autowired
	private CreditLimitRepository creditLimitRepository;
	
	public List<CreditLimit> findAll(){
		return creditLimitRepository.findAll();
	}
	
	public CreditLimit getOne(Long id){
		return creditLimitRepository.getOne(id);
	}
	
	public CreditLimit save(CreditLimit creditLimit) throws BusinessException{
		switch (creditLimit.getRisk()) {
		case A:
			creditLimit.setFees(new BigDecimal(0.00));			
			break;
		case B:
			creditLimit.setFees(new BigDecimal(0.10));
			break;
		case C:
			creditLimit.setFees(new BigDecimal(0.20));
			break;
		default:
			throw new BusinessException("Risco inválido!");
		}
		
		return creditLimitRepository.save(creditLimit);
	}
	
	public void validate(CreditLimit creditLimit) throws BusinessException {
    	if(creditLimit.getCustomerName() == null || creditLimit.getCustomerName().isEmpty()) {
    		throw new BusinessException("O nome do cliente é mandatório.");
    	}
    	
    	if(creditLimit.getRisk() == null) {
    		throw new BusinessException("O risco é mandatório.");
    	}
    	
    	if(creditLimit.getCreditLimitAmount() == null) {
    		throw new BusinessException("O limite de crédito é mandatório.");
    	}
    	
    }

	public CreditLimit update(CreditLimit creditLimit) throws BusinessException {
		switch (creditLimit.getRisk()) {
		case A:
			creditLimit.setFees(new BigDecimal(0.00));			
			break;
		case B:
			creditLimit.setFees(new BigDecimal(10.00));
			break;
		case C:
			creditLimit.setFees(new BigDecimal(20.00));
			break;
		default:
			throw new BusinessException("Risco inválido!");
		}
		
		return creditLimitRepository.save(creditLimit);		
	}
	
	public void delete(Long id) throws BusinessException {
		creditLimitRepository.deleteById(id);
	}

}
