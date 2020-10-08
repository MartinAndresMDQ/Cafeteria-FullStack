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

import com.cafeteria.model.Client;
import com.cafeteria.model.Manager;
import com.cafeteria.model.Operator;
import com.cafeteria.model.Supervisor;
import com.cafeteria.service.ClientService;

@RestController
@RequestMapping(value = "/client")
public class ClientControlador {

	@Autowired
	private ClientService service;

	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> get(@PathVariable("id") int id) {
		HttpHeaders headers = new HttpHeaders();
		Client dato;
		try {
			dato = service.get(id);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), headers, HttpStatus.CONFLICT);
		}

		return new ResponseEntity<Object>(dato, headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/getSupport", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getSupport() {
		HttpHeaders headers = new HttpHeaders();
		Client client = null;
		try {
			Supervisor supervisor = null;
			Manager manager = null;

			Operator operator = service.getOperatorAvailable();
			if (operator == null)
				supervisor = service.getSupervisorAvailable();
			else
				client = operator;

			if (supervisor == null)
				manager = service.getManagerAvailable();
			else
				client = supervisor;

			if (supervisor == null)
				client = manager;

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), headers, HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Object>(client, headers, HttpStatus.OK);

	}

	@RequestMapping(value = "/getAlls", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getAlls() {
		HttpHeaders headers = new HttpHeaders();
		List<Client> dato = new ArrayList<Client>();
		try {
			dato = service.getAlls();
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), headers, HttpStatus.CONFLICT);
		}

		return new ResponseEntity<Object>(dato, headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> Save(@RequestBody Client dato) {
		HttpHeaders headers = new HttpHeaders();

		try {

			dato = service.saveOrUpdate(dato);

		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), headers, HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Object>(dato, headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/saveOperator", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> saveOperator(@RequestBody Operator dato) {
		HttpHeaders headers = new HttpHeaders();

		try {
			System.out.println(dato.toString());
			dato = service.saveOrUpdateOperator(dato);

		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), headers, HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Object>(dato, headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/saveSupervisor", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> saveSupervisor(@RequestBody Supervisor dato) {
		HttpHeaders headers = new HttpHeaders();

		try {

			dato = service.saveOrUpdateSupervisor(dato);

		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), headers, HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Object>(dato, headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/saveManager", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> saveManager(@RequestBody Manager dato) {
		HttpHeaders headers = new HttpHeaders();

		try {

			dato = service.saveOrUpdateManager(dato);

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

	@RequestMapping(value = "/loginClient", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> loginClient(@RequestBody Client user) {
		HttpHeaders headers = new HttpHeaders();
		// String nana="true";
		try {
			// service.Borrar(id);
			System.out.println(user.toString());
			Client client = service.getUser(user.getName());
			if (client == null) {
				client = service.saveOrUpdate(new Client(0, user.getName(), true));
			}
			System.out.println(client.toString());
			return new ResponseEntity<Object>(client, headers, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> login(@RequestBody Operator user) {
		HttpHeaders headers = new HttpHeaders();
		// String nana="true";
		Client client = null;
		try {
			// service.Borrar(id);
			System.out.println(user.toString());

			Supervisor supervisor = null;
			Manager manager = null;

//			Operator operator = service.getOperatorAvailable();
//			if (operator == null)
//				supervisor = service.getSupervisorAvailable();
//			else
//				client = operator;
//
//			if (supervisor == null)
//				manager = service.getManagerAvailable();
//			else
//				client = supervisor;
//
//			if (supervisor == null)
//				client = manager;

//			Client client = service.getUser(user.getName());
			return new ResponseEntity<Object>(client, headers, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
