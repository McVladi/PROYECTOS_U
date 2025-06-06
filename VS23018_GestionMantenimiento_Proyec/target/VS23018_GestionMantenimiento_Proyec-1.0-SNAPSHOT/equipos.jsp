<%-- 
    Document   : equipos.jsp
    Created on : 20 may. 2025, 21:29:53
    Author     : vladi
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Equipos por Laboratorio</title>

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
                    <h2 class="h5 text-secondary mb-0">Gestión de Equipos</h2>
                </div>
                <!-- Breadcrumb -->
                <div class="col-auto text-end">
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb mb-0">
                            <li class="breadcrumb-item">
                                <a href="<%=request.getContextPath()%>/Menu.jsp">Inicio</a>
                            </li>
                            <li class="breadcrumb-item active" aria-current="page">
                                Gestión de Equipos
                            </li>
                        </ol>
                    </nav>
                </div>
            </div>
        </div>
    </div>

    <div id="page-content-wrapper">



        <div class="row mb-3">
            <label for="labSelect" class="col-sm-2 col-form-label">Laboratorio:</label>
            <div class="col-sm-10">
                <select id="labSelect" class="form-select">
                    <option value="1">Laboratorio Ciencias</option>
                    <option value="2">Laboratorio Informática</option>
                </select>
            </div>
        </div>

        <div class="mb-4">
            <input 
                type="text" 
                id="busqueda" 
                class="form-control" 
                placeholder="Buscar por marca o modelo...">
        </div>

        <table class="table table-striped table-bordered">
            <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Tipo</th>
                    <th>Marca</th>
                    <th>Modelo</th>
                    <th>Serie</th>
                    <th>Estado</th>
                </tr>
            </thead>
            <tbody id="tablaEquipos">

            </tbody>
        </table>
    </div>


    <script 
        src="https://code.jquery.com/jquery-3.6.0.min.js"
    ></script>

    <script 
    src="<%= request.getContextPath()%>/js/equipos.js"></script>

    <!-- (F) Bootstrap Bundle JS (Popper + Bootstrap) -->
    <script 
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
    ></script>
</body>
</html>
