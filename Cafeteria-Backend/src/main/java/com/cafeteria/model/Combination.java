package com.cafeteria.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "combination", uniqueConstraints = { @UniqueConstraint(columnNames = "id") })
public class Combination {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	@JsonProperty("id")
	private int id;

	@Column(name = "name")
	@JsonProperty("name")
	private String name;

	@JsonProperty("drink")
//	@OneToOne(cascade = { CascadeType.PERSIST }, fetch = FetchType.EAGER)
	@ManyToOne(fetch = FetchType.EAGER)
	private Drink drink;

	@Column(name = "fecha", columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonProperty("fecha")
	private Date fecha;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "additional_order", joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "additional_id"))
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonProperty("additionals")
	private Set<Additional> additionals = new HashSet<Additional>();

	public Combination() {
		super();
	}

	public Combination(@JsonProperty("id") int id, @JsonProperty("name") String name,
			@JsonProperty("drink") Drink drink, @JsonProperty("fecha") Date fecha,
			@JsonProperty("additionals") Set<Additional> additionals) {
		super();
		this.id = id;
		this.name = name;
		this.drink = drink;
		this.fecha = fecha;
		this.additionals = additionals;
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
	public Drink getDrink() {
		return drink;
	}

	@JsonIgnore
	public void setDrink(Drink drink) {
		this.drink = drink;
	}

	@JsonIgnore
	public Date getFecha() {
		return fecha;
	}

	@JsonIgnore
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@JsonIgnore
	public Set<Additional> getAdditionals() {
		return additionals;
	}

	@JsonIgnore
	public void setAdditionals(Set<Additional> rubros) {
		this.additionals = rubros;
	}

}
