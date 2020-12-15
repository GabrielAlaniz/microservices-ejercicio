package com.microservicio.commons.services;

import java.util.Optional;

public interface CommonService<E> {
	public Iterable<E> findAll();
	public Optional<E> findById(Long id);
	public E save(E entity);
	public E update(Long id, E entity);
	public boolean deleteById(Long id) throws Exception;
}
