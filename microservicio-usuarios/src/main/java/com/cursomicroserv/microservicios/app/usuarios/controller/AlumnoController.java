package com.cursomicroserv.microservicios.app.usuarios.controller;

import org.springframework.web.bind.annotation.RestController;

import com.cursomicroserv.microservicios.app.usuarios.models.entity.Alumno;
import com.cursomicroserv.microservicios.app.usuarios.service.AlumnoService;
import com.microservicio.commons.controllers.CommonController;

@RestController
public class AlumnoController extends CommonController<Alumno, AlumnoService> {
	
}
