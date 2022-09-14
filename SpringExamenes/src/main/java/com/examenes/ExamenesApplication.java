package com.examenes;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ExamenesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExamenesApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		//Se agrega este converter para que elimine los saltos de línea y espacios en blanco que vienen de la DB
		modelMapper.addConverter(new Converter<String, String>() {
			@Override
			public String convert(MappingContext<String, String> mappingContext) {
				return mappingContext.getSource()!=null?mappingContext.getSource().replace('\n', ' ').trim():mappingContext.getSource();
			}
		});

		return modelMapper;
	}

}
