$(document).ready(function(){
	HM.taxa.busca = function(){
		var ok = $("#search").val();
		if (ok == "") {
			ok = "null";
		}
		HM.taxa.buscar({
			data: ok,
			success:function(resp){
				var html = "";
				for ( var i = 0; i < resp.length; i++) {
					html += "<tr><td><span id='cod" + i + "'><strong>" + resp[i].cod + "</strong></span></td>"
							+ "<td><span id='nome" + i + "'>" + resp[i].nome + "</span></td>"
							+ "<td><span id='descricao" + i + "'>" + resp[i].descricao + "</span></td>"
							+ "<td><input id='valor" + i + "' class='form-control' value='" + resp[i].valor + "'/></td>"
							+ "<td><button type='button' class='btn btn-primary' onclick='HM.taxa.atualize(" + i  + ")'>atualizar</button></td></tr>";
				}
				$("table tbody").append(html);
			},
			error:function(err){
				console.log(err.responseText());
			}
		});
	};
	
	HM.taxa.atualize = function(cod){
		HM.taxa.atualizar({
			data: JSON.stringify({'cod': cod , 'valor': $("#valor"+cod).val()}),
			success:function(succ){
				console.log(succ);
			},
			error:function(err){
				console.log(err);
			}
		});
	};

	$('#buttonSearch').on('click', function(){
		HM.taxa.busca();
	});
	HM.taxa.busca();
});