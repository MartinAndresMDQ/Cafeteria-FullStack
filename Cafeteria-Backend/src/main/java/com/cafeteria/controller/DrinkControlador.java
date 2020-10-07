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

import com.cafeteria.model.Drink;
import com.cafeteria.service.DrinkService;

@RestController
@RequestMapping(value = "/drink")
public class DrinkControlador {

	@Autowired
	private DrinkService service;

	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> get(@PathVariable("id") int id) {
		HttpHeaders headers = new HttpHeaders();
		Drink dato = new Drink();
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
		List<Drink> dato = new ArrayList<Drink>();
		try {
			dato = service.getAlls();
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), headers, HttpStatus.CONFLICT);
		}

		return new ResponseEntity<Object>(dato, headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> Save(@RequestBody Drink dato) {
		HttpHeaders headers = new HttpHeaders();

		try {

			if (dato.getId() == 0)
				dato = service.saveOrUpdate(dato);
			else {
				dato.setHidden(true);
				dato = service.saveOrUpdate(dato);
				Drink nuevo = new Drink(0, dato.getName(), dato.getPrice(), false);
				dato = service.saveOrUpdate(nuevo);

			}

		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), headers, HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Object>(dato, headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> delete(@PathVariable("id") int id) {
		HttpHeaders headers = new HttpHeaders();
		try {
			service.delete(id);
			return new ResponseEntity<Object>(1, headers, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), headers, HttpStatus.CONFLICT);
		}
	}

}
