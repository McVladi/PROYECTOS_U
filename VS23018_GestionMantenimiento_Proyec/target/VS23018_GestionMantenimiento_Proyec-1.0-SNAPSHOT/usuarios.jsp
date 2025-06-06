<%-- 
    Document   : usuarios
    Created on : 2 jun. 2025, 00:46:06
    Author     : vladi
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Gestión de Usuarios</title>
       <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"/>
        <link rel="stylesheet" href="css/sidebar.css" />
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet" />


        <style>
            .sidebar-heading {
                background-color: transparent !important;
            }

            .list-group-item.active {
                background-color: rgba(0, 123, 255, 0.5) !important;
            }
        </style>
    </head>
    <body>
        <jsp:include page="sidebar.jsp" />
        <div id="page-content-wrapper">
            <div class="container-fluid bg-light border-bottom mb-4">
                <div class="row py-3 align-items-center">
                    <!-- Logo UES -->
                    <div class="col-auto">
                        <img src="<%=request.getContextPath()%>/imagenes/logo_ues.png" alt="Logo UES" style="height:48px; width:auto;">
                    </div>

                    <!-- Títulos -->
                    <div class="col ps-0">
                        <h1 class="h3 mb-1 text-dark fw-bold">TecMantUES</h1>
                        <h2 class="h5 text-secondary mb-0">Gestión de Usuarios</h2>
                    </div>

                    <!-- Breadcrumb -->
                    <div class="col-auto text-end">
                        <nav aria-label="breadcrumb">
                            <ol class="breadcrumb mb-0">
                                <li class="breadcrumb-item">
                                    <a href="<%=request.getContextPath()%>/Menu.jsp">Inicio</a>
                                </li>
                                <li class="breadcrumb-item active" aria-current="page">
                                    Gestión de Usuarios
                                </li>
                            </ol>
                        </nav>
                    </div>
                </div>
            </div>
        </div>
        <div id="page-content-wrapper">
            <div class="container mt-1">
            <div class="d-flex justify-content-end mb-3">
                <button id="btnNuevoUsr" class="btn btn-primary">+ Nuevo Usuario</button>
            </div>
            <div class="container-fluid">
                <table class="table table-striped table-bordered">
                    <thead class="table-dark">
                        <tr>
                            <th>ID</th>
                            <th>Nombre</th>
                            <th>Correo</th>
                            <th>Teléfono</th>
                            <th>Estado</th>
                            <th>Rol</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody id="tablaUsuarios">
                        <!-- Rellenado dinámico vía AJAX -->
                    </tbody>
                </table>
            </div>
        </div>


        <!-- Modal: Alta/Edición de Usuario -->
        <div class="modal fade" id="md_usuario" tabindex="-1">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Usuario</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                    </div>
                    <div class="modal-body">
                        <form id="formUsuario">
                            <input type="hidden" id="idUsuario" name="id" />

                            <div class="mb-2">
                                <label for="nombre" class="form-label">Nombre</label>
                                <input type="text" id="nombre" name="nombre" class="form-control" required />
                            </div>

                            <div class="mb-2">
                                <label for="username" class="form-label">Correo (username)</label>
                                <input type="email" id="username" name="username" class="form-control" required />
                            </div>

                            <div class="mb-2">
                                <label for="telefono" class="form-label">Teléfono</label>
                                <input type="text" id="telefono" name="telefono" class="form-control" />
                            </div>

                            <div class="mb-2">
                                <label for="password" class="form-label">Contraseña</label>
                                <input type="password" id="password" name="password" class="form-control" />
                                <small class="text-muted">Dejar vacío para no cambiar</small>
                            </div>

                            <div class="mb-2">
                                <label for="rol" class="form-label">Rol</label>
                                <select id="rol" name="rol" class="form-select" required>
                                    <!-- Se llenará vía AJAX (cargarRoles) -->
                                </select>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button id="btnGuardarUsr" class="btn btn-success">Guardar</button>
                        <button class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                    </div>
                </div>
            </div>
        </div>

        <!-- Scripts -->
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script>const baseUrl = '<%=request.getContextPath()%>';</script>
        <script src="<%=request.getContextPath()%>/js/registro_usuario.js"></script>
        <script 
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js">
        </script>
    </body>
</html>

