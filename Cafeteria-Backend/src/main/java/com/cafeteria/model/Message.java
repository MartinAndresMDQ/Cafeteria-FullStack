package com.cafeteria.model;

import java.text.ParseException;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "message")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("id")
	private int id;

	@Column(name = "fecha", columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonProperty("fecha")
	private Date fecha;

	@Column(name = "message")
	@JsonProperty("message")
	private String message;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonProperty("envia")
	@JoinColumn(name = "envia_id", nullable = false)
	private Client envia;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonProperty("recibe")
	@JoinColumn(name = "recibe_id", nullable = false)
	private Client recibe;

	@Column(name = "visto")
	@JsonProperty("visto")
	private Boolean visto;

	public Message() {
		super();
	}

	public Message(@JsonProperty("id") int id, @JsonProperty("fecha") Date fecha,
			@JsonProperty("mensaje") String message, @JsonProperty("envia") Client envia,
			@JsonProperty("recibe") Client recibe, @JsonProperty("visto") Boolean visto) throws ParseException {
		super();
		this.id = id;
		this.fecha = fecha;
		this.message = message;
		this.envia = envia;
		this.recibe = recibe;
		this.visto = visto;
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
	public Date getFecha() {
		return fecha;
	}

	@JsonIgnore
	public String getMessage() {
		return message;
	}

	@JsonIgnore
	public void setMessage(String message) {
		this.message = message;
	}

	@JsonIgnore
	public Client getEnvia() {
		return envia;
	}

	@JsonIgnore
	public void setEnvia(Client envia) {
		this.envia = envia;
	}

	@JsonIgnore
	public Client getRecibe() {
		return recibe;
	}

	@JsonIgnore
	public void setRecibe(Client recibe) {
		this.recibe = recibe;
	}

	@JsonIgnore
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@JsonIgnore
	public Boolean getVisto() {
		return visto;
	}

	@JsonIgnore
	public void setVisto(Boolean visto) {
		this.visto = visto;
	}
}