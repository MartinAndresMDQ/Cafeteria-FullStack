package com.cafeteria.dao;

import java.util.List;

public interface GenericDAO<T> {

	T saveOrUpdate(T dato);

	void delete(T dato);

	T get(int id);

	List<T> getAlls();

	int cant();

	T getLast();

	T getFirst();

}