package com.atos.fittrack.services;

import java.util.*;

public interface ICRUD <T,E>{
	List<T> findAll();
	void delete(E id);
	Optional<T> findById(E id);
	T save(T entity);
}
