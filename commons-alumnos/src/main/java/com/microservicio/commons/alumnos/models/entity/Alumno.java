package com.microservicio.commons.alumnos.models.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="alumnos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Alumno {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	@Column(name="nombre")
	private String nombre;
	
	@NotEmpty
	@Column(name="apellido")
	private String apellido;
	
	@NotEmpty
	@Email
	@Column(name="email")
	private String email;
	@Column(name="create_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createAt;
	
	@PrePersist
	public void prePersist() {
		this.createAt = new Date();
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			// verifica que el objeto del list y el alumno sean iguales
			return true;
		}
		if(!(obj instanceof Alumno)) {
			//verifica que el objeto sea de tipo Alumno
			return false;
		}
		// casteo del obj a tipo alumno para evaluar id
		Alumno a = (Alumno) obj;
		
		// si el id no es null y es igual al id del alumno, devuelve true
		return this.id != null && this.id.equals(a.getId());
	}
	
}
