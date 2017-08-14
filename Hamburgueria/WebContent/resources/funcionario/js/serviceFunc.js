$(document).ready(
		function() {
			HM.funcionario = new Object();
			HM.funcionario = {
				exibir : function(cfg) {
					var busca = cfg.busca;
					if (busca == "") {
						busca = "null";
					}
					HM.ajax.get({
						url : "rest/funcionarioRest/buscarFuncionariosPorNome/"
								+ busca,
						success : function(listFunc) {
							if (cfg && cfg.success) {
								cfg.success(listFunc);
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
						url : "rest/funcionarioRest/deletarFuncionario/" + cod,
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
						url : "rest/funcionarioRest/addFuncionario",
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
						url : "rest/funcionarioRest/buscarFuncionarioPeloId/"
								+ cod,
						async : false,
						success : function(func) {
							if (cfg && cfg.success) {
								cfg.success(func);
							}
						},
						error : function(err) {
							if (cfg && cfg.error) {
								cfg.error(error);
							}
						}
					})
				},
				editar : function(cfg) {
					var newData = cfg.data;
					HM.ajax.put({
						url : "rest/funcionarioRest/editarFuncionario",
						data : JSON.stringify(newData),
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