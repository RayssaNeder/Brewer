package br.com.virtualPet.controller;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import br.com.virtualPet.modelo.Especie;
import br.com.virtualPet.service.CadastroEspecieService;
import br.com.virtualPet.service.exception.NomeJaCadastradoException;

@Controller
@RequestMapping("/especies")
public class EspeciesController {
	
	@Autowired
	private CadastroEspecieService cadastroEspecieService;
	
	@RequestMapping("/novo")
	public ModelAndView novo(Especie especie) {
		ModelAndView mv = new ModelAndView("especie/CadastroEspecie");
		return mv;
		
	}
	
	@RequestMapping(value= "/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Especie especie, BindingResult result, Model model, RedirectAttributes attributes) {
		
		if(result.hasErrors()) {
			return novo(especie);
		}
		
		
		try {
			cadastroEspecieService.salvar(especie);
		}catch (NomeJaCadastradoException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return novo(especie);
		}
		attributes.addFlashAttribute("mensagem", "Espécie cadastrada com sucesso");
		
		return new ModelAndView("redirect:/especies/novo");
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes= {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody ResponseEntity<?> salvar(@RequestBody @Valid Especie especie, BindingResult result ) {
		if(result.hasErrors()) {
			return ResponseEntity.badRequest().body(result.getFieldError("nome").getDefaultMessage());
		}		
		especie = cadastroEspecieService.salvar(especie);
		return ResponseEntity.ok(especie);
	}

}
