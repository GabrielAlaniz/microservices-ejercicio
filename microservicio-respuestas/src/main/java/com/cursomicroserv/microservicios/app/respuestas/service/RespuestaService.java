package com.cursomicroserv.microservicios.app.respuestas.service;

import java.util.List;

import com.cursomicroserv.microservicios.app.respuestas.models.entity.Respuesta;

public interface RespuestaService {
	
	public List<Respuesta> saveAll(List<Respuesta> respuestas);
	
	public List<Respuesta> findRespuestaByAlumnoByExamen(Long alumnoId, Long examenId);
	
	public List<Long> findExamenesIdConRtaXAlumno(Long alumnoId);
	
	public List<Respuesta> findByAlumnoId(Long alumnoId);
}
