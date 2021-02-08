package com.cursomicroserv.microservicios.app.examenes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cursomicroserv.microservicios.app.examenes.models.repository.AsignaturaRepository;
import com.cursomicroserv.microservicios.app.examenes.models.repository.ExamenRepository;
import com.cursomicroserv.microservicios.commons.examenes.models.entity.Asignatura;
import com.cursomicroserv.microservicios.commons.examenes.models.entity.Examen;
import com.microservicio.commons.services.CommonServiceImpl;

@Service
public class ExamenServiceImpl extends CommonServiceImpl<Examen, ExamenRepository> implements ExamenService {
	
	@Autowired
	private AsignaturaRepository asignaturaRepository;
	
	@Override
	@Transactional
	public List<Examen> findByNombre(String term) {
		return repository.findByNombre(term);
	}

	@Override
	@Transactional
	public List<Asignatura> findAllAsignaturas() {
		return (List<Asignatura>) asignaturaRepository.findAll();
	}

	@Override
	@Transactional
	public List<Long> findExamenesIdConRtaByPreguntaIds(List<Long> preguntaIds) {
		return repository.findExamenesIdConRtaByPreguntaIds(preguntaIds);
	}
	
}
