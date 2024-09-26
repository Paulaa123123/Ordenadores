package com.ipartek.auxiliares;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Auxiliares {

	public static void crearCSV(String nombreArchivo, List<Object> lista) {
		try {
			Path ruta = Paths.get("src/main/backups/", nombreArchivo);
			BufferedWriter escritor = new BufferedWriter(new FileWriter(ruta.toString()));
			for (Object obj : lista) {

				escritor.write(obj.toString());
				escritor.newLine();

				System.out.println("El archivo " + nombreArchivo + " se ha creado correctamente.");
			}
			escritor.close();

		} catch (IOException e) {
			System.out.println("Ocurrió un error al crear el archivo.");
			e.printStackTrace();
		}
	}

	public static List leerCSV(String nombreArchivo) {
		List lista = new ArrayList<>();
		Path ruta = Paths.get("src/main/backups/", nombreArchivo);

		try (BufferedReader lector = new BufferedReader(new FileReader(ruta.toString()))) {
			String linea;
			while ((linea = lector.readLine()) != null) {
				lista.add(linea);
			}
			System.out.println("El archivo " + nombreArchivo + " se ha leído correctamente.");
		} catch (IOException e) {
			System.out.println("Ocurrió un error al leer el archivo.");
			e.printStackTrace();
		}

		return lista;
	}
}
