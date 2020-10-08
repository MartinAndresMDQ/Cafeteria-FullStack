package com.cafeteria.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

//@Entity
//@Table(name = "message")
//@JsonIgnoreProperties(ignoreUnknown = true)
public class Message {

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@JsonProperty("id")
//	private int id;

//	@Column(name = "fecha", columnDefinition = "DATETIME")
//	@Temporal(TemporalType.TIMESTAMP)
	@JsonProperty("date")
	private Date date;

//	@Column(name = "message")
	@JsonProperty("message")
	private String message;

//	@ManyToOne(fetch = FetchType.LAZY)
	@JsonProperty("fromId")
//	@JoinColumn(name = "envia_id", nullable = false)
	private String fromId;

//	@ManyToOne(fetch = FetchType.LAZY)
	@JsonProperty("toId")
//	@JoinColumn(name = "recibe_id", nullable = false)
	private String toId;

//	@Column(name = "visto")
//	@JsonProperty("visto")
//	private Boolean visto;

	public Message() {
		super();
	}

	public Message(@JsonProperty("date") String message, @JsonProperty("fromId") String fromId,
			@JsonProperty("toId") String toId, @JsonProperty("date") Date date) {
		super();
		this.date = date;
		this.message = message;
		this.fromId = fromId;
		this.toId = toId;
	}

	@JsonIgnore
	public Date getDate() {
		return date;
	}

	@JsonIgnore
	public void setDate(Date date) {
		this.date = date;
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
	public String getFromId() {
		return fromId;
	}

	@JsonIgnore
	public void setFromId(String fromId) {
		this.fromId = fromId;
	}

	@JsonIgnore
	public String getToId() {
		return toId;
	}

	@JsonIgnore
	public void setToId(String toId) {
		this.toId = toId;
	}

	@Override
	public String toString() {
		return "Message [date=" + date + ", message=" + message + ", fromId=" + fromId + ", toId=" + toId + "]";
	}

}