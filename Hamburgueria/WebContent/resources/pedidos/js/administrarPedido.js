$(document).ready(
		function() {
			$.fn.bootstrapSwitch.defaults.onText = 'Pago';
			$.fn.bootstrapSwitch.defaults.offText = 'Produção';
			$.fn.bootstrapSwitch.defaults.onColor = 'danger';
			$("[name='my-checkbox']").bootstrapSwitch();
			$(".cancelConfirm").on("click", function() {
				bootbox.confirm({
					size : 'small',
					message : "Deseja cancelar o pedido?",
					callback : function(result) {
					}
				});
			});

			$('input[name="my-checkbox"]').on('switchChange.bootstrapSwitch',
					function(event, state) {
						$(this).closest('tr').fadeOut(1000);
					});
		});
