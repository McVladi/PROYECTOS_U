$(function() {
    // Función para actualizar las tarjetas
    function actualizarEstadisticas() {
        $.ajax({
            url: baseUrl + '/EstadisticasServlet',
            method: 'POST',
            dataType: 'json',
            success: function(res) {
                console.log('Respuesta recibida:', res);
                $('#cntUsuarios').text(res.usuarios);
                $('#cntEquipos').text(res.equipos);
                $('#cntMant').text(res.mant);
                
                // Actualizar la fecha/hora de actualización
                $('.card-footer small').html('<i class="fas fa-sync-alt me-1"></i>Actualizado: ' + 
                    new Date().toLocaleTimeString());
            },
            error: function(xhr, status, error) {
                console.error('Error al obtener estadísticas:', error);
                $('#cntUsuarios').text('0');
                $('#cntEquipos').text('0');
                $('#cntMant').text('0');
                
                $('.card-footer small').html('<i class="fas fa-exclamation-triangle me-1"></i>Error al actualizar');
            }
        });
    }

    // Cargar al inicio
    actualizarEstadisticas();
    
    // Opcional: Actualizar cada 60 segundos
    setInterval(actualizarEstadisticas, 60000);
    
    // También puedes agregar un botón de actualización manual
    $(document).on('click', '.card-footer small', function(e) {
        e.preventDefault();
        $(this).html('<i class="fas fa-sync-alt fa-spin me-1"></i>Actualizando...');
        actualizarEstadisticas();
    });
});