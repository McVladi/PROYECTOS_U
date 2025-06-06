/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.ues.edu.mantenimiento.controllers;

import com.ues.edu.mantenimiento.logica.Equipo;
import com.ues.edu.mantenimiento.logica.Mantenimiento;
import com.ues.edu.mantenimiento.logica.Usuario;
import com.ues.edu.mantenimiento.persistencia.EquipoJpaController;
import com.ues.edu.mantenimiento.persistencia.MantenimientoJpaController;
import com.ues.edu.mantenimiento.persistencia.UsuarioJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

/**
 *
 * @author vladi
 */
@WebServlet(name = "EstadisticasServlet", urlPatterns = {"/EstadisticasServlet"})
public class EstadisticasServlet extends HttpServlet {

    private final UsuarioJpaController usuarioDao = new UsuarioJpaController();
    private final EquipoJpaController equipoDao = new EquipoJpaController();
    private final MantenimientoJpaController mantDao = new MantenimientoJpaController();

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
            out.println("<title>Servlet EstadisticasServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EstadisticasServlet at " + request.getContextPath() + "</h1>");
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

        doPost(request, response);
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
        response.setContentType("application/json;charset=UTF-8");

        JSONObject json = new JSONObject();

        try {
            // Usar los métodos específicos de conteo que ya tienes
            long totalUsuarios = usuarioDao.countUsuariosActivos();
            long totalEquipos = equipoDao.countEquipos();
            long totalMant = mantDao.countMantPendientes();

            json.put("usuarios", totalUsuarios);
            json.put("equipos", totalEquipos);
            json.put("mant", totalMant);

            // Debug en consola del servidor
            System.out.println("Estadísticas generadas - Usuarios: " + totalUsuarios
                    + ", Equipos: " + totalEquipos
                    + ", Mantenimientos: " + totalMant);
        } catch (Exception e) {
            System.err.println("Error al generar estadísticas: " + e.getMessage());
            json.put("usuarios", 0);
            json.put("equipos", 0);
            json.put("mant", 0);
        }

        try (PrintWriter out = response.getWriter()) {
            out.print(json.toString());
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
