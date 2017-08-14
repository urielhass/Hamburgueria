HM.login = function() {
	$("#login").submit();
		$("#myModal").modal('toggle');
		$("#div-menu-login strong").text('Logout');
		$("#div-menu-login").removeAttr('href');
		$("#div-menu-login").attr('onclick', 'HM.logout();');
		$("#div-menu-login").css('cursor', 'pointer');
};
HM.logout = function(){
	var toma = 'destroi';
	$.ajax({
		url: "logout/DestroiSessao",
		data: toma,
		success:function(succ){
			console.log(succ);
		},
		error:function(error){
			console.log(error);
		}
	});
};