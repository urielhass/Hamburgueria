$(document).ready(
		function() {
			HM.produto = new Object();
			HM.produto = {
				exibir : function(cfg) {
					var busca = cfg.data;
					if (busca == "") {
						busca = "null";
					}
					HM.ajax.get({
						url : "rest/ProdutoRest/buscarNome/"
								+ busca,
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
					})
				},
				deletar : function(cfg) {
					var cod = cfg.cod;
					HM.ajax.DELETE({
						url : "rest/ProdutoRest/deletar/" + cod,
						success : function(cod) {
							if (cfg && cfg.success) {
								cfg.success(cod);
							}
						},
						error : function(err) {
							if (cfg && cfg.error) {
								cfg.error(error);
							}
						}
					});

				},
				adicionar : function(cfg) {
					var newData = cfg.data;
					HM.ajax.post({
						url : "rest/ProdutoRest/adicionar",
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
				popular : function(cfg) {
					var cod = cfg.data;
					HM.ajax.get({
						url : "rest/ProdutoRest/buscarId/"
								+ cod,
						async : false,
						success : function(user) {
							if (cfg && cfg.success) {
								cfg.success(user);
							}
						},
						error : function(err) {
							if (cfg && cfg.error) {
								cfg.error(error);
							}
						}
					});
				},
				editar : function(cfg) { 
					HM.ajax.put({
						url : "rest/ProdutoRest/editar",
						data : JSON.stringify(cfg.data),
						success : function(succ) {
							if (cfg && cfg.success) {
								cfg.success(succ);
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