$(function () {

    function cargarEquipos(labId) {

        $.ajax({
            url: 'EquipoServlet',
            method: 'POST',
            data: {op: 'listar', labId: labId},
            dataType: 'json'
        }).done(function (json) {
            var tbody = $('#tablaEquipos').empty();
            json.forEach(function (e) {
                tbody.append(
                        `<tr>
             <td>${e.id}</td><td>${e.tipo}</td>
             <td>${e.marca}</td><td>${e.modelo}</td>
             <td>${e.serie}</td><td>${e.estado}</td>
           </tr>`
                        );
            });
        });
       
    }
    $('#busqueda').on('input', function () {
        const texto = $(this).val().toLowerCase();
        $('#tablaEquipos tr').each(function () {
            const marca = $(this).find('td:eq(2)').text().toLowerCase();
            const modelo = $(this).find('td:eq(3)').text().toLowerCase();
            $(this).toggle(marca.includes(texto) || modelo.includes(texto));
        });
    });

    $('#labSelect').change(function () {
        cargarEquipos($(this).val());
    });

    
    cargarEquipos($('#labSelect').val());
});
