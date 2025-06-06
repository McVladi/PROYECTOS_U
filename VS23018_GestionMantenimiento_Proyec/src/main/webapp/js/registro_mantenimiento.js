$(function(){
  // 1) instancio el modal correcto
  const md = new bootstrap.Modal($('#md_mantenimiento'));
  let modo = 'insertar';

  // 2) carga los combos de equipo y técnico
  function cargarCombos(){
    $.post(baseUrl + '/RegMantenimiento',
      { opcion: 'cargarCombos' },
      function(res){
        if(res[0].resultado === 'exito'){
          $('#equipoSelect').html(res[0].equipos);
          $('#tecnicoSelect').html(res[0].tecnicos);
        }
      }, 'json'
    );
  }

  // 3) lista todos los mantenimientos
  function listar(){
    $.post(baseUrl + '/RegMantenimiento',
      { opcion: 'consultar' },
      function(res){
        if(res[0].resultado === 'exito'){
          $('#tablaContainer').html(res[0].tabla);
        }
      }, 'json'
    );
  }

  // inicializo
  cargarCombos();
  listar();

  // 4) Nuevo mantenimiento
  $('#btnNuevoMant').click(() => {
    modo = 'insertar';
    $('#formMant')[0].reset();
    $('#idMant').val('');
    cargarCombos();
    md.show();
  });

  // 5) Editar
  $(document).on('click', '.btn_editar', function(){
    modo = 'editar';
    const id = $(this).data('id');
    $.post(baseUrl + '/RegMantenimiento',
      { opcion: 'editar_consultar', id: id },
      function(res){
        if(res[0].resultado === 'exito'){
          const m = res[0].mantenimiento;
          $('#idMant').val(m.id);
          $('#fechaProgramada').val(m.fechaProgramada);
          $('#fechaReal').val(m.fechaReal);
          $('#tipoMant').val(m.tipo);
          $('#observaciones').val(m.observaciones);
          $('#piezasReemplazadas').val(m.piezasReemplazadas);
          $('#equipoSelect').val(m.equipoId);
          $('#tecnicoSelect').val(m.tecnicoId);
          md.show();
        } else {
          alert('No se encontraron datos del mantenimiento');
        }
      }, 'json'
    );
  });

  // 6) Eliminar
  $(document).on('click', '.btn_eliminar', function(){
    if(!confirm('¿Eliminar este mantenimiento?')) return;
    const id = $(this).data('id');
    $.post(baseUrl + '/RegMantenimiento',
      { opcion: 'eliminar', id: id },
      function(res){
        if(res[0].resultado === 'exito') listar();
        else alert('No se pudo eliminar');
      }, 'json'
    );
  });

  // 7) Guardar (insert o update)
  $('#btnGuardarMant').click(() => {
    const datos = $('#formMant').serialize();
    const op = modo === 'insertar' ? 'insertar' : 'si_actualizalo';
    $.post(baseUrl + '/RegMantenimiento',
      datos + '&opcion=' + op,
      function(res){
        if(res[0].resultado === 'exito'){
          md.hide();
          listar();
        } else {
          alert('Error al guardar');
        }
      }, 'json'
    );
  });

});
