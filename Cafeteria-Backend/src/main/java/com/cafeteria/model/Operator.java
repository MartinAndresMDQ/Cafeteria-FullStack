package com.cafeteria.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "operator", uniqueConstraints = { @UniqueConstraint(columnNames = "id") })
public class Operator extends Client {

	@Column(name = "password")
	@JsonProperty("password")
	private String password;

	@Column(name = "available", columnDefinition = "tinyint default false")
	@JsonProperty("available")
	private Boolean available;

	public Operator() {
		super();
	}

	public Operator(@JsonProperty("id") int id, @JsonProperty("name") String name,
			@JsonProperty("online") Boolean online, @JsonProperty("available") Boolean available,
			@JsonProperty("password") String password) {
		super(id, name, online);
		this.available = available;
		this.password = password;
	}

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	@JsonIgnore
	public void setPassword(String password) {
		this.password = password;
	}

	@JsonIgnore
	public Boolean getAvailable() {
		return available;
	}

	@JsonIgnore
	public void setAvailable(Boolean available) {
		this.available = available;
	}

	@Override
	public String toString() {
		return "Operator [password=" + password + ", getId()=" + getId() + ", getName()=" + getName() + "]";
	}

}
