/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.api.saude.service;

import com.api.saude.model.Usuario;
import com.api.saude.model.UsuarioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;
    
     public Usuario buscarUsuarioPorId(Integer id){
        return usuarioRepository.findById(id).orElseThrow();
    }
      public Usuario buscarPorUserESenha(String user, String senha){
        return usuarioRepository.findByUserAndSenha(user, senha)
            .orElseThrow(() -> new RuntimeException("Usuário ou senha inválidos."));
    }
       public Usuario buscarUsuarioPorCpf(String cpf){
       return usuarioRepository.findByCpf(cpf)
       .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
       }
    
    public Usuario cadastrarUsuario (Usuario usuario){
        usuario.setId(null);
        usuarioRepository.save(usuario);
        return usuario;
    }
    
    public List<Usuario> listarUsuarios(){
        return usuarioRepository.findAll();
    }
    
    public Usuario atualizarUsuario(Integer id, Usuario usuario){
        Usuario usuarioEncontrado = buscarUsuarioPorId(id);
        usuarioEncontrado.setId(id);
        usuario.setNome(usuario.getNome());
        usuario.setSobrenome(usuario.getSobrenome());
        usuario.setUser(usuario.getUser());
        usuario.setSenha(usuario.getSenha());
        usuario.setCpf(usuario.getCpf());
        usuario.setGenero(usuario.getGenero());
        usuario.setTel(usuario.getTel());
        
        usuarioRepository.save(usuarioEncontrado);
        return usuarioEncontrado;
    }
    
    public void excluirUsuario(Integer id){
        Usuario usuarioEncontrado = buscarUsuarioPorId(id);
        usuarioRepository.deleteById(usuarioEncontrado.getId());
    } 
}
