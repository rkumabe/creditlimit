package com.zagwork.creditlimit.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.zagwork.creditlimit.exceptions.ApplicationException;
import com.zagwork.creditlimit.models.CreditLimit;
import com.zagwork.creditlimit.services.CreditLimitService;

@RestController
@RequestMapping("/creditLimit")
public class CreditLimitController {
	@Autowired
	private CreditLimitService creditLimitService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CreditLimit>> findAll() throws ApplicationException {
		List<CreditLimit> creditLimits = creditLimitService.findAll();
		if (creditLimits.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
		return new ResponseEntity<List<CreditLimit>>(creditLimits, HttpStatus.OK);
	}
		
	@RequestMapping(value = "/new/save", method = RequestMethod.POST)
	public ResponseEntity<?> save(@Valid @RequestBody CreditLimit creditLimit, UriComponentsBuilder uriComponentsBuilder) throws ApplicationException {
		creditLimitService.validate(creditLimit);
		CreditLimit db = creditLimitService.save(creditLimit);		
		HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponentsBuilder.path("/creditLimit/{id}").buildAndExpand(db.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getOne(@PathVariable("id") Long id) {
		CreditLimit creditLimit = creditLimitService.getOne(id);
        if (creditLimit == null) {
            return new ResponseEntity(new ApplicationException("Não foi possível encontrar nenhum limite de crédito com código " + id + "."), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<CreditLimit>(creditLimit, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/edit/save/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> update(@PathVariable("id") Long id, @Valid @RequestBody CreditLimit creditLimit, UriComponentsBuilder uriComponentsBuilder) throws ApplicationException {
		CreditLimit current = creditLimitService.getOne(id);
 
        if (current == null) {
            return new ResponseEntity(new ApplicationException("Não foi possível atualizar nenhum limite de crédito com código " + id + "."), HttpStatus.NOT_FOUND);
        }
 
        current.setCustomerName(creditLimit.getCustomerName());        
        current.setCreditLimitAmount(creditLimit.getCreditLimitAmount());
        current.setRisk(creditLimit.getRisk());
 
        creditLimitService.update(current);
        return new ResponseEntity<CreditLimit>(current, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable("id") Long id) throws ApplicationException {
		CreditLimit creditLimit = creditLimitService.getOne(id);
        if (creditLimit == null) {
            return new ResponseEntity(new ApplicationException("Não foi possível deletar nenhum limite de crédito com código " + id + "."), HttpStatus.NOT_FOUND);
        }
        creditLimitService.delete(id);
        return new ResponseEntity<CreditLimit>(HttpStatus.NO_CONTENT);
	}
	
}
