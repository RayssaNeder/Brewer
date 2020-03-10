Brewer = Brewer || {};
	
Brewer.PesquisaRapidaCliente = (function(){
	
	function PesquisaRapidaCliente(){
		this.pesquisaRapidalientesModal  = $('#modalPesquisaRapidaCliente');
		this.nomeInput = $('#nomeClienteModal');
		this.pesquisaRapidaClientesBtn =  $('.js-pesquisa-rapida-clientes-btn');
		this.containerTabelaPesquisa = $('#containerTabelaPesquisaRapidaClientes');
		this.htmlTablePesquisa =  $('#tebela-pesquisa-rapida-cliente').html();
		this.template = Handlebars.compile(this.htmlTablePesquisa);
		this.mensagemErro = $('.js-mensagem-erro');
	}
	
	PesquisaRapidaCliente.prototype.iniciar = function(){
		this.pesquisaRapidalientesModal.on('shown.bs.modal', onModalShow.bind(this)); 
		this.pesquisaRapidaClientesBtn.on('click', onPesquisaRapidaClicado.bind(this));
		
		function onModalShow(){
			this.nomeInput.focus();
		}
		
		function onPesquisaRapidaClicado(event){
			event.preventDefault();
			
			$.ajax({
				url: this.pesquisaRapidalientesModal.find('form').attr('action'),
				method: 'GET',
				contentType: 'application/json',
				data: {
					nome: this.nomeInput.val()
				},
				success: onPesquisaConcluida.bind(this),
				error: onErroPesquisa.bind(this)
			});
		}
	}
	
	function onPesquisaConcluida(resultado){
		var html =  this.template(resultado);
		this.containerTabelaPesquisa.html(html);
		this.mensagemErro.addClass('hidden');
		
		var tabelaClientePesquisaRapida = new Brewer.TabelaClientePesquisaRapida(this.pesquisaRapidalientesModal);
		tabelaClientePesquisaRapida.iniciar();
	}
	
	function onErroPesquisa(){
		this.mensagemErro.removeClass('hidden');
	}
	
	
	return PesquisaRapidaCliente;
	
}());

Brewer.TabelaClientePesquisaRapida = (function(){
	
	function TabelaClientePesquisaRapida(modal){
		this.modalCliente = modal;
		this.cliente = $('.js-cliente-pesquisa-rapida');
	}
	
	TabelaClientePesquisaRapida.prototype.iniciar = function() {
		this.cliente.on('click', onClienteSelecionado.bind(this));
	}
	
	function onClienteSelecionado(evento){
		var clienteSelecionado = $(evento.currentTarget);
		this.modalCliente.modal('hide');
		$('#nomeCliente').val(clienteSelecionado.data('nome'));
		$('#codigoCliente').val(clienteSelecionado.data('codigo'));
		
		
	}
	
	return TabelaClientePesquisaRapida;
}());

$(function(){
	var pesquisaRapidaCliente = new Brewer.PesquisaRapidaCliente();
	pesquisaRapidaCliente.iniciar();
	
});