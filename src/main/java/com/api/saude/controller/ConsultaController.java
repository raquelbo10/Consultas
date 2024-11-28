/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.api.saude.controller;

import com.api.saude.model.Consulta;
import com.api.saude.model.Usuario;
import com.api.saude.service.ConsultaService;
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
@RequestMapping("/consulta")
public class ConsultaController {
    
    @Autowired
    ConsultaService consultaService;
    
    
    
     @GetMapping("/{id}")
    public ResponseEntity<Consulta> carregarConsulta(@PathVariable Integer id) {
      Consulta consulta = consultaService.buscaConsultaPorId(id);
        return consulta != null ? ResponseEntity.ok(consulta) : ResponseEntity.notFound().build();
    }
    @GetMapping("buscar/{id}")
    public ResponseEntity<Consulta> pesquisarConsulta(@PathVariable("id") Integer id) {
       Consulta consultaEncontrada = consultaService.buscaConsultaPorId(id);
        
        return new ResponseEntity<>(consultaEncontrada, HttpStatus.OK);
    }

    @PostMapping("/adicionar")
public ResponseEntity<?> salvarConsulta(@RequestBody Consulta consulta) {
    if (consulta.getUsuario() == null || consulta.getUsuario().getId() == null) {
        return ResponseEntity.badRequest().body("O campo 'usuario.id' é obrigatório.");
    }

    // Salvando a consulta
   consultaService.saveConsulta(consulta);
    return ResponseEntity.ok("Consulta salva com sucesso.");
}

    @GetMapping("listar-todas")
    public ResponseEntity<List> listarConsultas() {
        List<Consulta> consultas = consultaService.listaConsultas();
        return new ResponseEntity<>(consultas, HttpStatus.OK);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Consulta> atualizar(@PathVariable Integer id, @RequestBody Consulta consulta) {
        Consulta consultaAtualizada = consultaService.atualizaConsulta(id, consulta);
        
        return new ResponseEntity<>(consultaAtualizada, HttpStatus.OK);
    }

    @DeleteMapping("excluir/{id}")
    public ResponseEntity<?> deletar(@PathVariable Integer id) {
        consultaService.excluiConsulta(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
 
    
}
