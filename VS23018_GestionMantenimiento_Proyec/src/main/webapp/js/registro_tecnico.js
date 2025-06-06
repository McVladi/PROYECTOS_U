$(function(){
  const md = new bootstrap.Modal($('#md_tecnico'));
  let modo = 'insertar'; // o 'editar'

  function listar(){
    $.post(baseUrl+'/RegTecnico', {opcion:'consultar'}, function(res){
      if(res[0].resultado==='exito'){
        $('#tablaContainer').html(res[0].tabla);
      }
    }, 'json');
  }

  $('#btnNuevo').click(()=>{
    modo='insertar';
    $('#formTecnico')[0].reset();
    $('#idTecnico').val('');
    md.show();
  });

  // para editar/eliminar
  $(document).on('click','.btn_editar', function(){
  modo = 'editar';
  const id = $(this).data('id');
  console.log("Pidiendo datos para ID:", id);
  $.post(baseUrl + '/RegTecnico',
    { opcion: 'editar_consultar', id: id },
    function(res){
      console.log("Respuesta editar_consultar:", res);
      if (res[0].resultado === 'exito') {
        const t = res[0].tecnico;
        // rellena el formulario
        $('#idTecnico').val(t.id);
        $('#nombre').val(t.nombre);
        $('#identificacion').val(t.identificacion);
        $('#especialidad').val(t.especialidad);
        $('#telefono').val(t.telefono);
        $('#email').val(t.email);
        // muestra el modal
        md.show();
      } else {
        alert('No se encontraron los datos del técnico');
      }
    },
    'json'
  ).fail(function(xhr, status, err) {
    console.error('Error AJAX editar_consultar:', status, err, xhr.responseText);
  });
});


 $(document).on('click','.btn_eliminar',function(){
  const id = $(this).data('id');
  if (!confirm('¿Eliminar este técnico?')) return;
  console.log("Eliminando ID:", id);
  $.post(baseUrl + '/RegTecnico',
    { opcion: 'eliminar', id: id },
    function(res){
      console.log("Respuesta eliminar:", res);
      if (res[0].resultado === 'exito') {
        listar();
      } else {
        alert('No se pudo eliminar: ' + (res[0].mensaje || ""));
      }
    },
    'json'
  ).fail(function(xhr, status, err){
    console.error('Error AJAX eliminar:', status, err, xhr.responseText);
  });
});


  $('#btnGuardar').click(()=>{
    const datos = $('#formTecnico').serialize();
    const op = modo==='insertar'?'insertar':'si_actualizalo';
    $.post(baseUrl+'/RegTecnico', datos + '&opcion='+op, function(res){
      if(res[0].resultado==='exito'){
        md.hide();
        listar();
      } else {
        alert('Error al guardar');
      }
    }, 'json');
  });

 //llamamos la lista
  listar();
});
