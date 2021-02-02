package com.cursomicroserv.microservicios.app.respuestas.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursomicroserv.microservicios.app.respuestas.models.entity.Respuesta;
import com.cursomicroserv.microservicios.app.respuestas.repository.RespuestaRepository;

@Service
public class RespuestaServiceImpl implements RespuestaService {
	
	@Autowired
	private RespuestaRepository repository;
	
	@Override
	@Transactional
	public Iterable<Respuesta> saveAll(Iterable<Respuesta> respuestas) {
		return repository.saveAll(respuestas);
	}

	@Override
	@Transactional
	public Iterable<Respuesta> findRespuestaByAlumnoByExamen(Long alumnoId, Long examenId) {
		return repository.findRespuestaByAlumnoByExamen(alumnoId, examenId);
	}

	@Override
	@Transactional
	public Iterable<Long> findExamenesIdConRtaXAlumno(Long alumnoId) {
		return repository.findExamenesIdConRtaXAlumno(alumnoId);
	}
	
}
