package com.examenes.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.examenes.model.Asignatura;
@Repository
public interface AsignaturaRepository extends CrudRepository<Asignatura, Long>{

}
