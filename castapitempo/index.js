$(function() {

    $('#btn-buscar').click(function (event) {
        
            $('#mostratempo').text("");
            var cidade = $('#cidade').val();

            $.ajax({
                url: 'http://localhost:8080/tempo/'+ cidade,
                method: 'GET',
                success: function (data) {

                     $('#mostratempo').append('<h1>'+ cidade +'</h1>')
                    $.each(data, function(i, item) {
                        $('#mostratempo').append('</br></br><span>Data: '+ item.data +'</span></br>')
                        .append('<span>Tempo Maximo: '+ item.temperaturaMax +'</span></br>')
                        .append('<span>Tempo Minimo: '+ item.temperaturaMin +'</span></br>')
                        .append('<span>Descrição: '+ item.descricao +'</span></br>')
                        .append('<span>Umidadde: '+ item.umidade +'</span></br>')
                        .append('<span> -------------------- </span></br>')	
                        
                    });
                    
                    		

                },error(x,y,z){
                    alert(z);
                }
            });
            
            event.preventDefault();

        });
});