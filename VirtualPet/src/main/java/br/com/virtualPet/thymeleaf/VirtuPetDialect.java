package br.com.virtualPet.thymeleaf;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

import br.com.virtualPet.thymeleaf.processor.MessageElementTagProcessor;

import br.com.virtualPet.thymeleaf.processor.ClassForErrorAttributeTagProcessor;

public class VirtuPetDialect extends AbstractProcessorDialect {

	public VirtuPetDialect() {
		super("VirtualPet", "virtuPet", StandardDialect.PROCESSOR_PRECEDENCE);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public Set<IProcessor> getProcessors(String dialectPrefix) {
		final Set<IProcessor> processadores = new HashSet<>();
		processadores.add(new ClassForErrorAttributeTagProcessor(dialectPrefix));
		processadores.add(new MessageElementTagProcessor(dialectPrefix));
		return processadores;
	}

	

}
