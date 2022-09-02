package com.lucho.ms.repository;

import com.lucho.ms.model.Alumno;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
@Repository
public interface AlumnoRepository extends CrudRepository<Alumno, Long> {

}
	