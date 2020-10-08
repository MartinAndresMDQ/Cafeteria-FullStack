package com.cafeteria.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "supervisor", uniqueConstraints = { @UniqueConstraint(columnNames = "id") })
public class Supervisor extends Operator {

	public Supervisor() {
		super();
	}

	public Supervisor(@JsonProperty("id") int id, @JsonProperty("name") String name,
			@JsonProperty("online") Boolean online, @JsonProperty("password") String password) {
		super(id, name, online, password);
	}

}
