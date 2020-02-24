var Brewer = Brewer || {};

Brewer.MaskMoney = (function(){
	
	function MaskMoney(){
		this.decimal = $('.js-decimal');
		this.plain = $('.js-plain');		
	}
	
	MaskMoney.prototype.enable = function(){
		this.decimal.maskMoney({ decimal: ',', thousands: '.' });
		this.plain.maskMoney({ precision: 0, thousands: '.' });
		
	}
	
	return MaskMoney;
}()) ;

Brewer.MaskPhoneNumber = (function(){
	function MaskPhoneNumber(){
		this.inputPhoneNumber = $('.js-phone-number');
	}
	
	MaskPhoneNumber.prototype.enable = function(){
		var maskBehavior = function (val) {
			  return val.replace(/\D/g, '').length === 11 ? '(00) 00000-0000' : '(00) 0000-00009';
			}
				
			var options = {
			  onKeyPress: function(val, e, field, options) {
			      field.mask(maskBehavior.apply({}, arguments), options);
			    }
			};

			this.inputPhoneNumber.mask(maskBehavior, options)
	}
	
	return MaskPhoneNumber;
}());

Brewer.MaskCEPNumber = (function(){
	function MaskCEPNumber(){
		this.inputCEPNumber = $('.js-cep-number');
	}
	
	MaskCEPNumber.prototype.enable = function(){			
			this.inputCEPNumber.mask('00000-000');
	}
	
	return MaskCEPNumber;
}());

Brewer.MaskDate = (function(){
	function MaskDate(){
		this.inputMaskDate = $('.js-mask-date');
	}
	
	MaskDate.prototype.enable = function(){			
			this.inputMaskDate.mask('00/00/0000');
			this.inputMaskDate.datepicker({
				orientation: 'bottom',
				language:'pt-BR',
				autoclose:true
			});
			
	}
	return  MaskDate;
}());

Brewer.Security = (function(){
	function Security(){
		this.token = $('input[name=_csrf]').val();
		this.header = $('input[name=_csrf_header]').val();
	}
	
	Security.prototype.enable = function(){
		$(document).ajaxSend(function(event, jqxhr, settings){
			jqxhr.setRequestHeader(this.header, this.token);
		}.bind(this));
		
	}
	
	return Security;
}());




$(function() {
	var maskMoney = new Brewer.MaskMoney();
	maskMoney.enable();
	
	var maskPhoneNumber = new Brewer.MaskPhoneNumber();
	maskPhoneNumber.enable();
	
	var maskCEPNumber = new Brewer.MaskCEPNumber();
	maskCEPNumber.enable();
	
	var maskDater = new Brewer.MaskDate();
	maskDater.enable();
	
	var security = new Brewer.Security();
	security.enable();
	
});