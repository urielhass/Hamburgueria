$(document).ready(function(){
	HM.usuario.cadastrar = function(){
		HM.usuario.adicionar({
			data:HM.usuario.getValor(),
			success : function(data) {
				console.log(data);
				bootbox.alert(data);
				carregar('resources/usuario/gerenciaUsuario.html');
			},
			error : function(error) {
				console.log(error);
			}
		});
	};
	HM.usuario.edit = function(){
		console.log(HM.usuario.getValor());
		HM.usuario.editar({
			data:HM.usuario.getValor(),
			success : function(data) {
				bootbox.alert(data);
				carregar('resources/usuario/gerenciaUsuario.html');
			},
			error : function(error) {
				console.log(error);
			}
		});
	};
	HM.usuario.getValor = function(value){
		var newUsuario = new Object();
		$("form div input, form div select").each(function(){
			newUsuario[this.name]=this.value;
			});
		return newUsuario;
	};
		$("#cidade").on('focus',function(){
				HM.cidade.listar({
				async : false,
				success : function(data) {
					var html = "";
					for ( var i = 0; i < data.length; i++) {
						html += "<option value='"
								+ data[i].cod
								+ "'>"
								+ data[i].cidade
								+ "</option>";
					}
					$("#cidade").append(html);
				},
				error:function(err){
					console.log(err);
				}
			});
		});
		$("#cidade").on('change', function(){
			HM.bairro.listar({
				data: $('#cidade').val(),
				success:function(data){
					var html = "";
					for ( var i = 0; i < data.length; i++) {
						html += "<option value='"
								+ data[i].codBairro
								+ "'>"
								+ data[i].bairro
								+ "</option>";
					}
					$("#bairro").html(html);
				},
				error:function(err){
					console.log(err);
				}
			});
		});
});