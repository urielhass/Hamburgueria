function carregar(pagina) {
	$("#conteudo").load(pagina);
}
HM.sessao = function(data){
	HM.ajax.post({
		url:"rest/Sessao/getSessao/"+ data,
		success:function(succ){
			console.log(succ);
		},
		error:function(err){
			console.log(err);
		}
	});
};
$(document).ready(function() {
	$('#conteudoIndex').load('resources/ofertadia.html');
	$('#sugestaoCritica').load('resources/sugestaocritica.html');
});
