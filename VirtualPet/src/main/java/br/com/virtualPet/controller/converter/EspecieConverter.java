package br.com.virtualPet.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import br.com.virtualPet.modelo.Especie;

public class EspecieConverter implements Converter<String, Especie>{

	@Override
	public Especie convert(String source) {
		if(!StringUtils.isEmpty(source)) {
			Especie especie = new Especie();
			especie.setCodigo(Long.parseLong(source));
			return especie;
		}
		return null;
	}

}
