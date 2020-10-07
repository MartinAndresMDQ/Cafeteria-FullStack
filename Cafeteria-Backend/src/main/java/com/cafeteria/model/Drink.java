package com.cafeteria.model;

import java.math.BigDecimal;

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

//@Entity
//@Table(name = "drink", uniqueConstraints = { @UniqueConstraint(columnNames = "id") })
//@Inheritance(strategy = InheritanceType.JOINED)

@Entity
@Table(name = "drink", uniqueConstraints = { @UniqueConstraint(columnNames = "id") })
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE", discriminatorType = DiscriminatorType.STRING)
//@JsonDeserialize(using = DrinkDeSerializer.class, as = Drink.class)
public class Drink {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	@JsonProperty("id")
	private int id;

	@Column(name = "name")
	@JsonProperty("name")
	private String name;

	@Column(name = "price")
	@JsonProperty("price")
	private BigDecimal price;

	@Column(name = "hidden", columnDefinition = "tinyint default false")
	@JsonProperty("hidden")
	private Boolean hidden;

	@Column(name = "DTYPE", insertable = false, updatable = false)
	@JsonProperty("DTYPE")
	private String DTYPE;

	public Drink() {
		super();
	}

	public Drink(@JsonProperty("id") int id, @JsonProperty("name") String name, @JsonProperty("price") BigDecimal price,
			@JsonProperty("hidden") Boolean hidden) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.hidden = hidden;
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
	public BigDecimal getPrice() {
		return price;
	}

	@JsonIgnore
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@JsonIgnore
	public Boolean getHidden() {
		return hidden;
	}

	@JsonIgnore
	public void setHidden(Boolean hidden) {
		this.hidden = hidden;
	}

	@Override
	public String toString() {
		return "Drink [id=" + id + ", name=" + name + ", price=" + price + "]";
	}
}
