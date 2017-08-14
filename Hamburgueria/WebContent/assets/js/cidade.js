$(document).ready(function() {
	HM.cidade = {
		listar : function(cfg) {
			HM.ajax.get({
				url : "rest/CidadesRest",
				success : function(listaDeCidade) {
					if (cfg && cfg.success) {
						cfg.success(listaDeCidade);
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