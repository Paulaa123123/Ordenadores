package com.ipartek.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ordenadores")
public class Ordenador {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "numeroSerie")
	private int numeroSerie;

	@ManyToOne
	private Modelo modelo;

	@ManyToOne
	private Marca marca;

	@Column(name = "anotaciones")
	private String anotaciones;

	@Column(name = "capacidad")
	private int capacidad;

	@Column(name = "ram")
	private int ram;

	public Ordenador(int id, int numeroSerie, Modelo modelo, Marca marca, String anotaciones, int capacidad, int ram) {
		super();
		this.id = id;
		this.numeroSerie = numeroSerie;
		this.modelo = modelo;
		this.marca = marca;
		this.anotaciones = anotaciones;
		this.capacidad = capacidad;
		this.ram = ram;
	}

	public Ordenador() {
		super();
		this.id = 0;
		this.numeroSerie = 0;
		this.modelo = new Modelo();
		this.marca = new Marca();
		this.anotaciones = "";
		this.capacidad = 0;
		this.ram = 0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumeroSerie() {
		return numeroSerie;
	}

	public void setNumeroSerie(int numeroSerie) {
		this.numeroSerie = numeroSerie;
	}

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public String getAnotaciones() {
		return anotaciones;
	}

	public void setAnotaciones(String anotaciones) {
		this.anotaciones = anotaciones;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public int getRam() {
		return ram;
	}

	public void setRam(int ram) {
		this.ram = ram;
	}

	@Override
	public String toString() {
		return "Ordenador [id=" + id + ", numeroSerie=" + numeroSerie + ", modelo=" + modelo + ", marca=" + marca
				+ ", anotaciones=" + anotaciones + ", capacidad=" + capacidad + ", ram=" + ram + "]";
	}

}
