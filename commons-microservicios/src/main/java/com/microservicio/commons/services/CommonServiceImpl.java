package com.microservicio.commons.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public class CommonServiceImpl<E, R extends CrudRepository<E, Long>> implements CommonService<E> {
	
	@Autowired
	protected R repository;
	
	@Override
	@Transactional
	public Iterable<E> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<E> findById(Long id) {
		Optional<E> entityOptional = repository.findById(id);
		return entityOptional;
	}
	
	@Override
	@Transactional
	public E save(E entity) {
		return repository.save(entity);
	}

	@Override
	@Transactional
	public E update(Long id, E entity) {
		Optional<E> entityOptional = repository.findById(id);
		E entityUpdate = entityOptional.get();
		entityUpdate = repository.save(entity);
		return entityUpdate;
	}

	@Override
	@Transactional
	public boolean deleteById(Long id) throws Exception{
		try {
			if(repository.existsById(id)) {
				repository.deleteById(id);
				return true;
			} else {
				throw new Exception();
			}
		} catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}


	
	
}
