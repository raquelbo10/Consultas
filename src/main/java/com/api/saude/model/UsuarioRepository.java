/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.api.saude.model;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author leona
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
     Optional<Usuario> findByUserAndSenha(String user, String senha);
     Optional<Usuario> findByCpf(String cpf);
}
