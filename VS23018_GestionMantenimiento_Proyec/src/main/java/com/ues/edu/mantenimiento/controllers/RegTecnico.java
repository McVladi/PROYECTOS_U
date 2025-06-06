/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.ues.edu.mantenimiento.controllers;

import com.ues.edu.mantenimiento.logica.Tecnico;
import com.ues.edu.mantenimiento.service.TecnicoService;
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
@WebServlet(name = "RegTecnico", urlPatterns = {"/RegTecnico"})
public class RegTecnico extends HttpServlet {
    
    private TecnicoService service = new TecnicoService();

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
            out.println("<title>Servlet RegTecnico</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegTecnico at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("/WEB-INF/jsp/tecnicos.jsp")
               .forward(request, response);
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
        JSONArray  array = new JSONArray();
        JSONObject json  = new JSONObject();

        try {
            switch (op) {
                case "consultar":
                    List<Tecnico> lista = service.listarTodos();
                    StringBuilder tabla = new StringBuilder();
                    tabla.append("<table class='table table-striped' id='tabla_tecnicos'>")
                         .append("<thead><tr>")
                         .append("<th>ID</th><th>Nombre</th><th>IDen.</th>")
                         .append("<th>Esp.</th><th>Tel.</th><th>Email</th><th>Acciones</th>")
                         .append("</tr></thead><tbody>");

                    for (Tecnico t : lista) {
                        tabla.append("<tr>")
                             .append("<td>").append(t.getId()).append("</td>")
                             .append("<td>").append(t.getNombre()).append("</td>")
                             .append("<td>").append(t.getIdentificacion()).append("</td>")
                             .append("<td>").append(t.getEspecialidad()).append("</td>")
                             .append("<td>").append(t.getTelefono()).append("</td>")
                             .append("<td>").append(t.getEmail()).append("</td>")
                             .append("<td>")
                               // botón de editar 
                               .append("<button class='btn btn-sm btn-primary btn_editar' data-id='")
                               .append(t.getId())
                               .append("'>✏️</button> ")
                               // botón de eliminar
                               .append("<button class='btn btn-sm btn-danger btn_eliminar' data-id='")
                               .append(t.getId())
                               .append("'>🗑️</button>")
                             .append("</td>")
                             .append("</tr>");
                    }

                    tabla.append("</tbody></table>");
                    json.put("resultado", "exito")
                        .put("tabla", tabla.toString())
                        .put("cantidad", lista.size());
                    break;

                case "insertar":
                    Tecnico nuevo = new Tecnico();
                    nuevo.setNombre(       request.getParameter("nombre"));
                    nuevo.setIdentificacion(request.getParameter("identificacion"));
                    nuevo.setEspecialidad(  request.getParameter("especialidad"));
                    nuevo.setTelefono(      request.getParameter("telefono"));
                    nuevo.setEmail(         request.getParameter("email"));
                    String resIns = service.crear(nuevo);
                    json.put("resultado", resIns.equals("exito") ? "exito" : "error");
                    break;

                case "editar_consultar":
                    long idEd = Long.parseLong(request.getParameter("id"));
                   Tecnico te = service.buscarPorId(idEd);      
                    if (te != null) {
                        JSONObject jt = new JSONObject();
                        jt.put("id",             te.getId())
                          .put("nombre",         te.getNombre())
                          .put("identificacion", te.getIdentificacion())
                          .put("especialidad",   te.getEspecialidad())
                          .put("telefono",       te.getTelefono())
                          .put("email",          te.getEmail());
                        json.put("resultado", "exito")
                            .put("tecnico", jt);
                    } else {
                        json.put("resultado", "error")
                            .put("mensaje", "Técnico no encontrado");
                    }
                    break;

                case "si_actualizalo":
                    Tecnico upd = new Tecnico();
                     upd.setId(Long.parseLong(request.getParameter("id")));  
                    upd.setNombre(           request.getParameter("nombre"));
                    upd.setIdentificacion(   request.getParameter("identificacion"));
                    upd.setEspecialidad(     request.getParameter("especialidad"));
                    upd.setTelefono(         request.getParameter("telefono"));
                    upd.setEmail(            request.getParameter("email"));
                    String resUp = service.actualizar(upd);
                    json.put("resultado", resUp.equals("exito") ? "exito" : "error");
                    break;

                case "eliminar":
                    long idDel = Long.parseLong(request.getParameter("id"));
                    String resDel = service.eliminar((int) idDel);
                    json.put("resultado", resDel.equals("exito") ? "exito" : "error");
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
