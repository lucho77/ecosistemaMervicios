package com.lucho.ms.controller;

import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.lucho.ms.model.Alumno;
import com.lucho.ms.service.AlumnoService;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.annotation.Put;
import jakarta.inject.Inject;

@Controller("/alumno")
public class AlumnoController {

	@Inject
	AlumnoService alumnoService;
	@Get("/all")
	@Produces(value = MediaType.APPLICATION_JSON)
	public HttpResponse<Object> getAlumnos(){
		return HttpResponse.status(HttpStatus.OK).body(alumnoService.getAlumnos());   		
	}
	

	@Produces(MediaType.APPLICATION_JSON)
    @Get("/show/{id}")
    public HttpResponse<?> show(@NotNull @PathVariable  Long  id) {
        Optional<Alumno>alumnno = alumnoService.findById(id);
        if(!alumnno.isPresent()) {
    		return HttpResponse.status(HttpStatus.NOT_FOUND);   		
        }
		return HttpResponse.status(HttpStatus.OK).body(alumnno.get());
    }

    @Put("/update/{id}")
    public HttpResponse update(@Body @Valid Alumno alumno,@NotNull @PathVariable Long  id) {
    	alumnoService.update(id, alumno);
        return HttpResponse
                .noContent();
    }

    @Post("/add")
    public HttpResponse<?> save(@Body @Valid Alumno alumno){
    	alumnoService.save(alumno);
        return HttpResponse.created(alumno);
    }

    @Delete("/delete/{id}")
    public HttpResponse delete( @PathVariable Long id) {
    	alumnoService.deleteById(id);
        return HttpResponse.noContent();
    }
}
