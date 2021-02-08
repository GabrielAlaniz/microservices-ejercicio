package com.cursomicroserv.microservicios.app.respuestas.service;

//import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.cursomicroserv.microservicios.app.respuestas.clients.ExamenFeignClient;
import com.cursomicroserv.microservicios.app.respuestas.models.entity.Respuesta;
import com.cursomicroserv.microservicios.app.respuestas.repository.RespuestaRepository;
//import com.cursomicroserv.microservicios.commons.examenes.models.entity.Examen;
//import com.cursomicroserv.microservicios.commons.examenes.models.entity.Pregunta;

@Service
public class RespuestaServiceImpl implements RespuestaService {
	
	@Autowired
	private RespuestaRepository repository;
	
//	@Autowired
//	private ExamenFeignClient examenClient;
	
	@Override
	public List<Respuesta> saveAll(List<Respuesta> respuestas) {
		return repository.saveAll(respuestas);
	}

	@Override
	public List<Respuesta> findRespuestaByAlumnoByExamen(Long alumnoId, Long examenId) {
//		COMUNICACION ENTRE MICROSERVICIOS CON API REST:
//		Examen examen = examenClient.obtenerExamenXId(examenId);
//		List<Pregunta> preguntas = examen.getPreguntas();
//		List<Long> preguntaIds = preguntas.stream().map(p -> p.getId()).collect(Collectors.toList());
//		List<Respuesta> respuestas = (List<Respuesta>) repository.findRespuestaByAlumnoByPreguntaIds(alumnoId, preguntaIds);
//		respuestas = respuestas.stream().map(r -> {
//			preguntas.forEach(p -> {
//				if(p.getId() == r.getPreguntaId()) {
//					r.setPregunta(p);
//				}
//			});
//			return r;
//		}).collect(Collectors.toList());

		List<Respuesta> respuestas = (List<Respuesta>) repository.findRespuestaByAlumnoByExamen(alumnoId, examenId);
		return respuestas;
	}

	@Override
	public List<Long> findExamenesIdConRtaXAlumno(Long alumnoId) {
//		COMUNICACION ENTRE MICROSERVICIOS CON API REST:
//		List<Respuesta> respuestasAlumno = (List<Respuesta>) repository.findByAlumnoId(alumnoId);
//		List<Long> examenIds = Collections.emptyList();
//		if(respuestasAlumno.size() > 0) {
//			List<Long> preguntaIds = respuestasAlumno.stream().map(r -> r.getPreguntaId()).collect(Collectors.toList());
//			examenIds = examenClient.obtenerExamenesIdsPorPreguntasIdRespondidas(preguntaIds);
//		}
		
		List<Respuesta> respuestasAlumno = (List<Respuesta>) repository.findExamenesIdConRtaXAlumno(alumnoId);
		List<Long> examenIds = respuestasAlumno.stream().map(r -> r.getPregunta().getExamen().getId())
				.distinct().collect(Collectors.toList());
		
		return examenIds;
	}

	@Override
	public List<Respuesta> findByAlumnoId(Long alumnoId) {
		return repository.findByAlumnoId(alumnoId);
	}
	
}
