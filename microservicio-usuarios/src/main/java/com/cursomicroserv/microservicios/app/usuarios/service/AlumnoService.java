package com.cursomicroserv.microservicios.app.usuarios.service;

import java.util.List;

import com.microservicio.commons.alumnos.models.entity.Alumno;
import com.microservicio.commons.services.CommonService;

public interface AlumnoService extends CommonService<Alumno> {
	public List<Alumno> findByNombreOrApellido(String term);
	public Alumno update (Long id, Alumno alumno);
	public List<Alumno> findAllById(List<Long> ids);
	public void eliminarCursoAlumnoXId(Long id);
}
