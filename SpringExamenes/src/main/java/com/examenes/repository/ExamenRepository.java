package com.examenes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.examenes.model.Examen;
@Repository
public interface ExamenRepository extends PagingAndSortingRepository<Examen, Long>{

	@Query("select e from Examen e where e.nombre like %?1%")
	public List<Examen> findByNombre(String term);
	
	@Query("select e.id from Pregunta p join p.examen e where p.id in ?1 group by e.id")
	public Iterable<Long> findExamenesIdsConRespuestasByPreguntaIds(Iterable<Long> preguntaIds);
}