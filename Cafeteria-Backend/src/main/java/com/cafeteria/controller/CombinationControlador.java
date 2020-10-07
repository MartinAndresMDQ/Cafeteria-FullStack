package com.cafeteria.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cafeteria.model.Combination;
import com.cafeteria.service.CombinationService;

@RestController
@RequestMapping(value = "/combination")
public class CombinationControlador {

	@Autowired
	private CombinationService service;

	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> get(@PathVariable("id") int id) {
		HttpHeaders headers = new HttpHeaders();
		Combination dato = new Combination();
		try {
			dato = service.get(id);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), headers, HttpStatus.CONFLICT);
		}

		return new ResponseEntity<Object>(dato, headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/getAlls", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getAlls() {
		HttpHeaders headers = new HttpHeaders();
		List<Combination> dato = new ArrayList<Combination>();
		try {
			dato = service.getAlls();
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), headers, HttpStatus.CONFLICT);
		}

		return new ResponseEntity<Object>(dato, headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> Save(@RequestBody Combination dato) {
		HttpHeaders headers = new HttpHeaders();

		try {
			dato = service.saveOrUpdate(dato);

		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), headers, HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Object>(dato, headers, HttpStatus.OK);
	}

//	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
//	public ResponseEntity<Object> delete(@PathVariable("id") int id) {
//		HttpHeaders headers = new HttpHeaders();
//		try {
//			service.delete(id);
//			return new ResponseEntity<Object>(1, headers, HttpStatus.OK);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return new ResponseEntity<Object>(e.getMessage(), headers, HttpStatus.CONFLICT);
//		}
//	}

}
