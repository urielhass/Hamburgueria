$(document).ready(
				function() {
					HM.funcionario.buscar = function() {
						var busca = $("#buscarInput").val();
						HM.funcionario.exibirFuncionarios(undefined, busca);
					};

					HM.funcionario.exibirFuncionarios = function(listFunc,
							busca) {
						HM.funcionario
								.exibir({
									busca : busca,
									success : function(listFunc) {
										var html;
										for (var i = 0; i < listFunc.length; i++) {
											html += "<tr><td>"
													+ listFunc[i]['codfuncionario']
													+ "</td>"
													+ "<td>"
													+ listFunc[i]['nomeFuncionario']
													+ "</td>"
													+ "<td>"
													+ listFunc[i]['funcao']
													+ "</td>"
													+ "<td>"
													+ listFunc[i]['fone']
													+ "</td>"
													+ "<td><a href='#' onclick='HM.funcionario.editarFuncionario("
													+ listFunc[i]['codfuncionario']
													+ ")'>"
													+ "<i class='glyphicon glyphicon-edit editFunc' aria-hidden='true'></i>"
													+ "<a href='#' onclick='HM.funcionario.deletarFuncionario("
													+ listFunc[i]['codfuncionario']
													+ ");'>"
													+ "<i class='glyphicon glyphicon-remove-sign deletFunc' aria-hidden='true'></i></a></td></tr>";
										}
										$("tbody").html(html);
									},
									error : function(err) {
										console.log(err);
									}
								});
					};
					
					HM.funcionario.deletarFuncionario = function(cod) {
						bootbox
								.confirm({
									size : 'small',
									message : "Deseja deletar o funcionário?",
									callback : function(cb) {
										if (cb == true) {
											HM.funcionario
													.deletar({
														cod : cod,
														success : function(succ) {
															bootbox.alert(succ);
															HM.funcionario
																	.buscar();
														},
														error : function(err) {
															console.log(err);
															bootbox
																	.alert('Erro ao deletar funcionário');
														}
													});
										}
									}
								});
					};
					HM.funcionario.editarFuncionario = function(cod) {
						$
								.ajax({
									url : "resources/funcionario/cadastroFuncionario.html",
									async : false,
									success : function(dat) {
										$("#conteudo").html(dat);
										HM.cidade
												.listar({
													async : false,
													success : function(data) {
														var html = "";
														for (var i = 0; i < data.length; i++) {
															html += "<option value='"
																	+ data[i].cod
																	+ "'>"
																	+ data[i].cidade
																	+ "</option>";
														}
														$("#txtcidade").append(
																html);
														HM.funcionario
																.popular({
																	data : cod,
																	success : function(
																			func) {
																		$(
																				'#txtnome')
																				.val(
																						func.nomeFuncionario);
																		$(
																				'#nmbrcpf')
																				.val(
																						func.cpf);
																		$(
																				"#nmbrrg")
																				.val(
																						func.rg);
																		$(
																				"#nmbrdatanascimento")
																				.val(
																						func.dataNascimento);
																		$(
																				"#nmbrfone")
																				.val(
																						func.fone);
																		$(
																				"#txtemail")
																				.val(
																						func.email);
																		$(
																				"#txtfuncao")
																				.val(
																						func.funcao);
																		$(
																				"#txtcidade")
																				.val(
																						func.cidade);
																		$(
																				"#txtbairro")
																				.val(
																						func.bairro);
																		$(
																				"#txtrua")
																				.val(
																						func.rua);
																		$(
																				"#nmbrcasa")
																				.val(
																						func.numero);
																		$(
																				"#cep")
																				.val(
																						func.cep);
																		$(
																				"#txtcomplemento")
																				.val(
																						func.complemento);
																		$(
																				"#codfuncionario")
																				.val(
																						func.codfuncionario);
																		$(
																				"#buttonConfirmar")
																				.attr(
																						"onclick",
																						"HM.funcionario.exibirEdicao();");
																		if (func.administrador == 1) {
																			$(
																					"#administrador")
																					.prop(
																							"checked",
																							"true")
																		} else {
																			$(
																					"#administrador")
																					.prop(
																							"unchecked")
																		}

																	},
																	error : function(
																			err) {
																		console
																				.log(err);
																		bootbox
																				.alert("Erro ao Editar Funcionario.");
																	}
																})
													}
												});
									},
									error : function(err) {
										console.log(err);
									}
								})
					}
					$("#searchButton").on('click', function(){
						HM.funcionario.buscar();
					});
					HM.funcionario.buscar();
				});