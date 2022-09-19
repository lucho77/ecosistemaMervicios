package com.examenes.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Predicate;

/**
 * Aspecto para capturar los llamados REST a los endpoints definidos 
 * en los controllers y que tengan alguna annotation del package
 * org.springframework.web.bind.annotation, cuyo nombre arranque
 * con cualquier nombre y terminen con Mapping, por ejemplo @GetMapping,
 * @PutMapping, @DeleteMapping, @PatchMapping.
 * El fin de este es poder inyectar lo que retornen los endpoint
 * dentro de un ResponseEntity y que todas las respuestas por 
 * el camino feliz tengan un Status Code 200.
 * Esta hecho con un @Around 
 * @author daniel
 *
 */
@Aspect
@Component
public class SuccessfulResponseEntityAspect {

    protected final Logger logger= LoggerFactory.getLogger(getClass());

    @Around("execution(@org.springframework.web.bind.annotation.*Mapping * *(..))")
    public ResponseEntity<Object> buildSuccessfulResponseEntity(ProceedingJoinPoint jp) throws Throwable {
    	
    	Object returnedValue = jp.proceed();

        return Optional.of(returnedValue)
                .filter(isOfTypeResponseEntiy().negate())
                .map(p -> ResponseEntity.status(HttpStatus.OK).body(p))
                .orElseGet(() -> (ResponseEntity) returnedValue);
    }

    private Predicate<Object> isOfTypeResponseEntiy() {
        return ResponseEntity.class::isInstance;
    }
}