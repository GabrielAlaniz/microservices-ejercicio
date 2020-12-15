package com.cursomicroserv.microservicios.app.cursos.repository;

import org.springframework.data.repository.CrudRepository;

import com.cursomicroserv.microservicios.app.cursos.models.entity.Curso;

public interface CursoRepository extends CrudRepository<Curso, Long> {

}
