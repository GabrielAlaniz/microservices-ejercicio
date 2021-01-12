package com.cursomicroserv.microservicios.app.usuarios.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cursomicroserv.microservicios.app.usuarios.repository.AlumnoRepository;
import com.microservicio.commons.alumnos.models.entity.Alumno;
import com.microservicio.commons.services.CommonServiceImpl;

@Service
public class AlumnoServiceImpl extends CommonServiceImpl<Alumno, AlumnoRepository> implements AlumnoService {

	@Override
	@Transactional
	public List<Alumno> findByNombreOrApellido(String term) {
		return repository.findByNombreOrApellido(term);
	}

	@Override
	@Transactional
	public Alumno update(Long id, Alumno alumno) {
		return null;
	}
	
}
