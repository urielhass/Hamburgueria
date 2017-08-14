$(document).ready(function() {
	HM.sugescri = new Object();
	HM.sugescri = {
		adicionar : function(cfg) {
			var newData = cfg.data;
			HM.ajax.post({
				url : "rest/DuvidaRest/adicionar",
				data : JSON.stringify(newData),
				success : function(data) {
					if (cfg && cfg.success) {
						cfg.success(data);
					}
				},
				error : function(error) {
					if (cfg && cfg.error) {
						cfg.error(error);
					}
				}
			});
		},
		exibir : function(cfg) {
			HM.ajax.get({
				url : "rest/DuvidaRest/buscarNome/" + busca,
				success : function(list) {
					if (cfg && cfg.success) {
						cfg.success(list);
					}
				},
				error : function(err) {
					if (cfg && cfg.error) {
						cfg.error(error);
					}
				}
			});
		}
	};
});