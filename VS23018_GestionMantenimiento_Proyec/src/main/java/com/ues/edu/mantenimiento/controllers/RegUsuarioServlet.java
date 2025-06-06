/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.ues.edu.mantenimiento.controllers;


import com.ues.edu.mantenimiento.logica.Role;
import com.ues.edu.mantenimiento.logica.Usuario;
import com.ues.edu.mantenimiento.persistencia.RolJpaController;
import com.ues.edu.mantenimiento.service.UsuarioService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author vladi
 */
@WebServlet(name = "RegUsuarioServlet", urlPatterns = {"/RegUsuarioServlet"})
public class RegUsuarioServlet extends HttpServlet {

    private final UsuarioService usuarioService = new UsuarioService();
    private final RolJpaController rolDao = new RolJpaController();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RegUsuarioServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegUsuarioServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/usuarios.jsp");
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");

        String op = request.getParameter("opcion");
        JSONArray array = new JSONArray();
        JSONObject json = new JSONObject();

        try {
            switch (op) {
                // 1) Devolver el <select> con todos los roles del enum
                case "cargarRoles":
                    StringBuilder sb = new StringBuilder();
                    for (Role r : Role.values()) {
                        sb.append("<option value='")
                          .append(r.name())
                          .append("'>")
                          .append(r.name())
                          .append("</option>");
                    }
                    json.put("resultado", "exito")
                        .put("roles", sb.toString());
                    break;

                // 2) Consultar todos los usuarios y devolver filas <tr>…</tr>
                case "consultar":
                    List<Usuario> lista = usuarioService.listarUsuarios();
                    StringBuilder tabla = new StringBuilder();
                    for (Usuario u : lista) {
                        String clsBtn = u.isActivo() ? "danger" : "success";
                        String txtBtn = u.isActivo() ? "Desactivar" : "Activar";
                        tabla.append("<tr>")
                                .append("<td>").append(u.getId()).append("</td>")
                                .append("<td>").append(u.getNombre()).append("</td>")
                                .append("<td>").append(u.getUsername()).append("</td>")
                                .append("<td>").append(u.getTelefono()).append("</td>")
                                .append("<td>").append(u.isActivo() ? "Activo" : "Inactivo").append("</td>")
                                .append("<td>").append(u.getRol().name()).append("</td>")
                                .append("<td>")
                                    .append("<button class='btn btn-sm btn-warning btn_editar' data-id='")
                                    .append(u.getId())
                                    .append("'>✏️</button> ")
                                    .append("<button class='btn btn-sm btn-")
                                    .append(clsBtn)
                                    .append(" btn_toggle' data-id='").append(u.getId()).append("'>")
                                    .append(txtBtn)
                                    .append("</button>")
                                .append("</td>")
                              .append("</tr>");
                    }
                    json.put("resultado", "exito")
                        .put("tabla", tabla.toString())
                        .put("cantidad", lista.size());
                    break;

                // 3) Insertar nuevo usuario
                case "insertar":
                    String nombreN = request.getParameter("nombre");
                    String usernameN = request.getParameter("username");
                    String pwdN = request.getParameter("password");
                    String telN = request.getParameter("telefono");
                    String rolN = request.getParameter("rol");
                    String estadoIns = usuarioService.registrarUsuario(
                                           nombreN, usernameN, pwdN, telN, rolN);
                    if ("usuario_existe".equals(estadoIns)) {
                        json.put("resultado", "error")
                            .put("mensaje", "El correo ya está registrado");
                    } else {
                        json.put("resultado", "exito");
                    }
                    break;

                // 4) Obtener datos de un usuario para llenar la modal
                case "editar_consultar":
                    Long idEd = Long.parseLong(request.getParameter("id"));
                    Usuario uEd = usuarioService.buscarPorId(idEd);
                    if (uEd != null) {
                        JSONObject ju = new JSONObject();
                        ju.put("id",        uEd.getId());
                        ju.put("nombre",    uEd.getNombre());
                        ju.put("username",  uEd.getUsername());
                        ju.put("telefono",  uEd.getTelefono());
                        ju.put("activo",    uEd.isActivo());
                        ju.put("rolNombre", uEd.getRol().name());
                        json.put("resultado", "exito")
                            .put("usuario", ju);
                    } else {
                        json.put("resultado", "error")
                            .put("mensaje", "Usuario no encontrado");
                    }
                    break;

                // 5) Actualizar usuario existente
                case "si_actualizalo":
                    Long idUp = Long.parseLong(request.getParameter("id"));
                    String nombreU = request.getParameter("nombre");
                    String usernameU = request.getParameter("username");
                    String pwdU = request.getParameter("password");
                    String telU = request.getParameter("telefono");
                    String rolU = request.getParameter("rol");

                    Usuario uUpd = usuarioService.buscarPorId(idUp);
                    if (uUpd != null) {
                        uUpd.setNombre(nombreU);
                        uUpd.setUsername(usernameU);
                        uUpd.setTelefono(telU);
                        // Solo re‐hashear si vino algo en password
                        if (pwdU != null && !pwdU.trim().isEmpty()) {
                            uUpd.setPasswordHash(usuarioService.hashPassword(pwdU));
                        }
                        try {
                            Role enumRol = Role.valueOf(rolU);
                            uUpd.setRol(enumRol);
                        } catch (IllegalArgumentException ex) {
                            uUpd.setRol(Role.ROLE_USER);
                        }
                        usuarioService.actualizarUsuario(uUpd);
                        json.put("resultado", "exito");
                    } else {
                        json.put("resultado", "error")
                            .put("mensaje", "No se pudo actualizar");
                    }
                    break;

                // 6) Activar/Desactivar al usuario (simplemente invierte “activo”)
                case "activar_desactivar":
                    Long idT = Long.parseLong(request.getParameter("id"));
                    Usuario uTog = usuarioService.buscarPorId(idT);
                    if (uTog != null) {
                        uTog.setActivo(!uTog.isActivo());
                        usuarioService.actualizarUsuario(uTog);
                        json.put("resultado", "exito");
                    } else {
                        json.put("resultado", "error")
                            .put("mensaje", "Usuario no encontrado");
                    }
                    break;

                default:
                    json.put("resultado", "error")
                        .put("mensaje", "Operación desconocida");
            }
        } catch (Exception ex) {
            json.put("resultado", "error")
                .put("mensaje", ex.getMessage());
        }

        array.put(json);
        try (PrintWriter out = response.getWriter()) {
            out.print(array.toString());
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
