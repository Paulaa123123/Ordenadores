package com.ipartek.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.model.Marca;
import com.ipartek.model.Modelo;
import com.ipartek.repository.MarcaRepository;
import com.ipartek.repository.ModeloRepository;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;

@Service
public class InicializarDatos {

	@Autowired
	private ModeloRepository modeloRepo;

	@Autowired
	private MarcaRepository marcaRepo;

//	@PostConstruct
//	@Transactional
//	public void inicializarDatos() {
//		//lista de generos
//		modeloRepo.save(new Modelo(1, "dfsdfsdf"));
//		modeloRepo.save(new Modelo(2, "sfsfs"));
//		modeloRepo.save(new Modelo(3, "Infasfsfsfntil"));
//		
//		//equipos en las aulas
//		marcaRepo.save(new Marca(1, "Samsung"));
//		marcaRepo.save(new Marca(2, "Apple"));
//		marcaRepo.save(new Marca(3, "Oppo"));
//		marcaRepo.save(new Marca(4, "Xiaomi"));
//		
//		
//		
//		
//	
//	}

}
