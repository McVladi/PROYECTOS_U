<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.ues.edu.mantenimiento.logica.Usuario, com.ues.edu.mantenimiento.logica.Role" %>
<%
    // Recuperamos el Usuario completo de la sesión
    Usuario usr = (Usuario) session.getAttribute("usuarioConectado");
%>

<div class="border-end bg-dark" id="sidebar-wrapper">
  <!-- 1) Encabezado -->
  <div class="sidebar-heading text-center text-white py-3">
    TecMantUES
    <div class="border-top border-secondary my-2"></div>
  </div>

  <!-- 2) Lista de enlaces del sidebar -->
  <div class="list-group list-group-flush">

    <% if (usr != null) { %>
      <!-- Link a Tablero Principal -->
      <a href="<%= request.getContextPath() %>/Menu.jsp"
         class="list-group-item list-group-item-action bg-dark text-white
           <%= request.getRequestURI().endsWith("/Menu.jsp") ? "active" : "" %>">
        <i class="fas fa-home me-2"></i> Home
      </a>
        
         <!-- Usuarios sólo ROLE_ADMIN -->
      <% if (usr.getRol() == Role.ROLE_ADMIN) { %>
        <a href="<%= request.getContextPath() %>/usuarios.jsp"
           class="list-group-item list-group-item-action bg-dark text-white
             <%= request.getRequestURI().endsWith("/usuarios.jsp") ? "active" : "" %>">
          <i class="fas fa-user me-2"></i>Usuarios
        </a>
      <% } %>   

      <!-- Consulta Equipos (cualquier usuario autenticado) -->
      <a href="<%= request.getContextPath() %>/equipos.jsp"
         class="list-group-item list-group-item-action bg-dark text-white
           <%= request.getRequestURI().endsWith("/equipos.jsp") ? "active" : "" %>">
        <i class="fas fa-laptop me-2"></i>Consulta Equipos
      </a>
        
          

      <!-- Laboratorio: sólo ROLE_TECNICO o ROLE_ADMIN -->
       <% if (usr.getRol() == Role.ROLE_ADMIN || usr.getRol() == Role.ROLE_TECNICO) { %>
        <a href="<%= request.getContextPath() %>/laboratorios.jsp"
           class="list-group-item list-group-item-action bg-dark text-white
             <%= request.getRequestURI().endsWith("/tecnicos.jsp") ? "active" : "" %>">
          <i class="fas fa-flask me-2"></i>Laboratorios
        </a>
      <% } %>  
      

      <!-- Mantenimiento: sólo ROLE_TECNICO o ROLE_ADMIN -->
      <% if (usr.getRol() == Role.ROLE_TECNICO || usr.getRol() == Role.ROLE_ADMIN) { %>
        <a class="list-group-item list-group-item-action bg-dark text-white 
                  d-flex justify-content-between align-items-center
                  <%= request.getRequestURI().endsWith("/mantenimientos.jsp") ? "active" : "" %>"
           data-bs-toggle="collapse"
           href="#collapseMantenimiento"
           aria-expanded="<%= request.getRequestURI().endsWith("/mantenimientos.jsp") ? "true" : "false" %>"
           aria-controls="collapseMantenimiento">
          <span><i class="fas fa-tools me-2"></i>Mantenimiento</span>
          <i class="fas fa-chevron-down small"></i>
        </a>
        <div class="collapse bg-dark ps-4" id="collapseMantenimiento">
          <a href="<%= request.getContextPath() %>/mantenimientos.jsp"
             class="list-group-item list-group-item-action bg-dark text-white">
            <i class="fas fa-list me-2"></i>Ver Mantenimientos
          </a>
          <a href="#"
             class="list-group-item list-group-item-action bg-dark text-white"
             data-bs-toggle="modal"
             data-bs-target="#md_mantenimiento">
            <i class="fas fa-plus me-2"></i>Nuevo Mantenimiento
          </a>
        </div>
      <% } %>

      <!-- Técnicos: sólo ROLE_ADMIN -->
      <% if (usr.getRol() == Role.ROLE_ADMIN) { %>
        <a href="<%= request.getContextPath() %>/tecnicos.jsp"
           class="list-group-item list-group-item-action bg-dark text-white
             <%= request.getRequestURI().endsWith("/tecnicos.jsp") ? "active" : "" %>">
          <i class="fas fa-user-cog me-2"></i>Técnicos
        </a>
      <% } %>

      <!-- Divider + Menú de Usuario (Perfil / Logout) -->
      <div class="border-top border-secondary my-2"></div>
      <div class="list-group-item bg-dark text-white text-center">
        <div class="dropdown">
          <a href="#"
             class="d-flex align-items-center text-white text-decoration-none dropdown-toggle"
             id="dropdownUser"
             data-bs-toggle="dropdown"
             aria-expanded="false">
            <img src="https://via.placeholder.com/30"
                 alt="avatar"
                 class="rounded-circle me-2"
                 width="30"
                 height="30">
            <strong><%= usr.getUsername() %></strong>
          </a>
          <ul class="dropdown-menu dropdown-menu-dark text-small shadow"
              aria-labelledby="dropdownUser">
            <li>
              <a class="dropdown-item" href="#">
                <i class="fas fa-user me-2"></i>Perfil
              </a>
            </li>
            <li><hr class="dropdown-divider"></li>
            <li>
              <a class="dropdown-item" href="<%= request.getContextPath() %>/LogoutServlet">
                <i class="fas fa-sign-out-alt me-2"></i>Cerrar Sesión
              </a>
            </li>
          </ul>
        </div>
      </div>
    <% } else { %>
      <!-- Si no hay usuario en sesión, mostramos solo link a Login -->
      <a href="<%= request.getContextPath() %>/login.jsp"
         class="list-group-item list-group-item-action bg-dark text-white">
        <i class="fas fa-sign-in-alt me-2"></i>Iniciar Sesión
      </a>
    <% } %>

  </div>
</div>
