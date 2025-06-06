<%-- 
    Document   : Menu
    Created on : 31 may. 2025, 19:32:07
    Author     : vladi
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="com.ues.edu.mantenimiento.logica.Usuario" %>
<%
    
    Usuario usr = (Usuario) session.getAttribute("usuarioConectado");
    if (usr == null) {
        
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Tablero Principal - TecMantUES</title>
    
    <link 
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" 
      rel="stylesheet" />
  
    <link
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"
      rel="stylesheet" />
    
    <link rel="stylesheet" href="css/sidebar.css" />

    <style>
    
      #page-content-wrapper {
        margin-left: 250px; 
        padding: 20px;
      }
      @media (max-width: 768px) {
        #page-content-wrapper { margin-left: 0; }
      }
    </style>
</head>
<body>
    <!-- Incluir el sidebar fijo -->
    <jsp:include page="sidebar.jsp" />

    
    <div id="page-content-wrapper">
      <div class="container-fluid">
        <div class="row mb-4">
          <div class="col">
            <h1 class="h3">¡Bienvenido, <%= usr.getNombre() != null ? usr.getNombre() : usr.getUsername() %>!</h1>
            <p class="text-muted">Esto es tu Tablero Principal</p>
          </div>
        </div>

       
        <div class="row gy-4">
         
          <div class="col-md-4">
            <div class="card text-white bg-primary h-100">
              <div class="card-body d-flex">
                <div class="me-3 align-self-center">
                  <i class="fas fa-users fa-3x"></i>
                </div>
                <div>
                  <h5 class="card-title">Usuarios Activos</h5>
                  <h2 class="card-text" id="cntUsuarios">0</h2>
                </div>
              </div>
              <div class="card-footer bg-transparent">
                <small><i class="fas fa-sync-alt me-1"></i>Actualizado recientemente</small>
              </div>
            </div>
          </div>

        
          <div class="col-md-4">
            <div class="card text-white bg-success h-100">
              <div class="card-body d-flex">
                <div class="me-3 align-self-center">
                  <i class="fas fa-desktop fa-3x"></i>
                </div>
                <div>
                  <h5 class="card-title">Equipos Registrados</h5>
                  <h2 class="card-text" id="cntEquipos">0</h2>
                </div>
              </div>
              <div class="card-footer bg-transparent">
                <small><i class="fas fa-sync-alt me-1"></i>Actualizado recientemente</small>
              </div>
            </div>
          </div>

        
          <div class="col-md-4">
            <div class="card text-white bg-warning h-100">
              <div class="card-body d-flex">
                <div class="me-3 align-self-center">
                  <i class="fas fa-tools fa-3x"></i>
                </div>
                <div>
                  <h5 class="card-title">Mantenimientos Pendientes</h5>
                  <h2 class="card-text" id="cntMant">0</h2>
                </div>
              </div>
              <div class="card-footer bg-transparent">
                <small><i class="fas fa-sync-alt me-1"></i>Actualizado recientemente</small>
              </div>
            </div>
          </div>
        </div>

       
        <div class="row mt-5">
          <div class="col-md-4 mb-3">
            <a href="usuarios.jsp" class="text-decoration-none">
              <div class="card border-secondary h-100">
                <div class="card-body text-center">
                  <i class="fas fa-user fa-2x text-secondary mb-2"></i>
                  <h5 class="card-title">Gestionar Usuarios</h5>
                </div>
              </div>
            </a>
          </div>
          <div class="col-md-4 mb-3">
            <a href="equipos.jsp" class="text-decoration-none">
              <div class="card border-secondary h-100">
                <div class="card-body text-center">
                  <i class="fas fa-desktop fa-2x text-secondary mb-2"></i>
                  <h5 class="card-title">Consultar Equipos</h5>
                </div>
              </div>
            </a>
          </div>
          <div class="col-md-4 mb-3">
            <a href="mantenimientos.jsp" class="text-decoration-none">
              <div class="card border-secondary h-100">
                <div class="card-body text-center">
                  <i class="fas fa-calendar-check fa-2x text-secondary mb-2"></i>
                  <h5 class="card-title">Ver Mantenimientos</h5>
                </div>
              </div>
            </a>
          </div>
        </div>
      </div>
    </div>

    
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    
    <script>
      const baseUrl = '<%= request.getContextPath() %>';
    </script>
   
    <script src="<%= request.getContextPath() %>/js/menu.js"></script>
    
    <script 
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js">
    </script>
</body>
</html>

