$(function(){
  const md = new bootstrap.Modal($('#md_usuario'));
  let modo = 'insertar';

  // 1) Listar los usuarios y rellenar <tbody id="tablaUsuarios">
  function listar() {
    $.post(baseUrl + '/RegUsuarioServlet',
      { opcion: 'consultar' },
      function(res) {
        if (res[0].resultado === 'exito') {
          $('#tablaUsuarios').html(res[0].tabla);
        }
      }, 'json'
    );
  }

  // 2) Cargar el <select id="rol"> con los enums
  function cargarRoles(selectedRol) {
    $.post(baseUrl + '/RegUsuarioServlet',
      { opcion: 'cargarRoles' },
      function(res) {
        if (res[0].resultado === 'exito') {
          $('#rol').html(res[0].roles);
          if (selectedRol) {
            $('#rol').val(selectedRol);
          }
        }
      }, 'json'
    );
  }

  // 3) “+ Nuevo Usuario”: limpia form, carga roles vacíos y abre modal
  $('#btnNuevoUsr').click(() => {
    modo = 'insertar';
    $('#formUsuario')[0].reset();
    $('#idUsuario').val('');
    cargarRoles(null);
    md.show();
  });

  // 4) Editar usuario: pedir datos al servlet
  $(document).on('click', '.btn_editar', function(){
    modo = 'editar';
    const id = $(this).data('id');
    $.post(baseUrl + '/RegUsuarioServlet',
      { opcion: 'editar_consultar', id },
      function(res) {
        if (res[0].resultado === 'exito') {
          const u = res[0].usuario;
          $('#idUsuario').val(u.id);
          $('#nombre').val(u.nombre);
          $('#username').val(u.username);
          $('#telefono').val(u.telefono);
          $('#password').val(''); // Siempre vacío
          cargarRoles(u.rolNombre);
          md.show();
        } else {
          alert('Usuario no encontrado');
        }
      }, 'json'
    );
  });

  // 5) Activar/Desactivar usuario
  $(document).on('click', '.btn_toggle', function(){
    const id = $(this).data('id');
    $.post(baseUrl + '/RegUsuarioServlet',
      { opcion: 'activar_desactivar', id },
      function(res){
        if (res[0].resultado === 'exito') {
          listar();
        } else {
          alert(res[0].mensaje || 'Error cambiando estado');
        }
      }, 'json'
    );
  });

  // 6) Guardar (Insertar o Actualizar)
  $('#btnGuardarUsr').click(() => {
    const datos = $('#formUsuario').serialize();
    const op = (modo === 'insertar') ? 'insertar' : 'si_actualizalo';
    $.post(baseUrl + '/RegUsuarioServlet',
      datos + '&opcion=' + op,
      function(res) {
        if (res[0].resultado === 'exito') {
          md.hide();
          listar();
        } else {
          alert(res[0].mensaje || 'Error al guardar usuario');
        }
      }, 'json'
    );
  });

  // 7) Al cargar la página, listar siempre
  listar();
});
