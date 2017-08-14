$(document).ready(function() {
	$.fn.bootstrapSwitch.defaults.onText = 'Entregue';
	$.fn.bootstrapSwitch.defaults.offText = 'Finalizado';
	$.fn.bootstrapSwitch.defaults.onColor = 'danger';
	$("[name='my-checkbox']").bootstrapSwitch();
});