package com.examenes.service;

import java.util.List;

import com.examenes.model.Asignatura;
import com.examenes.model.Examen;

public interface ExamenService extends CommonService<Examen> {
	public List<Examen> findByNombre(String term);
	
	public Iterable<Asignatura> findAllAsignaturas();
	
	public Iterable<Long> findExamenesIdsConRespuestasByPreguntaIds(Iterable<Long> preguntaIds);
}

