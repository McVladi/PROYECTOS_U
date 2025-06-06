/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.ues.edu.mantenimiento.controllers;

import com.ues.edu.mantenimiento.logica.Equipo;
import com.ues.edu.mantenimiento.logica.Mantenimiento;
import com.ues.edu.mantenimiento.logica.Tecnico;
import com.ues.edu.mantenimiento.service.EquipoService;
import com.ues.edu.mantenimiento.service.MantenimientoService;
import com.ues.edu.mantenimiento.service.TecnicoService;
import com.ues.edu.mantenimiento.logica.TipoMantenimiento;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
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
@WebServlet(name = "RegMantenimiento", urlPatterns = {"/RegMantenimiento"})
public class RegMantenimiento extends HttpServlet {

    private MantenimientoService service = new MantenimientoService();
    private EquipoService eqService = new EquipoService();
    private TecnicoService teService = new TecnicoService();
    private SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

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
            out.println("<title>Servlet RegMantenimiento</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegMantenimiento at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("/WEB-INF/jsp/mantenimientos.jsp")
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
            case "cargarCombos":
              
                StringBuilder sbEq = new StringBuilder();
                for (Equipo e : eqService.listarTodos()) {
                    sbEq.append("<option value='")
                            .append(e.getId()).append("'>")
                            .append(e.getTipo()).append(" – ")
                            .append(e.getNumeroSerie()).append(" – ")
                            .append(e.getModelo()).append(" – ")
                            .append(e.getMarca()).append(" – ")
                            .append(e.getLaboratorio().getNombre())
                            .append("</option>");
                }
               
                StringBuilder sbTe = new StringBuilder();
                for (Tecnico t : teService.listarTodos()) {
                    sbTe.append("<option value='")
                            .append(t.getId()).append("'>")
                            .append(t.getNombre())
                            .append("</option>");
                }
                json.put("resultado", "exito")
                        .put("equipos", sbEq.toString())
                        .put("tecnicos", sbTe.toString());
                break;

            case "consultar":
                List<Mantenimiento> list = service.listarTodos();
                StringBuilder tbl = new StringBuilder();
                tbl.append("<table class='table table-striped' id='tabla_mantenimientos'>")
                   .append("<thead><tr>")
                   .append("<th>ID</th><th>Fecha Programada</th><th>Fecha Real</th><th>Tipo De Mantenimiento</th>")
                   .append("<th>Observación</th><th>Piezas</th><th>Equipo_N Serie</th><th>Técnico</th><th>Acciones</th>")
                   .append("</tr></thead><tbody>");
                for (Mantenimiento m : list) {
                    tbl.append("<tr>")
                       .append("<td>").append(m.getId()).append("</td>")
                       .append("<td>").append(fmt.format(m.getFechaProgramada())).append("</td>")
                       .append("<td>")
                         .append(m.getFechaReal() != null ? fmt.format(m.getFechaReal()) : "")
                       .append("</td>")
                       .append("<td>").append(m.getTipo()).append("</td>")
                       .append("<td>").append(m.getObservaciones()).append("</td>")
                       .append("<td>").append(m.getPiezasReemplazadas()).append("</td>")
                       .append("<td>").append(m.getEquipo().getNumeroSerie()).append("</td>")
                       .append("<td>").append(m.getTecnico().getNombre()).append("</td>")
                       .append("<td>")
                         .append("<button class='btn btn-sm btn-primary btn_editar' data-id='")
                         .append(m.getId()).append("'>✏️</button> ")
                         .append("<button class='btn btn-sm btn-danger btn_eliminar' data-id='")
                         .append(m.getId()).append("'>🗑️</button>")
                       .append("</td>")
                       .append("</tr>");
                }
                tbl.append("</tbody></table>");
                json.put("resultado", "exito")
                    .put("tabla", tbl.toString());
                break;

            case "insertar":
                Mantenimiento ins = new Mantenimiento();
                ins.setFechaProgramada(fmt.parse(request.getParameter("fechaProgramada")));
                String fr = request.getParameter("fechaReal");
                if (fr != null && !fr.isEmpty()) {
                    ins.setFechaReal(fmt.parse(fr));
                }
                ins.setTipo(TipoMantenimiento.valueOf(request.getParameter("tipo")));
                ins.setObservaciones(request.getParameter("observaciones"));
                ins.setPiezasReemplazadas(request.getParameter("piezasReemplazadas"));

                Equipo eq = new Equipo();
                eq.setId(Long.parseLong(request.getParameter("equipoId")));
                ins.setEquipo(eq);

                Tecnico te = new Tecnico();
                te.setId(Long.parseLong(request.getParameter("tecnicoId")));
                ins.setTecnico(te);

                json.put("resultado",
                    service.crear(ins).equals("exito") ? "exito" : "error"
                );
                break;

            case "editar_consultar":
              
                Long idE = Long.parseLong(request.getParameter("id"));
               
                Mantenimiento m0 = service.buscarPorId(idE);
                if (m0 != null) {
                    JSONObject jm = new JSONObject();
                    jm.put("id", m0.getId())
                      .put("fechaProgramada", fmt.format(m0.getFechaProgramada()))
                      .put("fechaReal", m0.getFechaReal() != null ? fmt.format(m0.getFechaReal()) : "")
                      .put("tipo", m0.getTipo().toString())
                      .put("observaciones", m0.getObservaciones())
                      .put("piezasReemplazadas", m0.getPiezasReemplazadas())
                      .put("equipoId", m0.getEquipo().getId())
                      .put("tecnicoId", m0.getTecnico().getId());
                    json.put("resultado", "exito")
                        .put("mantenimiento", jm);
                } else {
                    json.put("resultado", "error")
                        .put("mensaje", "no encontrado");
                }
                break;

            case "si_actualizalo":
                Mantenimiento up = new Mantenimiento();
                up.setId(Long.parseLong(request.getParameter("id")));
                up.setFechaProgramada(fmt.parse(request.getParameter("fechaProgramada")));
                String fr2 = request.getParameter("fechaReal");
                if (fr2 != null && !fr2.isEmpty()) {
                    up.setFechaReal(fmt.parse(fr2));
                }
                up.setTipo(TipoMantenimiento.valueOf(request.getParameter("tipo")));
                up.setObservaciones(request.getParameter("observaciones"));
                up.setPiezasReemplazadas(request.getParameter("piezasReemplazadas"));

                Equipo eq2 = new Equipo();
                eq2.setId(Long.parseLong(request.getParameter("equipoId")));
                up.setEquipo(eq2);

                Tecnico te2 = new Tecnico();
                te2.setId(Long.parseLong(request.getParameter("tecnicoId")));
                up.setTecnico(te2);

                json.put("resultado",
                    service.actualizar(up).equals("exito") ? "exito" : "error"
                );
                break;

            case "eliminar":
               
                Long idD = Long.parseLong(request.getParameter("id"));
                json.put("resultado",
                    service.eliminar(idD).equals("exito") ? "exito" : "error"
                );
                break;

            default:
                json.put("resultado", "error")
                    .put("mensaje", "operación desconocida");
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
