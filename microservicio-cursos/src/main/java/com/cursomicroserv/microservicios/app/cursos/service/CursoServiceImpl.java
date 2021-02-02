package com.cursomicroserv.microservicios.app.cursos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cursomicroserv.microservicios.app.cursos.clients.AlumnoFeignClient;
import com.cursomicroserv.microservicios.app.cursos.clients.RespuestaFeignClient;
import com.cursomicroserv.microservicios.app.cursos.models.entity.Curso;
import com.cursomicroserv.microservicios.app.cursos.repository.CursoRepository;
import com.microservicio.commons.alumnos.models.entity.Alumno;
import com.microservicio.commons.services.CommonServiceImpl;

@Service
public class CursoServiceImpl extends CommonServiceImpl<Curso, CursoRepository> implements CursoService {

	@Autowired
	private RespuestaFeignClient client;
	
	@Autowired
	private AlumnoFeignClient clientAlumno;
	
	@Override
	@Transactional
	public Curso findCursoByAlumnoId(Long id) {
		return repository.findCursoByAlumnoId(id);
	}

	@Override
	public Iterable<Long> obtenerExamenesIdsConRespuestasAlumno(Long alumnoId) {
		return client.obtenerExamenesIdsConRespuestasAlumno(alumnoId);
	}

	@Override
	public Iterable<Alumno> obtenerAlumnosXCurso(List<Long> ids) {
		return clientAlumno.obtenerAlumnosXCurso(ids);
	}

	@Override
	@Transactional
	public void eliminarCursoAlumnoXId(Long id) {
		repository.eliminarCursoAlumnoXId(id);
	}

}
