package com.cursomicroserv.microservicios.app.usuarios.repository;

import org.springframework.data.repository.CrudRepository;

import com.microservicio.commons.alumnos.models.entity.Alumno;

public interface AlumnoRepository extends CrudRepository<Alumno, Long> {

}
