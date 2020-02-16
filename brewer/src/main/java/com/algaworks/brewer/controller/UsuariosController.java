package com.algaworks.brewer.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.algaworks.brewer.model.Cerveja;
import com.algaworks.brewer.model.Origem;
import com.algaworks.brewer.model.Sabor;
import com.algaworks.brewer.model.Usuario;
import com.algaworks.brewer.repository.Estilos;
import com.algaworks.brewer.repository.Grupos;
import com.algaworks.brewer.repository.Usuarios;
import com.algaworks.brewer.service.CadastroUsuarioService;
import com.algaworks.brewer.service.exception.NomeJaCadastradoException;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {
	
	@Autowired
	private Usuarios usuarios;
	
	@Autowired
	private Grupos grupos;

	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;
	
	@RequestMapping("novo")
	public ModelAndView novo(Usuario usuario) {
		ModelAndView mv = new ModelAndView("usuario/CadastroUsuario");
				
		mv.addObject("grupos", grupos.findAll());
		
		return mv;
	}
	
	@PostMapping("novo")
	public ModelAndView cadastrar(@Valid Usuario usuario, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			return novo(usuario);
		}
		
		try {
		cadastroUsuarioService.salvar(usuario);
		}catch (NomeJaCadastradoException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return novo(usuario);
		}
		attributes.addFlashAttribute("mensagem", "Usuario salvo com sucesso");
		
		return new ModelAndView("redirect:/usuarios/novo");
	}
	
}
