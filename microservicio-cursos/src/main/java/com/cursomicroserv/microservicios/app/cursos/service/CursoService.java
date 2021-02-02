package com.cursomicroserv.microservicios.app.cursos.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import com.cursomicroserv.microservicios.app.cursos.models.entity.Curso;
import com.microservicio.commons.alumnos.models.entity.Alumno;
import com.microservicio.commons.services.CommonService;

public interface CursoService extends CommonService<Curso> {
	
	public Curso findCursoByAlumnoId(Long id);
	
	public Iterable<Long> obtenerExamenesIdsConRespuestasAlumno(Long alumnoId);
	
	public Iterable<Alumno> obtenerAlumnosXCurso(List<Long> ids);
	
	public void eliminarCursoAlumnoXId(Long id);
}
