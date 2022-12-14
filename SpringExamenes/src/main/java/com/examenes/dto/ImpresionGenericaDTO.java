package com.examenes.dto;

import java.util.List;
import java.util.Map;

public class ImpresionGenericaDTO {

    private    String archivoMaqueta;
    private	   String archivoFinal; 
    private	   String tipo;
    private	   Map<String, ParametroDTO> parametros; 
    private    List<Object>list;
    private    String classDTO;
	
    public     String getArchivoMaqueta() {
		return archivoMaqueta;
	}
	public void setArchivoMaqueta(String archivoMaqueta) {
		this.archivoMaqueta = archivoMaqueta;
	}
	public String getArchivoFinal() {
		return archivoFinal;
	}
	public void setArchivoFinal(String archivoFinal) {
		this.archivoFinal = archivoFinal;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<Object> getList() {
		return list;
	}
	public void setList(List<Object> list) {
		this.list = list;
	}
	public String getClassDTO() {
		return classDTO;
	}
	public void setClassDTO(String classDTO) {
		this.classDTO = classDTO;
	}
	public Map<String, ParametroDTO> getParametros() {
		return parametros;
	}
	public void setParametros(Map<String, ParametroDTO> parametros) {
		this.parametros = parametros;
	}

	
}
