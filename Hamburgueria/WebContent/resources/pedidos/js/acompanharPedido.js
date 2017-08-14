$(document).ready(function() {
	$.fn.bootstrapSwitch.defaults.onText = 'Pronto';
	$.fn.bootstrapSwitch.defaults.offText = 'Entrega';
	$.fn.bootstrapSwitch.defaults.onColor = 'danger';
	$("[name='my-checkbox']").bootstrapSwitch();

	$(".cancelConfirm").on("click", function() {
		bootbox.confirm({
			size : 'small',
			message : "Deseja cancelar o Pedido?<br/>",
			callback : function(result) {
			}
		})
	})

});