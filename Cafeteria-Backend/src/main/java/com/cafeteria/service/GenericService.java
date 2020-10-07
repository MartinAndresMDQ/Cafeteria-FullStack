package com.cafeteria.service;

import java.util.List;

public interface GenericService<T> {

	// void Guardar(T dato) throws Exception;

	void delete(int id) throws Exception;

	T get(int id) throws Exception;

	List<T> getAlls() throws Exception;

	int cant();

	T getLast();

	T getFirst();

	T saveOrUpdate(T dato) throws Exception;

}
