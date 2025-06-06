<%-- 
    Document   : laboratorios
    Created on : 30 may. 2025, 12:54:15
    Author     : vladi
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Gestión de Laboratorios</title>
        <!-- (1) Bootstrap CSS -->
        <link 
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" 
            rel="stylesheet"/>

        <link 
            rel="stylesheet" 
            href="css/sidebar.css" />
        <!-- 2) FontAwesome (para los íconos) -->
        <link
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"
            rel="stylesheet" />
     

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
                    <img
                        src="<%=request.getContextPath()%>/imagenes/logo_ues.png"
                        alt="Logo UES"
                        style="height:48px; width:auto;">
                </div>
                <!-- Títulos -->
                <div class="col ps-0">
                    <h1 class="h3 mb-1 text-dark fw-bold">TecMantUES</h1>
                    <h2 class="h5 text-secondary mb-0">Gestión de Laboratorios</h2>
                </div>
                <!-- Breadcrumb -->
                <div class="col-auto text-end">
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb mb-0">
                            <li class="breadcrumb-item">
                                <a href="<%=request.getContextPath()%>/Menu.jsp">Inicio</a>
                            </li>
                            <li class="breadcrumb-item active" aria-current="page">
                               Gestión de Laboratorios
                            </li>
                        </ol>
                    </nav>
                </div>
            </div>
        </div>
    </div>

        <div id="page-content-wrapper">
            <div class="container mt-1 ">
                <div div class="d-flex justify-content-end">
                    <button id="btnNuevo" class="btn btn-primary mb-3"> 
                       ️ <i class="fas fa-plus"></i> Nuevo</button>
                </div>
                <div id="tablaContainer"></div>

                <!-- Modal de Alta/Edición de Laboratorio -->
                <div class="modal fade" id="md_lab" tabindex="-1">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title">Laboratorio</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                            </div>
                            <div class="modal-body">
                                <form id="formLab">
                                    <input type="hidden" id="idLab" name="id"/>
                                    <div class="mb-2">
                                        <label>Nombre</label>
                                        <input type="text" id="nombre" name="nombre" class="form-control" required/>
                                    </div>
                                    <div class="mb-2">
                                        <label>Edificio</label>
                                        <input type="text" id="edificio" name="edificio" class="form-control" required/>
                                    </div>
                                    <div class="mb-2">
                                        <label>Piso</label>
                                        <input type="text" id="piso" name="piso" class="form-control" required/>
                                    </div>
                                </form>
                            </div>
                            <div class="modal-footer">
                                <button id="btnGuardar" class="btn btn-success">Guardar</button>
                                <button class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script> const baseUrl = '<%=request.getContextPath()%>';</script>
        <script src="<%=request.getContextPath()%>/js/registro_laboratorio.js"></script>
        <script 
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js">
        </script>

        <!-- (F) Bootstrap Bundle JS (Popper + Bootstrap) -->
        <script 
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        ></script>
    </body>
</html>

