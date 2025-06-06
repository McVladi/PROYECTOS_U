<%-- 
    Document   : modal_mantenimiento
    Created on : 31 may. 2025, 19:50:41
    Author     : vladi
--%>

<%-- modal_mantenimiento.jsp --%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!-- Modal de Mantenimiento (solo el HTML del modal) -->
<div class="modal fade" id="md_mantenimiento" tabindex="-1">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Mantenimiento</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
      </div>
      <div class="modal-body">
        <form id="formMant">
          <input type="hidden" id="idMant" name="id"/>
          <div class="mb-2">
            <label>Fecha Programada</label>
            <input type="date" id="fechaProgramada" name="fechaProgramada"
                   class="form-control" required/>
          </div>
          <div class="mb-2">
            <label>Fecha Real</label>
            <input type="date" id="fechaReal" name="fechaReal"
                   class="form-control"/>
          </div>
          <div class="mb-2">
            <label>Tipo</label>
            <select id="tipoMant" name="tipo" class="form-select" required>
              <option value="PREVENTIVO">PREVENTIVO</option>
              <option value="CORRECTIVO">CORRECTIVO</option>
            </select>
          </div>
          <div class="mb-2">
            <label>Observaciones</label>
            <textarea id="observaciones" name="observaciones"
                      class="form-control"></textarea>
          </div>
          <div class="mb-2">
            <label>Piezas Reemplazadas</label>
            <input type="text" id="piezasReemplazadas"
                   name="piezasReemplazadas"
                   class="form-control"/>
          </div>
          <div class="mb-2">
            <label>Equipo</label>
            <select id="equipoSelect" name="equipoId" class="form-select" required>
              <!-- Se llenará por AJAX en registro_mantenimiento.js -->
            </select>
          </div>
          <div class="mb-2">
            <label>Técnico</label>
            <select id="tecnicoSelect" name="tecnicoId" class="form-select" required>
              <!-- Se llenará por AJAX en registro_mantenimiento.js -->
            </select>
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button id="btnGuardarMant" class="btn btn-success">Guardar</button>
        <button type="button" class="btn btn-secondary"
                data-bs-dismiss="modal">Cerrar</button>
      </div>
    </div>
  </div>
</div>

