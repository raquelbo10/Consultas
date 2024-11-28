/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.api.saude.controller;

import com.api.saude.model.Usuario;
import com.api.saude.service.UsuarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
   @Autowired
    UsuarioService usuarioService;
    
     @GetMapping("/buscar/{id}")
    public ResponseEntity<Usuario> carregarUsuario(@PathVariable Integer id) {
      Usuario usuario = usuarioService.buscarUsuarioPorId(id);
        return usuario != null ? ResponseEntity.ok(usuario) : ResponseEntity.notFound().build();
    }
  @GetMapping("buscar")
public ResponseEntity<Usuario> pesquisar(@RequestParam("user") String user, @RequestParam("senha") String senha) {
try{
    Usuario usuarioEncontrado = usuarioService.buscarPorUserESenha(user, senha);
    if (usuarioEncontrado != null) {
        return new ResponseEntity<>(usuarioEncontrado, HttpStatus.OK);
    } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
} catch (Exception e) {
        // Caso ocorra uma exceção, exibe o erro no log
        e.printStackTrace();
        
        // Retorna HTTP 500 (Erro Interno do Servidor)
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
@GetMapping("pesquisar-cpf")
public ResponseEntity<Usuario> pesquisarPorCpf(@RequestParam("cpf") String cpf ){
try{
    Usuario usuarioEncontrado = usuarioService.buscarUsuarioPorCpf(cpf);
    if (usuarioEncontrado != null) {
        return new ResponseEntity<>(usuarioEncontrado, HttpStatus.OK);
    } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
} catch (Exception e) {
        // Caso ocorra uma exceção, exibe o erro no log
        e.printStackTrace();
        
        // Retorna HTTP 500 (Erro Interno do Servidor)
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}


    @PostMapping("/adicionar")
    public ResponseEntity<Usuario> criar(@RequestBody Usuario usuario) {
        Usuario novoUsuario = usuarioService.cadastrarUsuario(usuario);
        return new ResponseEntity<>(novoUsuario, HttpStatus.CREATED);
    }

    @GetMapping("listar-todos")
    public ResponseEntity<List> listar() {
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Usuario> atualizar(@PathVariable Integer id, @RequestBody Usuario usuario) {
        Usuario usuarioAtualizado = usuarioService.atualizarUsuario(id, usuario);
        
        return new ResponseEntity<>(usuarioAtualizado, HttpStatus.OK);
    }

    @DeleteMapping("excluir/{id}")
    public ResponseEntity<?> deletar(@PathVariable Integer id) {
        usuarioService.excluirUsuario(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
 
}
