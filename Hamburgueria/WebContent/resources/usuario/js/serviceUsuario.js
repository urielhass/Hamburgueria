$(document).ready(
		function() {
			HM.usuario = new Object();
			HM.usuario = {
				exibir : function(cfg) {
					var busca = cfg.data;
					if (busca == "") {
						busca = "null";
					}
					HM.ajax.get({
						url : "rest/UsuarioRest/buscarUsuariosPorNome/"
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
						url : "rest/UsuarioRest/deletarUsuario/" + cod,
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
						url : "rest/UsuarioRest/addUsuario",
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
						url : "rest/UsuarioRest/buscarUsuarioPeloId/"
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
						url : "rest/UsuarioRest/editarUsuario",
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