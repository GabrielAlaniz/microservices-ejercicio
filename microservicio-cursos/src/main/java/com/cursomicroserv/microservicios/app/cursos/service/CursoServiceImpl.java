package com.cursomicroserv.microservicios.app.cursos.service;

import org.springframework.stereotype.Service;

import com.cursomicroserv.microservicios.app.cursos.models.entity.Curso;
import com.cursomicroserv.microservicios.app.cursos.repository.CursoRepository;
import com.microservicio.commons.services.CommonServiceImpl;

@Service
public class CursoServiceImpl extends CommonServiceImpl<Curso, CursoRepository> implements CursoService {

}
