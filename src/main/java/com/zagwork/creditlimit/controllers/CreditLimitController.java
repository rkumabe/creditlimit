package com.zagwork.creditlimit.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
		
	@RequestMapping(value = "/new/save", produces = { MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
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
	
}
