package com.ipartek.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ipartek.auxiliares.Auxiliares;
import com.ipartek.repository.MarcaRepository;
import com.ipartek.repository.ModeloRepository;
import com.ipartek.repository.OrdenadorRepository;
import com.ipartek.repository.RolRepository;
import com.ipartek.repository.UsuarioRepository;

@Controller
public class SeguridadController {

	@Autowired
	private MarcaRepository marcaRepo;

	@Autowired
	private ModeloRepository modeloRepo;

	@Autowired
	private OrdenadorRepository ordenadorRepo;

	@Autowired
	private UsuarioRepository usuarioRepo;

	@Autowired
	private RolRepository rolRepo;

	@RequestMapping("/mantenimiento")
	public String menuCopiaSeguridad(Model model) {

		System.out.println("MENU COPIA");

		return "seguridad";
	}

	@RequestMapping("/crearCopia")
	public String crearCopiaSeguridad(Model model) {

		String nombresArchivos[] = { "modelos.csv", "marcas.csv", "ordenadores.csv", "usuarios.csv", "roles.csv" };

		List<Object>[] lista = new ArrayList[5];
		for (int i = 0; i < lista.length; i++) {
			lista[i] = new ArrayList<>();
		}

		lista[0].addAll(modeloRepo.findAll());
		lista[1].addAll(marcaRepo.findAll());
		lista[2].addAll(ordenadorRepo.findAll());
		lista[3].addAll(usuarioRepo.findAll());
		lista[4].addAll(rolRepo.findAll());

		for (int i = 0; i < nombresArchivos.length; i++) {
			Auxiliares.crearCSV(nombresArchivos[i], lista[i]);
		}

		return "seguridad";
	}

	@RequestMapping("/restaurarCopia")
	public String restaurarCopiaSeguridad(Model model) {
		String nombresArchivos[] = { "modelos.csv", "marcas.csv", "ordenadores.csv", "usuarios.csv", "roles.csv" };
		Map repositorioMap = new HashMap<>();

		repositorioMap.put("modelos.csv", new ArrayList<>());
		repositorioMap.put("marcas.csv", new ArrayList<>());
		repositorioMap.put("ordenadores.csv", new ArrayList<>());
		repositorioMap.put("usuarios.csv", new ArrayList<>());
		repositorioMap.put("roles.csv", new ArrayList<>());

		for (String nombreArchivo : nombresArchivos) {
			List lista = Auxiliares.leerCSV("" + nombreArchivo);
			List repositorio = (List) repositorioMap.get(nombreArchivo);
			if (repositorio != null) {
				repositorio.addAll(lista);
			}
		}

		System.out.println("RESTAURA");

		return "seguridad";
	}

}
