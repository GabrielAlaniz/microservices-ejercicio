package com.cursomicroserv.microservicios.app.usuarios.service;

import java.util.Optional;

import com.cursomicroserv.microservicios.app.usuarios.models.entity.Alumno;

public interface AlumnoService {
	public Iterable<Alumno> findAll();
	public Optional<Alumno> findById(Long id);
	public Alumno save(Alumno alumno);
	public Alumno update(Long id, Alumno alumno);
	public boolean deleteById(Long id) throws Exception;
}
