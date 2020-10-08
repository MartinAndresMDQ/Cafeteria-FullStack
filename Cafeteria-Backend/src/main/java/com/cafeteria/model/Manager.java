package com.cafeteria.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "manager", uniqueConstraints = { @UniqueConstraint(columnNames = "id") })
public class Manager extends Supervisor {

	public Manager() {
		super();
	}

	public Manager(@JsonProperty("id") int id, @JsonProperty("name") String name,
			@JsonProperty("online") Boolean online, @JsonProperty("available") Boolean available,
			@JsonProperty("password") String password) {
		super(id, name, online, available, password);
	}

}
