package com.algaworks.brewer.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.algaworks.brewer.model.Estilo;
import com.algaworks.brewer.service.CadastroEstiloService;
import com.algaworks.brewer.service.exception.NomeJaCadastradoException;

@Controller
public class EstilosController {
	
	
	@Autowired
	private CadastroEstiloService cadastroEstiloService;

	@RequestMapping("/estilos/novo")
	public ModelAndView novo(Estilo estilo) {
		ModelAndView mv = new ModelAndView("estilo/CadastroEstilo");
		return mv;
	}
	
	@RequestMapping(value= "/estilos/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Estilo estilo, BindingResult result, Model model, RedirectAttributes atributes) {
		
		if(result.hasErrors()) {
			return novo(estilo);
		}
		
		
		//salva no banco de dados
		
		try {
			cadastroEstiloService.salvar(estilo);
		}catch (NomeJaCadastradoException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return novo(estilo);
		}
		atributes.addFlashAttribute("mensagem", "Estilo cadastrado com sucesso");
		
		return new ModelAndView("redirect:/estilos/novo");
	}
	
}
