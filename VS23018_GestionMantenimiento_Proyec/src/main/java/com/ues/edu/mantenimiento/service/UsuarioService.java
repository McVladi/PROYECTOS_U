/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ues.edu.mantenimiento.service;

import com.ues.edu.mantenimiento.logica.Usuario;
import com.ues.edu.mantenimiento.logica.Role;
import com.ues.edu.mantenimiento.persistencia.UsuarioJpaController;
import com.ues.edu.mantenimiento.persistencia.RolJpaController;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.util.List;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author vladi
 */
public class UsuarioService {

    private final UsuarioJpaController userDao = new UsuarioJpaController();
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // 1) Listar todos
    public List<Usuario> listarUsuarios() {
        return userDao.findUsuarioEntities();
    }

    // 2) Buscar por ID
    public Usuario buscarPorId(Long id) {
        return userDao.findUsuario(id);
    }

    // 3) Registrar un usuario nuevo
    //    username = correo, pwdPlano = contraseña, rolNombre = string del enum (ej. "ROLE_ADMIN")
    public String registrarUsuario(String nombre,
                                   String username,
                                   String pwdPlano,
                                   String telefono,
                                   String rolNombre) {
        // Validar que no exista otro con mismo username
        if (userDao.findByUsername(username) != null) {
            return "usuario_existe";
        }
        Usuario u = new Usuario();
        u.setNombre(nombre);
        u.setUsername(username);
        u.setPasswordHash(hashPassword(pwdPlano));
        u.setTelefono(telefono);
        u.setActivo(true);

        try {
            Role enumRol = Role.valueOf(rolNombre);
            u.setRol(enumRol);
        } catch (IllegalArgumentException ex) {
            // Si el rol no coincide, asignamos por defecto ROLE_USER
            u.setRol(Role.ROLE_USER);
        }

        return userDao.create(u);
    }

    // 4) Autenticar usuario (ejemplo de login)
    public Usuario autenticar(String username, String pwdPlano) {
        Usuario u = userDao.findByUsername(username);
        if (u == null || !u.isActivo()) {
            return null;
        }
        // bcrypt.matches compara el plain con el hash
        if (encoder.matches(pwdPlano, u.getPasswordHash())) {
            return u;
        } else {
            return null;
        }
    }

    // 5) Actualizar usuario existente
    public String actualizarUsuario(Usuario u) {
        return userDao.update(u);
    }

    // 6) Hashear contraseña
    public String hashPassword(String plainPassword) {
        return encoder.encode(plainPassword);
    }

}
