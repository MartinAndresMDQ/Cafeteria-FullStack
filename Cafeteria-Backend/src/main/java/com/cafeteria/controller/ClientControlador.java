package com.cafeteria.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cafeteria.model.Client;
import com.cafeteria.model.Manager;
import com.cafeteria.model.Message;
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

	@RequestMapping(value = "/getSupport/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getSupport(@PathVariable("id") int id) {
		HttpHeaders headers = new HttpHeaders();
		Operator client = null;
		try {
			client = service.getOperatorAvailable();
			if (client == null)
				client = service.getSupervisorAvailable();
			if (client == null)
				client = service.getManagerAvailable();
			if (client != null) {
				client.setAvailable(false);
				client = service.saveOrUpdateOperator(client);

				this.simpMessagingTemplate.convertAndSend("/socket-publisher/" + client.getId(),
						new Message("Se esta conectando con un Cliente", String.valueOf(id),
								String.valueOf(client.getId()), new Date()));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), headers, HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Object>(client, headers, HttpStatus.OK);

	}

	@RequestMapping(value = "/getClientsOnline", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getClientsOnline() {
		HttpHeaders headers = new HttpHeaders();
		List<Client> clients = new ArrayList<Client>();
		try {
			clients = service.getClientsOnline();

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), headers, HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Object>(clients, headers, HttpStatus.OK);

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

	@RequestMapping(value = "/loginClient", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> loginClient(@RequestBody Client user) {
		HttpHeaders headers = new HttpHeaders();
		// String nana="true";
		try {
			// service.Borrar(id);
			Client client = service.getUser(user.getName());
			if (client == null) {
				client = service.saveOrUpdate(new Client(0, user.getName(), true));
			} else {
				client.setOnline(true);
				client = service.saveOrUpdate(client);
			}
			return new ResponseEntity<Object>(client, headers, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/disconnect/{id}/{idR}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> disconnect(@PathVariable("id") int id, @PathVariable("idR") int idR) {
		HttpHeaders headers = new HttpHeaders();
		// String nana="true";
		try {
			Client client = service.get(id);

			if (client.getDTYPE().equals("Operator")) {
				client.setOnline(false);
				client = service.saveOrUpdateOperator((Operator) client);
			} else if (client.getDTYPE().equals("Supervisor")) {

				client.setOnline(false);
				client = service.saveOrUpdateSupervisor((Supervisor) client);
			} else if (client.getDTYPE().equals("Manager")) {

				client.setOnline(false);
				client = service.saveOrUpdateManager((Manager) client);
			} else if (client.getDTYPE().equals("Client")) {
				client = service.saveOrUpdate(client);
				if (idR != 0) {
					Operator sup = service.getOperator(idR);
					sup.setAvailable(true);
					service.saveOrUpdate(sup);

					this.simpMessagingTemplate.convertAndSend("/socket-publisher/" + idR,
							new Message("Se ha desconectado el Cliente", String.valueOf(client.getId()),
									String.valueOf(idR), new Date()));

				}
			}
			return new ResponseEntity<Object>(client, headers, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> login(@RequestBody Operator user) {
		HttpHeaders headers = new HttpHeaders();
		Operator client = null;
		try {

			client = (Operator) service.getSupport(user.getName(), user.getPassword());
			if (client != null) {
				client.setOnline(true);
				client.setAvailable(true);
				client = (Operator) service.saveOrUpdate((Client) client);

			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Object>(client, headers, HttpStatus.OK);
	}
}
