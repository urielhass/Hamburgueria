$(document).ready(function() {
	HM.bairro = {
		listar : function(cfg) {
			HM.ajax.get({
				url : "rest/BairroRest/buscarPorId/"+cfg.data,
				success : function(listaDeBairro) {
					if (cfg && cfg.success) {
						cfg.success(listaDeBairro);
					}
				},
				error : function(error) {
					if (cfg && cfg.error) {
						cfg.error(error);
					}
				}
			});
		}
	};	
});