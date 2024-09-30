package com.ipartek.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ipartek.auxiliares.I_Logs;
import com.ipartek.auxiliares.Logs;
import com.ipartek.auxiliares.Mensajeria;
import com.ipartek.model.Ordenador;
import com.ipartek.model.Rol;
import com.ipartek.model.Usuario;
import com.ipartek.repository.MarcaRepository;
import com.ipartek.repository.ModeloRepository;
import com.ipartek.repository.OrdenadorRepository;
import com.ipartek.repository.UsuarioRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {

	@Autowired
	private ModeloRepository modeloRepo;

	@Autowired
	private MarcaRepository marcaRepo;

	@Autowired
	private OrdenadorRepository ordenadorRepo;

	@Autowired
	private UsuarioRepository usuarioRepo;

	@RequestMapping("/")
	public String cargarAdmin(Model model, HttpSession session) {

		model.addAttribute("atr_lista_marcas", marcaRepo.findAll());
		model.addAttribute("atr_lista_modelos", modeloRepo.findAll());
		model.addAttribute("atr_lista_ordenadores", ordenadorRepo.findAll());

		model.addAttribute("obj_ordenador", new Ordenador());

		return "index";
	}

	@RequestMapping("/adminFrmBorrarOrdenador")
	public String borrarOrdenadorAdmin(Model model, @RequestParam(value = "id", required = false) Integer id,
			HttpSession session) {

		Usuario usu = new Usuario();
		if (session.getAttribute("s_usuario") != null) {
			usu = (Usuario) (session.getAttribute("s_usuario"));
		}

		String ruta = "";
		switch (usu.getRol().getId()) {
		case 1:
			ruta = "redirect:/";
			break;
		case 2:
			ruta = "redirect:/";
			break;
		default:
			ruta = "redirect:https://www.google.es/";
			break;
		}

		Mensajeria.limpiarMensaje(session);

		Optional<Ordenador> ordenador = ordenadorRepo.findById(id);

		ordenadorRepo.deleteById(id);

		Mensajeria.registrarMensaje(session, 3);

		return ruta;
	}

	@RequestMapping("/login")
	public String loginAdmin(Model model, HttpSession session) {

		Mensajeria.limpiarMensaje(session);

		model.addAttribute("obj_usuario", new Usuario());
		if (session.getAttribute("s_intentos") == null) {
			session.setAttribute("s_intentos", 0);
		}

		return "login";
	}

	@RequestMapping("/revisarLogin")
	public String revisarLoginAdmin(Model model, @ModelAttribute("obj_usuario") Usuario usuario, HttpSession session) {

		Mensajeria.limpiarMensaje(session);

		Usuario usuValidado = usuarioRepo.validarUsuario(usuario.getUsuario(), usuario.getPass());

		if (usuValidado != null) {
			session.setAttribute("s_usuario", usuValidado);
			session.setAttribute("s_usuario_Log", usuValidado);
			// en caso que el login sea correcto, no hara falta mandar mensaje de error

			session.removeAttribute("s_intentos");// limpiamos el numero de intentos

			String ruta = "";
			switch (usuValidado.getRol().getId()) {
			case 1:
				ruta = "redirect:/";
				break;
			case 2:
				ruta = "redirect:/";
				break;

			default:
				ruta = "redirect:https://www.google.es/";
				break;
			}

			Logs.registrarLogAcceso(I_Logs.LOG_ACCESOS_ACCESO_CORRECTO, session);

			return ruta;
		} else {
			session.setAttribute("s_usuario_Log", usuario);
			Mensajeria.registrarMensaje(session, 5);

			session.setAttribute("s_intentos", (int) session.getAttribute("s_intentos") + 1);

			System.out.println("Intento" + (int) session.getAttribute("s_intentos"));

			if ((int) session.getAttribute("s_intentos") < 3) {

				Logs.registrarLogAcceso(I_Logs.LOG_ACCESOS_ACCESO_FALLIDO, session);
				return "login";
			} else if ((int) session.getAttribute("s_intentos") == 3) {
				Usuario usuBlock = usuarioRepo.validarUsuario(usuario.getUsuario());
				if (usuBlock != null) {
					usuBlock.setRol(new Rol(3, "baneado"));
					usuarioRepo.save(usuBlock);

					Logs.registrarLogAcceso(I_Logs.LOG_ACCESOS_ACCESO_BANEADO, session);
				}

				return "login";
			} else {

				Logs.registrarLogAcceso(I_Logs.LOG_ACCESOS_HONEYPOT, session);

				return "redirect:https://www.google.es/";
			}
		}
	}

	@RequestMapping("/cerrarSesion")
	public String cerrarSesionAdmin(Model model, HttpSession session) {

		Usuario usu = new Usuario();
		if (session.getAttribute("s_usuario") != null) {
			usu = (Usuario) (session.getAttribute("s_usuario"));
		}

		String ruta = "";
		switch (usu.getRol().getId()) {
		case 1:
			ruta = "redirect:/";
			break;
		case 2:
			ruta = "redirect:/";
			break;
		default:
			ruta = "redirect:https://www.google.es/";
			break;
		}

		session.setAttribute("s_usuario_Log", usu);
		Logs.registrarLogAcceso(I_Logs.LOG_ACCESOS_ACCESO_CIERRE_SESION, session);

		session.invalidate();

		return ruta;
	}

	@RequestMapping("/adminBorrarOrdenador")
	public String borrarOrdenador(Model model, @RequestParam(value = "id", required = false) Integer id) {
		ordenadorRepo.deleteById(id);
		return "redirect:/";
	}

	@RequestMapping("/adminFrmModificarOrdenador")
	public String frmModificarOrdenadorAdmin(Model model, @RequestParam(value = "id", required = false) Integer id,
			HttpSession session) {

		Usuario usu = new Usuario();
		if (session.getAttribute("s_usuario") != null) {
			usu = (Usuario) (session.getAttribute("s_usuario"));
		}

		String ruta = "";
		switch (usu.getRol().getId()) {
		case 1:
			ruta = "frm_modificar_ordenador";
			break;
		case 2:
			ruta = "frm_modificar_ordenador";
			break;
		default:
			ruta = "redirect:https://www.google.es/";
			break;
		}

		model.addAttribute("atr_lista_marcas", marcaRepo.findAll());
		model.addAttribute("atr_lista_modeloss", modeloRepo.findAll());

		Ordenador ordenador = new Ordenador();
		if (id != null) {
			ordenador = ordenadorRepo.findById(id).orElse(ordenador = new Ordenador());
		}

		model.addAttribute("obj_ordenador", ordenador);

		return ruta;
	}

	@RequestMapping("/adminModificarOrdenador")
	public String modificarOrdenadorAdmin(Model model, @ModelAttribute("obj_ordenador") Ordenador ordenador,
			HttpSession session) {

		Usuario usu = new Usuario();
		if (session.getAttribute("s_usuario") != null) {
			usu = (Usuario) (session.getAttribute("s_usuario"));
		}

		String ruta = "";
		switch (usu.getRol().getId()) {
		case 1:
			ruta = "redirect:/";
			break;
		case 2:
			ruta = "redirect:/";
			break;
		default:
			ruta = "redirect:https://www.google.es/";
			break;
		}

		Mensajeria.limpiarMensaje(session);

		ordenadorRepo.save(ordenador);

		Mensajeria.registrarMensaje(session, 4);

		return "redirect:/";
	}

	@RequestMapping("/adminGuardarOrdenador")
	public String guardarOrdenadorAdmin(Model model, @ModelAttribute("obj_ordenador") Ordenador ordenador,
			HttpSession session) {

//		Usuario usu = new Usuario();
//		if (session.getAttribute("s_usuario") != null) {
//			usu = (Usuario) (session.getAttribute("s_usuario"));
//		}
//
//		String ruta = "";
//		switch (usu.getRol().getId()) {
//		case 1:
//			ruta = "redirect:/";
//			break;
//		case 2:
//			ruta = "redirect:/";
//			break;
//		default:
//			return "redirect:https://www.google.es/";
//		}
//
//		Mensajeria.limpiarMensaje(session);

		ordenadorRepo.save(ordenador);

//		Mensajeria.registrarMensaje(session, 1);

		return "index";
	}

}
