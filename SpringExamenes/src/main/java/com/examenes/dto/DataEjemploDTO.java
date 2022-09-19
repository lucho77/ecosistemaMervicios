package com.examenes.dto;

import java.util.List;

public class DataEjemploDTO {

	private String name;
	private String apel;
	private String dir;
	private String tel;
	private List<String>redes;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getApel() {
		return apel;
	}
	public void setApel(String apel) {
		this.apel = apel;
	}
	public String getDir() {
		return dir;
	}
	public void setDir(String dir) {
		this.dir = dir;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public List<String> getRedes() {
		return redes;
	}
	public void setRedes(List<String> redes) {
		this.redes = redes;
	}
	
}
