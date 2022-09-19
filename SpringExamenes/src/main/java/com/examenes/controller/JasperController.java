package com.examenes.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examenes.dto.ImpresionGenericaDTO;
import com.examenes.service.ReportService;

@RestController
@RequestMapping(value = "/jasper")

public class JasperController {

@Autowired
private ReportService reportService;
   protected final Logger log = LoggerFactory.getLogger(getClass());
	
	@PostMapping("/post")
	public ResponseEntity<?> ejecutarJasper(@RequestBody ImpresionGenericaDTO dataJasper){

		log.info("entro al ejecutar jasper");
		reportService.geterateReport( dataJasper);
		
		return ResponseEntity.status(HttpStatus.OK).body("jasper prueba");
	}

}
