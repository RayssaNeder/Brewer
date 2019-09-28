var VirtuPet = VirtuPet || {};

VirtuPet.EspecieCadastroRapido = (function(){
	
	function EspecieCadastroRapido(){
		this.modal = $('#modalCadastroRapidoEspecie'); 
		this.botaoSalvar = this.modal.find('.js-modal-cadastro-especie-salvar-btn');
		this.form = this.modal.find('form');
		this.inputNomeEspecie = $('#nomeEspecie');
		this.url = this.form.attr('action');
		this.containerMensagemErro = $('.js-mensagem-cadastro-rapido-especie');
	}
	
	EspecieCadastroRapido.prototype.iniciar = function(){
		this.form.on('submit', function(event) { event.preventDefault() });	
		this.modal.on('shown.bs.modal', onModalShow.bind(this)); 
		this.modal.on('hide.bs.modal', onModalClose.bind(this));
		this.botaoSalvar.on('click', onBotaoSalvarClick.bind(this));
	}
	
	function onModalShow(){
		this.inputNomeEspecie.focus();
	}
	
	function onModalClose(){
		this.inputNomeEspecie.val('');
		this.containerMensagemErro.addClass('hidden');
		this.form.find('.form-group').removeClass('has-error');
	}
	
	function onBotaoSalvarClick(){
		var nomeEspecie = this.inputNomeEspecie.val().trim();		
		$.ajax({
			url: this.url,
			method: 'POST',
			contentType: 'application/json',
			data: JSON.stringify({'nome': nomeEspecie }),
			error: onErroSalvandoEspecie.bind(this),
			success: onEspecieSalva.bind(this)
		});
	}
	
	
	function onErroSalvandoEspecie(obj){
		var mensagemErro = obj.responseText;
		this.containerMensagemErro.removeClass('hidden');
		this.containerMensagemErro.html('<span>' + mensagemErro + '</span>');	
		this.form.find('.form-group').addClass('has-error');		
	}
	 
	function onEspecieSalva(especie){
		var comboEspecie = $('#especie');
		comboEspecie.append('<option value =' + especie.codigo + '>' + especie.nome + '</option>');
		comboEspecie.val(especie.codigo);
		this.modal.modal('hide');
	}
	

	return EspecieCadastroRapido;
	
}());

$(function(){
	var especieCadastroRapido = new  VirtuPet.EspecieCadastroRapido();
	especieCadastroRapido.iniciar();
});