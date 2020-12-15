package com.cursomicroserv.microservicios.app.cursos.controller;

import org.springframework.web.bind.annotation.RestController;

import com.cursomicroserv.microservicios.app.cursos.models.entity.Curso;
import com.cursomicroserv.microservicios.app.cursos.service.CursoService;
import com.microservicio.commons.controllers.CommonController;

@RestController
public class CursoController extends CommonController<Curso, CursoService> {

}
