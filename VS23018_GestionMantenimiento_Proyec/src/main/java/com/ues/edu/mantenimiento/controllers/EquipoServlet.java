        /*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
     */
    package com.ues.edu.mantenimiento.controllers;

    import com.ues.edu.mantenimiento.logica.Equipo;
    import com.ues.edu.mantenimiento.service.EquipoService;
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

    @WebServlet(name = "EquipoServlet", urlPatterns = {"/EquipoServlet"})
    public class EquipoServlet extends HttpServlet {

        private EquipoService service = new EquipoService();

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
                out.println("<title>Servlet EquipoServlet</title>");            
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Servlet EquipoServlet at " + request.getContextPath() + "</h1>");
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
             request.getRequestDispatcher("/WEB-INF/jsp/equipos.jsp")
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
             String op = request.getParameter("op");
            if ("listar".equals(op)) {
                Long labId = Long.parseLong(request.getParameter("labId"));
                List<Equipo> lista = service.listarPorLaboratorio(labId);

                JSONArray arr = new JSONArray();
                for (Equipo e : lista) {
                    JSONObject obj = new JSONObject();
                    obj.put("id", e.getId());
                    obj.put("tipo", e.getTipo());
                    obj.put("marca", e.getMarca());
                    obj.put("modelo", e.getModelo());
                    obj.put("serie", e.getNumeroSerie());
                    obj.put("estado", e.getEstado().toString());
                    arr.put(obj);
                }
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().write(arr.toString());
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
