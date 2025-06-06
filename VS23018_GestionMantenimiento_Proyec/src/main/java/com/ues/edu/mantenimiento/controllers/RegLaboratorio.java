/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.ues.edu.mantenimiento.controllers;

import com.ues.edu.mantenimiento.logica.Laboratorio;
import com.ues.edu.mantenimiento.service.LaboratorioService;
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
@WebServlet(name = "RegLaboratorio", urlPatterns = {"/RegLaboratorio"})
public class RegLaboratorio extends HttpServlet {

    private LaboratorioService service = new LaboratorioService();

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
            out.println("<title>Servlet RegLaboratorio</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegLaboratorio at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("/WEB-INF/jsp/laboratorios.jsp")
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
        JSONArray array = new JSONArray();
        JSONObject json = new JSONObject();

        try {
            switch (op) {
                case "consultar":
                    List<Laboratorio> list = service.listarTodos();
                    StringBuilder tbl = new StringBuilder()
                            .append("<table class='table table-striped' id='tabla_labs'>")
                            .append("<thead><tr>")
                            .append("<th>ID</th><th>Nombre</th><th>Edificio</th><th>Piso</th><th>Acciones</th>")
                            .append("</tr></thead><tbody>");
                    for (Laboratorio l : list) {
                        tbl.append("<tr>")
                                .append("<td>").append(l.getId()).append("</td>")
                                .append("<td>").append(l.getNombre()).append("</td>")
                                .append("<td>").append(l.getEdificio()).append("</td>")
                                .append("<td>").append(l.getPiso()).append("</td>")
                                .append("<td>")
                                .append("<button class='btn btn-sm btn-primary btn_editar' data-id='")
                                .append(l.getId()).append("'>✏️</button> ")
                                .append("<button class='btn btn-sm btn-danger btn_eliminar' data-id='")
                                .append(l.getId()).append("'>🗑️</button>")
                                .append("</td>")
                                .append("</tr>");
                    }
                    tbl.append("</tbody></table>");
                    json.put("resultado", "exito")
                            .put("tabla", tbl.toString());
                    break;

                case "insertar":
                    Laboratorio nuevo = new Laboratorio();
                    nuevo.setNombre(request.getParameter("nombre"));
                    nuevo.setEdificio(request.getParameter("edificio"));
                    nuevo.setPiso(request.getParameter("piso"));
                    json.put("resultado",
                            service.crear(nuevo).equals("exito") ? "exito" : "error"
                    );
                    break;

                case "editar_consultar":
                    Long idE = Long.parseLong(request.getParameter("id"));
                    Laboratorio l = service.buscarPorId(idE);
                    if (l != null) {
                        JSONObject jl = new JSONObject()
                                .put("id", l.getId())
                                .put("nombre", l.getNombre())
                                .put("edificio", l.getEdificio())
                                .put("piso", l.getPiso());
                        json.put("resultado", "exito")
                                .put("laboratorio", jl);
                    } else {
                        json.put("resultado", "error").put("mensaje", "No encontrado");
                    }
                    break;

                case "si_actualizalo":
                    
                    Long idToUpdate = Long.parseLong(request.getParameter("id"));
                    Laboratorio existente = service.buscarPorId(idToUpdate);

                    if (existente != null) {
                       
                        existente.setNombre(request.getParameter("nombre"));
                        existente.setEdificio(request.getParameter("edificio"));
                        existente.setPiso(request.getParameter("piso"));
                        String resUp = service.actualizar(existente);
                        json.put("resultado", resUp.equals("exito") ? "exito" : "error");
                    } else {
                        json.put("resultado", "error")
                                .put("mensaje", "Laboratorio no encontrado");
                    }
                    break;
                case "eliminar":

                    Long idD = Long.parseLong(request.getParameter("id"));
                    json.put("resultado",
                            service.eliminar(idD).equals("exito") ? "exito" : "error"
                    );
                    break;

                default:
                    json.put("resultado", "error").put("mensaje", "Operación desconocida");
            }
        } catch (Exception ex) {
            json.put("resultado", "error").put("mensaje", ex.getMessage());
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
