package com.cursomicroserv.microservicios.app.usuarios.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cursomicroserv.microservicios.app.usuarios.models.entity.Alumno;
import com.cursomicroserv.microservicios.app.usuarios.repository.AlumnoRepository;

@Service
public class AlumnoServiceImpl implements AlumnoService {
	
	@Autowired
	protected AlumnoRepository repository;
	
	@Override
	@Transactional
	public Iterable<Alumno> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<Alumno> findById(Long id) {
		Optional<Alumno> entityOptional = repository.findById(id);
		return entityOptional;
	}
	
	@Override
	@Transactional
	public Alumno save(Alumno alumno) {
		return repository.save(alumno);
	}

	@Override
	@Transactional
	public Alumno update(Long id, Alumno alumno) {
		Optional<Alumno> entityOptional = repository.findById(id);
		Alumno entityUpdate = entityOptional.get();
		entityUpdate = repository.save(alumno);
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
