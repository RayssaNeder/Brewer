package com.algaworks.brewer.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.algaworks.brewer.model.Cerveja;
import com.algaworks.brewer.model.Origem;
import com.algaworks.brewer.model.Sabor;
import com.algaworks.brewer.model.Usuario;
import com.algaworks.brewer.repository.Estilos;
import com.algaworks.brewer.repository.Usuarios;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {
	
	@Autowired
	private Usuarios usuarios;

	
	
	@RequestMapping("novo")
	public ModelAndView novo(Usuario usuario) {
		ModelAndView mv = new ModelAndView("usuario/CadastroUsuario");
				
		//mv.addObject("usuarios", usuarios.findAll());
		
		return mv;
	}
	
	@PostMapping("novo")
	public ModelAndView cadastrar(@Valid Usuario usuario, BindingResult result) {
		if(result.hasErrors()) {
			return novo(usuario);
		}
		
		//ssalvar
		return null;
	}
	
}
