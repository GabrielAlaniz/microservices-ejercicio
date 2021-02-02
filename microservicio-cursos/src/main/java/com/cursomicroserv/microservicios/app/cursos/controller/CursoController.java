package com.cursomicroserv.microservicios.app.cursos.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cursomicroserv.microservicios.app.cursos.models.entity.Curso;
import com.cursomicroserv.microservicios.app.cursos.models.entity.CursoAlumno;
import com.cursomicroserv.microservicios.app.cursos.service.CursoService;
import com.cursomicroserv.microservicios.commons.examenes.models.entity.Examen;
import com.microservicio.commons.alumnos.models.entity.Alumno;
import com.microservicio.commons.controllers.CommonController;

@RestController
public class CursoController extends CommonController<Curso, CursoService> {

	@Value("${config.balanceador.test}")
	private String balanceadorTest;
	
	@DeleteMapping("/eliminar-alumno/{id}")
	public ResponseEntity<?> eliminarCursoAlumnoXId(@PathVariable Long id) {
		service.eliminarCursoAlumnoXId(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping
	@Override
	public ResponseEntity<?> getAll(){
		List<Curso> cursos = ((List<Curso>) service.findAll()).stream().map(c ->{
			c.getCursoAlumnos().forEach(ca ->{
				//por cada curso, recorro los alumnos y creo un objeto por c/u de ellos
				Alumno alumno = new Alumno();
				alumno.setId(ca.getAlumnoId());
				c.addAlumno(alumno); //lleno con obj alumno la coleccion del curso
			});
			return c; //retorno el curso modificado
		}).collect(Collectors.toList());
		return ResponseEntity.ok().body(cursos);
	}
	
	@GetMapping("/paginable")
	@Override
	public ResponseEntity<?> getAll(Pageable pageable){
		Page<Curso> cursos = service.findAll(pageable).map(curso ->{
			curso.getCursoAlumnos().forEach(ca ->{
				Alumno alumno = new Alumno();
				alumno.setId(ca.getAlumnoId());
				curso.addAlumno(alumno);
			});
			return curso;
		});
		return ResponseEntity.ok().body(cursos);
	}
	
	@GetMapping("/{id}")
	@Override
	public ResponseEntity<?> getOne(@PathVariable Long id){
		Optional<Curso> o = service.findById(id);
		if(!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Curso curso = o.get();
		if(curso.getCursoAlumnos().isEmpty() == false) {
			List<Long> ids = curso.getCursoAlumnos().stream().map(ca -> ca.getAlumnoId()).collect(Collectors.toList());
			List<Alumno> alumnos = (List<Alumno>) service.obtenerAlumnosXCurso(ids);
			curso.setAlumnos(alumnos);
		}
		return ResponseEntity.ok(curso);
	}
	
	
	
	@GetMapping("/balanceador-test")
	public ResponseEntity<?> balanceadorTest() {
		Map<String, Object> response = new HashMap<>();
		response.put("balanceador", balanceadorTest);
		response.put("cursos", service.findAll());
		return ResponseEntity.ok(response);
	}

	@PutMapping("/{id})")
	public ResponseEntity<?> editar(@Valid @RequestBody Curso curso, BindingResult result, @PathVariable Long id){
		Optional<Curso> o = this.service.findById(id);
		if(!o.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		Curso dbCurso = o.get();
		dbCurso.setNombre(curso.getNombre());
		return ResponseEntity.status(HttpStatus.OK).body(service.save(dbCurso));
	}
	
	@PutMapping("/{id}/asignar-alumnos")
	public ResponseEntity<?> asignarAlumnos(@RequestBody List<Alumno> alumnos, @PathVariable Long id){
		Optional<Curso> o = this.service.findById(id);
		if(!o.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		Curso dbCurso = o.get();
		
		alumnos.forEach(a -> {
			CursoAlumno cursoAlumno = new CursoAlumno();
			cursoAlumno.setAlumnoId(a.getId());
			cursoAlumno.setCurso(dbCurso);
			dbCurso.addCursoAlumno(cursoAlumno);			
		});
		return ResponseEntity.status(HttpStatus.OK).body(service.save(dbCurso));
	}
	
	@PutMapping("/{id}/eliminar-alumno")
	public ResponseEntity<?> eliminarAlumno(@RequestBody Alumno alumno, @PathVariable Long id){
		Optional<Curso> o = this.service.findById(id);
		if(!o.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		Curso dbCurso = o.get();
		CursoAlumno cursoAlumno = new CursoAlumno();
		cursoAlumno.setAlumnoId(alumno.getId());
		dbCurso.removeCursoAlumno(cursoAlumno);			
		
		return ResponseEntity.status(HttpStatus.OK).body(service.save(dbCurso));
	}
	
	@GetMapping("/alumno/{id}")
	public ResponseEntity<?> buscarAlumnoPorId(@PathVariable Long id){
		Curso curso = service.findCursoByAlumnoId(id);
		
		if(curso != null) {
			List<Long> examenesIds = (List<Long>) service.obtenerExamenesIdsConRespuestasAlumno(id);
			
			List<Examen> examenes = curso.getExamenes().stream().map(examen -> {
				if(examenesIds.contains(examen.getId())) {
					examen.setRespondido(true);
				}
				return examen;
			}).collect(Collectors.toList());
			
			curso.setExamenes(examenes);
		}
		
		return ResponseEntity.ok(curso);
	}
	
	@PutMapping("/{id}/asignar-examenes")
	public ResponseEntity<?> asignarExamenes(@RequestBody List<Examen> examenes, @PathVariable Long id){
		Optional<Curso> o = this.service.findById(id);
		if(!o.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		Curso dbCurso = o.get();
		
		examenes.forEach(e -> {
			dbCurso.addExamen(e);			
		});
		return ResponseEntity.status(HttpStatus.OK).body(service.save(dbCurso));
	}
	
	@PutMapping("/{id}/eliminar-examen")
	public ResponseEntity<?> eliminarExamen(@RequestBody Examen examen, @PathVariable Long id){
		Optional<Curso> o = this.service.findById(id);
		if(!o.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		Curso dbCurso = o.get();
		
		dbCurso.removeExamen(examen);			
		
		return ResponseEntity.status(HttpStatus.OK).body(service.save(dbCurso));
	}
}
