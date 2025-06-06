$(function(){
 const md = new bootstrap.Modal($('#md_lab')[0]);

  let modo = 'insertar';

  function listar(){
    $.post(baseUrl + '/RegLaboratorio',
      { opcion: 'consultar' },
      function(res){
        if (res[0].resultado === 'exito') {
          $('#tablaContainer').html(res[0].tabla);
        }
      }, 'json'
    );
    
  }

  $('#btnNuevo').click(() => {
    modo = 'insertar';
    $('#formLab')[0].reset();
    $('#idLab').val('');
    md.show();
    
    
  });

  // Editar
  $(document).on('click', '.btn_editar', function(){
    modo = 'editar';
    const id = $(this).data('id');
    $.post(baseUrl + '/RegLaboratorio',
      { opcion: 'editar_consultar', id },
      function(res){
        if (res[0].resultado === 'exito') {
          const l = res[0].laboratorio;
          $('#idLab').val(l.id);
          $('#nombre').val(l.nombre);
          $('#edificio').val(l.edificio);
          $('#piso').val(l.piso);
          md.show();
        } else {
          alert('Laboratorio no encontrado');
        }
      }, 'json'
    );
  });

  // Eliminar
  $(document).on('click', '.btn_eliminar', function(){
    if (!confirm('¿Eliminar este laboratorio?')) return;
    const id = $(this).data('id');
    $.post(baseUrl + '/RegLaboratorio',
      { opcion: 'eliminar', id },
      function(res){
        if (res[0].resultado === 'exito') {
          listar();
        } else {
          alert('Error al eliminar');
        }
      }, 'json'
    );
  });

  // Guardar (insertar / actualizar)
  $('#btnGuardar').click(() => {
    const datos = $('#formLab').serialize();
    const op = modo === 'insertar' ? 'insertar' : 'si_actualizalo';
    $.post(baseUrl + '/RegLaboratorio',
      datos + '&opcion=' + op,
      function(res){
        if (res[0].resultado === 'exito') {
                     
          md.hide();
          listar();
        } else {
          alert('Error al guardar');
        }
      }, 'json'
    );
  });

  // Al cargar la página
  listar();
});
    