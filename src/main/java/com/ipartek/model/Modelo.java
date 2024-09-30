package com.ipartek.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "modelos")
public class Modelo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "modelo")
	private String modelo;

	public Modelo(int id, String modelo) {
		super();
		this.id = id;
		this.modelo = modelo;
	}

	public Modelo() {
		super();
		this.id = 0;
		this.modelo = "";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	@Override
	public String toString() {
		return "Modelo [id=" + id + ", modelo=" + modelo + "]";
	}

}
