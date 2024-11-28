/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.api.saude.service;

import com.api.saude.model.Consulta;
import com.api.saude.model.ConsultaRepository;
import com.api.saude.model.Usuario;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultaService {
    
   @Autowired
   ConsultaRepository consultaRepository;
   @Autowired 
   UsuarioService usuarioService;
   public Consulta buscaConsultaPorId(Integer id){
        return consultaRepository.findById(id).orElseThrow();
    }
    
    
    public List<Consulta> listaConsultas(){
        return consultaRepository.findAllByOrderByDataAsc();
    }
    
    public Consulta atualizaConsulta(Integer id, Consulta consulta){
       Consulta consultaEncontrada = buscaConsultaPorId(id);
        consultaEncontrada.setId(id);
        consulta.setUsuario(consulta.getUsuario());
         consulta.setProfissional(consulta.getProfissional());
          consulta.setTipo(consulta.getTipo());
           consulta.setData(consulta.getData());
           consulta.setHora( consulta.getHora());
        
        consultaRepository.save(consultaEncontrada);
        return consultaEncontrada;
    }
    
    public void excluiConsulta(Integer id){
        Consulta consultaEncontrada = buscaConsultaPorId(id);
        consultaRepository.deleteById( consultaEncontrada.getId());
    }
  public Consulta saveConsulta(Consulta consulta) {
    
    if (consulta.getUsuario() == null || consulta.getUsuario().getId() == null) {
        throw new IllegalArgumentException("O ID do usuário é obrigatório.");
    }

   
    Usuario usuario = usuarioService.buscarUsuarioPorId(consulta.getUsuario().getId());
    
    if (usuario == null) {
        throw new IllegalArgumentException("Usuário não encontrado com o ID fornecido.");
    }

   
    consulta.setUsuario(usuario);

    
    return consultaRepository.save(consulta);
}
}