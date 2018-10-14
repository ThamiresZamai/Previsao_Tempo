$(function() {

	$(document).ready(function() {

			function limpa_formulário() {
				// Limpa valores do formulário de cep.
				$("#nome").val("");
				$("#cpf").val("");
				$("#email").val("");
				$("#cep").val("");
				$("#logradouro").val("");
				$("#numero").val("");
				$("#complemento").val("");
				$("#bairro").val("");
				$("#cidade").val("");
				$("#uf").val("");
			}

			// Quando o campo cep perde o foco.
			$("#cep").blur(function() {

				var cep = $(this).val().replace(/\D/g, '');

					if (cep != "") {

						var validacep = /^[0-9]{8}$/;
							if (validacep.test(cep)) {

										$("#logradouro").val("");
										$("#numero").val("");
										$("#complemento").val("");
										$("#bairro").val("");
										$("#cidade").val("");
										$("#uf").val("");
										
										$.getJSON("http://treinamento023/endereco?cep="+ cep ,function(dados) {

										if (!("erro" in dados)) {
												$("#logradouro").val(dados.logradouro);
												$("#numero").val(dados.numero);
												$("#complemento").val(dados.complemento);
												$("#bairro").val(dados.bairro);
												$("#cidade").val(dados.cidade);
												$("#uf").val(dados.uf);
										} else {
												limpa_formulário();
												alert("CEP não encontrado.");
										}
								});
										} else {
												limpa_formulário();
												alert("Formato de CEP inválido.");
										}
									} else {
										limpa_formulário();
								}
							});
					});

	$('#btn-salvar').click(function(event) {
		
				event.preventDefault();
				var pessoa = {
					nome : $('#nome').val(),
					cpf : $('#cpf').val(),
					email : $('#email').val(),
					enderecodto : {
						cep : $("#cep").val(),
						logradouro : $("#logradouro").val(),
						numero : $("#numero").val(),
						complemento : $("#complemento").val(),
						bairro : $("#bairro").val(),
						cidade : $("#cidade").val(),
						uf : $("#uf").val()

					}
				}
				
				
				var opcoes = {
			            url: 'http://localhost:8080/pessoa',
			            method: 'POST',
			            contentType: 'application/json',
			            data: JSON.stringify(pessoa),
			           success: function (resposta) {
			               /* if (resposta.sucesso) {
			                    alert(resposta.mensagem);
			                } else {
			                    console.log(resposta.mensagem);
							}*/
							listar();
			            }
			        };

			        $.ajax(opcoes);
				
			});
	
	$('#btn-listar').click(function() {
        listar();
        
	});

	function listar(){
		$('#container').load('/listar.html', function() {

            $.get('http://localhost:8080/pessoa', function(dados){
            	
            	$('table > tbody > tr').remove();
            	
                dados.forEach(function(pessoa) {
                    
                    var tdcpf = $('<td></td>').html(pessoa.cpf).addClass('cpf');
                    var tdNome = $('<td></td>').html(pessoa.nome);
                    var tdCidade = $('<td></td>').html(pessoa.enderecodto.cidade);

					var btnExcluir = $('<button>Excluir</button>')
										.addClass('btn-excluir')
										.attr('cpf', pessoa.cpf);

                    var btnAlterar = $('<button>Alterar</button>').addClass('btn-alterar').attr('cpf', pessoa.cpf);
            
                    var tr = $('<tr></tr>').append(tdcpf).append(tdNome).append(tdCidade).append(btnExcluir).append(btnAlterar);
            
                    $('table > tbody').append(tr);
                });
            });
        });
	}
	
	$('#container').on('click', '.btn-excluir', function (event) {
		
        var cpf = $(this).attr('cpf');

        $.ajax({
            url: 'http://localhost:8080/pessoa/' + cpf,
            method: 'DELETE',
            success: function () {
               
                listar();
                
            }
        });
        
        event.preventDefault();

	});
	
	$('#container').on('click', '.btn-alterar', function (event) {
		
        var cpf = $(this).attr('cpf');
		console.log(cpf)
        $.ajax({
            url: 'http://localhost:8080/pessoa/' + cpf,
            method: 'get',
            success: function (json) {

				$('#cpf').val(json.cpf);
                    $('#nome').val(json.nome);
                    $('#email').val(json.email);
                    $('#cep').val(json.enderecodto.cep);
                    $('#logradouro').val(json.enderecodto.logradouro);
                    $('#numero').val(json.enderecodto.numero);
                    $('#complemento').val(json.enderecodto.complemento);
                    $('#bairro').val(json.enderecodto.bairro);
                    $('#cidade').val(json.enderecodto.cidade);
                    $('#uf').val(json.enderecodto.uf);
                
            }
        });
        
        event.preventDefault();

	});

	$('#btn-tempo').click(function(){
		window.location.href = "/tempo.html";
	});
	
	$('#btn-buscar').click(function (event) {
	
		$('#mostratempo').text("");
		
        $.ajax({
            url: 'http://api.openweathermap.org/data/2.5/weather?q='+ $('#cidade').val() +',br&appid=2a1ed974a4e86642a69ac57828175631',
            method: 'GET',
            success: function (data) {
				
				$('#mostratempo').append('</br></br><span>Tempo Maximo: '+ data.main.temp_max +'</span></br>')
								 .append('<span>Tempo Minimo: '+ data.main.temp_min +'</span></br>')
								 .append('<span>Descrição: '+ data.weather[0].description +'</span></br>')
								 .append('<span>Umidadde: '+ data.main.humidity +'</span></br>')			

            },error(x,y,z){
				alert(z);
			}
        });
        
        event.preventDefault();

	});
})