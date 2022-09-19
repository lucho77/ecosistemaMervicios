package com.lucho.ms.service;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import com.lucho.ms.exception.AlumnoException;
import com.lucho.ms.model.Alumno;
import com.lucho.ms.repository.AlumnoRepository;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
@Singleton
public class AlumnoService {

	@Inject
	AlumnoRepository alumnoRepository;
	
	public List<Alumno> getAlumnos(){
		return (List<Alumno>) alumnoRepository.findAll();
	}
	
    public Optional<Alumno> findById(@NotNull Long id) {
        return alumnoRepository.findById(id);
    }

    public Alumno save(@NotNull Alumno alumno) {
        return alumnoRepository.save(alumno);
    }

    public void deleteById(@NotNull Long id) {
        Optional<Alumno>alumnno = findById(id);       
        alumnoRepository.delete(alumnno.orElseThrow(()-> new AlumnoException("No se ha encontrado el alumno a eliminar")));
    }

    public Alumno update(@NotNull Long id, @NotNull Alumno alumno) {
        Optional<Alumno>a = findById(id);
        if(a.isPresent()) {
            Alumno alumnobd = a.get();
            alumnobd.setApellido(alumno.getApellido());
            alumnobd.setEmail(alumno.getEmail());
            alumnobd.setNombre(alumno.getNombre());
        	alumnoRepository.update(alumnobd);       
        	return alumnobd;
        }
        throw  new AlumnoException("No se ha encontrado el alumno a modificar");
        
    }
}
