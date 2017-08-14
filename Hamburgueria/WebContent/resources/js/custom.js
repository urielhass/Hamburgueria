function carregarPedido(pagina){
   	$("#conteudo").load(pagina);
}
function adicionar(produto){
	bootbox.dialog({
	  message: "<img src='imagem/imagem3.png' width='100%' style='margin:0 auto'/><div class='descricaoProduto'><p>LOREM LADFJ UHAEUI HMAS I LKAS JI IFHS FISH PAGE WEB RESPONSE</p></div>",
	  title: "Título<hr>",
	  size: 'small',
	  onEscape: function() {},
	  buttons: {
	    success: {
	      label: "Adicionar ao Carrinho",
	      className: "btn-success",
	      callback: function() {
	        alert("great success");
	  	    	}
	    	},
	    	danger: {
		      label: "Fechar",
		      className: "btn-danger",
		      callback: function() {
		       		$(this).fadeOut(1000);
		      	}
		    }
		}
	});
}
