$(document).ready(function(){
	HM.usuario.delet = function(cod) {
		bootbox.confirm({
			size : 'small',
			message : "Deseja deletar o Usuário?",
			callback : function(result) {
				if(result){
					HM.usuario.deletar({
						cod : cod,
						success : function(succ) {
							bootbox.alert(succ);
							HM.usuario.listarTodos();
						},
						error : function(err) {
							console.log(err);
							bootbox.alert('Erro ao deletar Usuário');
						}
					});
				}
				
			}
		});
	};

	HM.usuario.listarTodos = function(){
		HM.usuario.exibir({
			data:$("#search").val(),
			success : function(data) {
				var html = "";
				for ( var i = 0; i < data.length; i++) {
					html += "<tr><td>" + data[i].cod + "</td>"
							+"<td>" + data[i].nome + "</td>"
							+"<td>" + data[i].cidade + "</td>"
							+"<td>" + data[i].bairro + "</td>"
							+"<td>" + data[i].rua + ", nº " + data[i].numero + "</td>"
							+"<td><a href='#'title='Editar Usuário' onclick='HM.usuario.edit(" + data[i].cod + ")'>"
							+ "<i class='glyphicon glyphicon-edit editUser' aria-hidden='true'></i>"
							+ "<a href='#' title='Deletar Usuário' onclick='HM.usuario.delet(" + data[i].cod + ");'>"
							+ "<i class='glyphicon glyphicon-remove-sign deletUser' aria-hidden='true'></i></a></td></tr>";
				}
				$("tbody").html(html);
			},
			error : function(error) {
				console.log(error);
			}
		});
	};
	
	HM.usuario.edit = function(cod){
		$.ajax({
			url : "resources/usuario/cadastroUsuario.html",
			success : function(dat) {
				$("#conteudo").html(dat);
				HM.cidade.listar({
					success : function(data) {
						var html = "";
						for (var i = 0; i < data.length; i++) {
							html += "<option value='"+ data[i].cod+ "'>"+ data[i].cidade+ "</option>";
						}
						$("#cidade").append(html);
						HM.usuario.popular({
							data: cod,
							success:function(resp){
								HM.bairro.listar({
									data:resp.cidade,
									success:function(data){
										html="";
										for (var i = 0; i < data.length; i++) {
											html += "<option value='"+ data[i].codBairro+ "'>"+ data[i].bairro+ "</option>";
										}
										$("#bairro").append(html);
										$("#titleChange").text("Editar usuário");
										$("#cadastrarUsuario").attr('onclick', "HM.usuario.edit();");
										$("form input, form select").each(function(){
											$(this).val(resp[this.id]);
										});
									},error:function(err){console.log(err.responseText());}
								})

							},
							error:function(err){
								console.log(err.responseText());
							}
						});
					}
				});		
			},
			error:function(err){
				console.log(err.responseText());
			}
		});
	};
	HM.usuario.listarTodos();
});
