package com.cafeteria.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "operator", uniqueConstraints = { @UniqueConstraint(columnNames = "id") })
public class Operator extends Client {

	@Column(name = "password")
	@JsonProperty("password")
	private String password;

	public Operator() {
		super();
	}

	public Operator(@JsonProperty("id") int id, @JsonProperty("name") String name,
			@JsonProperty("online") Boolean online, @JsonProperty("password") String password) {
		super(id, name, online);
		this.password = password;
	}

	@Override
	public String toString() {
		return "Operator [password=" + password + ", getId()=" + getId() + ", getName()=" + getName() + "]";
	}

}
