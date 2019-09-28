package br.com.virtualPet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.virtualPet.modelo.Animal;
import br.com.virtualPet.modelo.Especie;
import br.com.virtualPet.modelo.Raca;
import br.com.virtualPet.modelo.Sexo;
import br.com.virtualPet.modelo.Status;
import br.com.virtualPet.repository.Animais;
import br.com.virtualPet.repository.Especies;
import br.com.virtualPet.repository.Racas;
import br.com.virtualPet.repository.filter.AnimalFilter;

@Controller
@RequestMapping("/animais")
public class AnimalController {
	
	@Autowired
	private Animais animais;
	
	@Autowired
	private Especies especies;
	
	@Autowired
	private Racas racas;
	
	
	@RequestMapping("novo")
	public ModelAndView novo(Animal animal) {
		ModelAndView mv = new ModelAndView("animal/CadastroAnimal");
		mv.addObject("status", Status.values());
		mv.addObject("especies",especies.findAll());
		mv.addObject("racas", racas.findAll());
		mv.addObject("sexo", Sexo.values());
		return mv;
		
	}
	
	@GetMapping
	public ModelAndView pesquisar(AnimalFilter animalFilter, BindingResult result, @PageableDefault(size = 4) Pageable pageable) {
		ModelAndView mv = new ModelAndView("animal/PesquisaAnimais");
		Page<Animal> pagina = animais.filtrar(animalFilter, pageable); 
		mv.addObject("pagina", pagina);
		mv.addObject("especies",especies.findAll());
		mv.addObject("racas", racas.findAll());
		mv.addObject("sexo", Sexo.values());
		return mv;
		
	}

}
