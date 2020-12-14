package com.cursomicroserv.microservicios.app.usuarios.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cursomicroserv.microservicios.app.usuarios.models.entity.Alumno;
import com.cursomicroserv.microservicios.app.usuarios.service.AlumnoService;

@RestController
public class AlumnoController {
	
	@Autowired
	protected AlumnoService service;
	
	@GetMapping
	public ResponseEntity<?> getAll(){
		return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getOne(@PathVariable Long id){
		return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
	}
	
	@PostMapping("")
	public ResponseEntity<?> save(@RequestBody Alumno alumno){
		return ResponseEntity.status(HttpStatus.OK).body(service.save(alumno));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Alumno alumno){
		return ResponseEntity.status(HttpStatus.OK).body(service.update(id, alumno));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(service);
	}
	
}
