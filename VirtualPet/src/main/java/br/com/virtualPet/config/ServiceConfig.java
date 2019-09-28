package br.com.virtualPet.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import br.com.virtualPet.service.CadastroAnimalService;

@Configuration
@ComponentScan(basePackageClasses = CadastroAnimalService.class)
public class ServiceConfig {

}
