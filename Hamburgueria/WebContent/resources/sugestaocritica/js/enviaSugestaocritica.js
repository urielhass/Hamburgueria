$(document).ready(function(){
	$("#buttonSugescri").on('click',function(){
		HM.sugescri.adicionar({
			data:{'email':$("#email").val(), 'nome':$("#nome").val(),
				'telefone':$("#telefone").val(), 'mensagem':$("#mensagem").val()},
			success:function(data){
				bootbox.alert(data);
				$("#myModal2").modal('toggle');
			},
			error:function(error){
				console.log(error);
			}
		});
	});
});