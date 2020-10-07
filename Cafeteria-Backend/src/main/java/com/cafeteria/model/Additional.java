package com.cafeteria.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonProperty;

//@Entity
//@Table(name = "additional", uniqueConstraints = { @UniqueConstraint(columnNames = "id") })

@Entity
@Table(name = "additional", uniqueConstraints = { @UniqueConstraint(columnNames = "id") })
//@JsonDeserialize(as = Additional.class)
public class Additional extends Drink {

//	@ManyToMany(mappedBy = "additionals")
//	@JsonIgnore
//	private Set<Order> orders = new HashSet<Order>();

	public Additional() {
		super();
	}

	public Additional(@JsonProperty("id") int id, @JsonProperty("name") String name,
			@JsonProperty("price") BigDecimal price, @JsonProperty("hidden") Boolean hidden) {
		super(id, name, price, hidden);
	}

}
