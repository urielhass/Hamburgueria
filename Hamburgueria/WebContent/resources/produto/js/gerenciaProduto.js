$(document).ready(function(){
	HM.produto.delet = function(cod) {
		bootbox.confirm({
			size : 'small',
			message : "Deseja deletar o Produto?",
			callback : function(result) {
				if(result){
					HM.produto.deletar({
						cod : cod,
						success : function(succ) {
							bootbox.alert(succ);
							HM.produto.listarTodos();
						},
						error : function(err) {
							console.log(err);
							bootbox.alert('Erro ao deletar Produto');
						}
					});
				}
			}
		});
	};

	HM.produto.listarTodos = function(){
		HM.produto.exibir({
			data:$("#search").val(),
			success : function(data) {
				var html = "";
				for ( var i = 0; i < data.length; i++) {
					html += "<tr><td>" + data[i].cod + "</td>"
							+ "<td>" + data[i].nome + "</td>"
							+ "<td>" + data[i].descricao + "</td>"
							+ "<td>" + data[i].categoria + "</td>"
							+ "<td> R$ " + data[i].valor + "</td>"
							+ "<td><a href='#' onclick='HM.produto.edit(" + data[i].cod + ")'"
							+ "<i class='glyphicon glyphicon-edit editProduto' aria-hidden='true'></i></a>    "
							+ "   <a href='#' onclick='HM.produto.delet(" + data[i].cod + ")'>"
							+ "<i class='glyphicon glyphicon-remove-sign deletProduto' aria-hidden='true'></i></a></td></tr>";
				}
				$("tbody").html(html);
			},
			error : function(error) {
				console.log(error);
			}
		});
	};

	HM.produto.edit = function(cod){
		$.ajax({
			url : "resources/produto/formularioProduto.html",
			success : function(dat) {
				HM.produto.popular({
					data: cod,
					success: function(resp){
						$("#conteudo").html(dat);
						$("#titleChange").text("Editar Produto");
						$("#cadastrarProduto").attr('onclick', "HM.produto.edite();");
						$("form input, form select").each(function(){
							$(this).val(resp[this.id]);
						});
					},
					error: function(err){
						console.log(err);
					}
				})
			},error:function(err){console.log(err.responseText());}
		})
	}
	HM.produto.listarTodos();
});