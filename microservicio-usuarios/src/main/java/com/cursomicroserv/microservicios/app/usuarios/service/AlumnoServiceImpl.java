package com.cursomicroserv.microservicios.app.usuarios.service;

import org.springframework.stereotype.Service;

import com.cursomicroserv.microservicios.app.usuarios.models.entity.Alumno;
import com.cursomicroserv.microservicios.app.usuarios.repository.AlumnoRepository;
import com.microservicio.commons.services.CommonServiceImpl;

@Service
public class AlumnoServiceImpl extends CommonServiceImpl<Alumno, AlumnoRepository> implements AlumnoService {
		
}
