package com.cafeteria.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "client", uniqueConstraints = { @UniqueConstraint(columnNames = "id") })
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "DTYPE", discriminatorType = DiscriminatorType.STRING)
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	@JsonProperty("id")
	private int id;

	@Column(name = "name")
	@JsonProperty("name")
	private String name;

	@Column(name = "online", columnDefinition = "tinyint default false")
	@JsonProperty("online")
	private Boolean online;

	@Column(name = "DTYPE", insertable = false, updatable = false)
	@JsonProperty("DTYPE")
	private String DTYPE;

	public Client() {
		super();
	}

	public Client(@JsonProperty("id") int id, @JsonProperty("name") String name,
			@JsonProperty("online") Boolean online) {
		super();
		this.id = id;
		this.name = name;
		this.online = online;
	}

	@JsonIgnore
	public int getId() {
		return id;
	}

	@JsonIgnore
	public void setId(int id) {
		this.id = id;
	}

	@JsonIgnore
	public String getName() {
		return name;
	}

	@JsonIgnore
	public void setName(String name) {
		this.name = name;
	}

	@JsonIgnore
	public Boolean getOnline() {
		return online;
	}

	@JsonIgnore
	public void setOnline(Boolean online) {
		this.online = online;
	}

	@JsonIgnore
	public String getDTYPE() {
		return DTYPE;
	}

	@JsonIgnore
	public void setDTYPE(String dTYPE) {
		DTYPE = dTYPE;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + ", online=" + online + ", DTYPE=" + DTYPE + "]";
	}

}
