<%-- 
    Document   : login
    Created on : 1 jun. 2025, 12:36:42
    Author     : vladi
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Login - TecMantUES</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    </head>
    <body class="d-flex align-items-center justify-content-center" style="height:100vh; background:#f5f5f5;">
        <div class="card p-4 shadow-sm" style="width: 320px;">
            <h4 class="card-title text-center mb-3">Iniciar Sesión</h4>

            <% if (request.getAttribute("error") != null) {%>
            <div class="alert alert-danger py-1">
                <small><%= request.getAttribute("error")%></small>
            </div>
            <% }%>

            <form method="post" action="<%=request.getContextPath()%>/LoginServlet">
                <div class="mb-3">
                    <label for="username" class="form-label">Usuario (correo)</label>
                    <input 
                        type="text" 
                        id="username" 
                        name="username" 
                        class="form-control" 
                        required 
                        autofocus />
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Contraseña</label>
                    <input 
                        type="password" 
                        id="password" 
                        name="password" 
                        class="form-control" 
                        required />
                </div>
                <button type="submit" class="btn btn-primary w-100">Entrar</button>
            </form>

            <div class="text-center mt-3">
                <button type="button" 
                        class="btn btn-link" 
                        data-bs-toggle="modal"
                        data-bs-target="#md_usuario">
                    <i class="fas fa-user-plus me-1"></i>Registrar nuevo usuario
                </button>
            </div>
        </div>

        <!-- Modal de Registro (copiado de usuarios.jsp) -->
        <div class="modal fade" id="md_usuario" tabindex="-1">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Registro de Usuario</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                    </div>
                    <div class="modal-body">
                        <form id="formUsuarioLogin">
                            <input type="hidden" id="idUsuario" name="id" value="" />

                            <div class="mb-2">
                                <label for="nombre" class="form-label">Nombre</label>
                                <input type="text" id="nombre" name="nombre" class="form-control" required />
                            </div>

                            <div class="mb-2">
                                <label for="usernameReg" class="form-label">Correo (username)</label>
                                <input type="email" id="usernameReg" name="username" class="form-control" required />
                            </div>

                            <div class="mb-2">
                                <label for="telefono" class="form-label">Teléfono</label>
                                <input type="text" id="telefono" name="telefono" class="form-control" />
                            </div>

                            <div class="mb-2">
                                <label for="passwordReg" class="form-label">Contraseña</label>
                                <input type="password" id="passwordReg" name="password" class="form-control" required />
                            </div>

                            <div class="mb-2">
                                <label for="rol" class="form-label">Rol</label>
                                <select id="rol" name="rol" class="form-select" required>
                                    <option value="">Cargando roles...</option>
                                </select>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button id="btnRegistrarUsr" class="btn btn-success">Registrar</button>
                        <button class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                    </div>
                </div>
            </div>
        </div>

        <!-- Scripts -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script>
            const baseUrl = '<%=request.getContextPath()%>';
            
            $(document).ready(function() {
               
                $('#md_usuario').on('show.bs.modal', function() {
                    cargarRoles();
                });
                
              
                function cargarRoles() {
                    $.post(baseUrl + '/RegUsuarioServlet',
                        { opcion: 'cargarRoles' },
                        function(res) {
                            if (res[0].resultado === 'exito') {
                                $('#rol').html(res[0].roles);
                            } else {
                                $('#rol').html('<option value="">Error cargando roles</option>');
                            }
                        }, 'json'
                    ).fail(function() {
                        $('#rol').html('<option value="">Error al conectar con el servidor</option>');
                    });
                }
                
              
                $('#btnRegistrarUsr').click(function() {
                    const formData = $('#formUsuarioLogin').serialize() + '&opcion=insertar';
                    
                    $.post(baseUrl + '/RegUsuarioServlet',
                        formData,
                        function(res) {
                            if (res[0].resultado === 'exito') {
                                alert('Usuario registrado con éxito');
                                $('#md_usuario').modal('hide');
                                $('#formUsuarioLogin')[0].reset();
                            } else {
                                alert(res[0].mensaje || 'Error al registrar usuario');
                            }
                        }, 'json'
                    ).fail(function() {
                        alert('Error al conectar con el servidor');
                    });
                });
            });
        </script>
    </body>
</html>
