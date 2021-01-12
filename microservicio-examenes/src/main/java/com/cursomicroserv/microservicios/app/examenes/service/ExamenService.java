package com.cursomicroserv.microservicios.app.examenes.service;

import java.util.List;

import com.cursomicroserv.microservicios.commons.examenes.models.entity.Asignatura;
import com.cursomicroserv.microservicios.commons.examenes.models.entity.Examen;
import com.microservicio.commons.services.CommonService;

public interface ExamenService extends CommonService<Examen> {
	
	public List<Examen> findByNombre(String term);
	
	public List<Asignatura> findAllAsignaturas();
}
