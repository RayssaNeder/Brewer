package com.algaworks.brewer.controller.validator;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.algaworks.brewer.model.Venda;

@Component
public class VendaValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Venda.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors erros) {
		ValidationUtils.rejectIfEmpty(erros, "cliente.codigo", "", "Selecione um cliente na pesquisa rápida");
	
		Venda venda = (Venda) target;
		
		validaSeInformouApenasHorarioDeEntrega(erros, venda);
		validarSeInformouPeloMenosUmaCerveja(erros, venda);
		
		validaarValorTotalNegativo(erros, venda);
	
	}

	private void validaarValorTotalNegativo(Errors erros, Venda venda) {
		if(venda.getValorTotal().compareTo(BigDecimal.ZERO) < 0) {
			erros.reject("", "Valor total não pode ser negativo");
		}
	}

	private void validarSeInformouPeloMenosUmaCerveja(Errors erros, Venda venda) {
		if(venda.getItensVenda().isEmpty()) {
			erros.reject("", "Adicione pelo menos uma cerveja na venda");
		}
	}

	private void validaSeInformouApenasHorarioDeEntrega(Errors erros, Venda venda) {
		if(venda.getHoraEntrega() != null && venda.getDataEntrega() == null) {
			erros.rejectValue("dataEntrega","", "Informe uma data de entrega para um horário");
		}
	}

}
