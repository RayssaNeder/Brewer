var Brewer = Brewer || {};

Brewer.MaskCpfCnpj = (function(){
	function MaskCpfCnpj(){
		
		this.radioTipoPessoa = $('.js-radio-tipo-pessoa');
		this.labelcpfOuCnpj = $('[for=cpfOuCnpj]');
		this.inputCpfCnpj = $('#cpfOuCnpj');
	}
	
	MaskCpfCnpj.prototype.iniciar = function(){
		this.radioTipoPessoa.on('change', onTipoAlterado.bind(this));
	}
	
	
	function onTipoAlterado(event){
		var tipoPessoaSelecionada  = $(event.currentTarget);
		this.labelcpfOuCnpj.text(tipoPessoaSelecionada.data('documento'));
		this.inputCpfCnpj.mask(tipoPessoaSelecionada.data('mascara'));
		this.inputCpfCnpj.val('');
		this.inputCpfCnpj.removeAttr('disabled');
	}
	
		
		return MaskCpfCnpj;
}());


$(function(){
	var maskCpfCnpj = new Brewer.MaskCpfCnpj();
	maskCpfCnpj.iniciar();
	
});