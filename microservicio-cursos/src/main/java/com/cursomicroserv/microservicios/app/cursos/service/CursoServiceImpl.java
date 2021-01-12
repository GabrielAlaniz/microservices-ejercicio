package com.cursomicroserv.microservicios.app.cursos.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cursomicroserv.microservicios.app.cursos.models.entity.Curso;
import com.cursomicroserv.microservicios.app.cursos.repository.CursoRepository;
import com.microservicio.commons.services.CommonServiceImpl;

@Service
public class CursoServiceImpl extends CommonServiceImpl<Curso, CursoRepository> implements CursoService {

	@Override
	@Transactional
	public Curso findCursoByAlumnoId(Long id) {
		return repository.findCursoByAlumnoId(id);
	}

}
