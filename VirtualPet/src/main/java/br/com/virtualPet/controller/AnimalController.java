package br.com.virtualPet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/animais")
public class AnimalController {
	
	
	@RequestMapping("novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView("animal/CadastroAnimal");
		return mv;
		
	}
	
	@GetMapping
	public ModelAndView pesquisar() {
		ModelAndView mv = new ModelAndView("animal/PesquisaAnimais");
		return mv;
		
	}

}
