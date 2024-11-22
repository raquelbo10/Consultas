
package com.api.saude.controller;

import com.api.saude.model.Consulta;
import com.api.saude.model.Profissional;
import com.api.saude.model.Usuario;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AgendaController {

    private List<Consulta> consultas = new ArrayList<>();
    private List<Usuario> usuarios = new ArrayList<>();

    // Página de Cadastro
    @GetMapping("/Cadastro")
    public String mostraCadastro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "cadastro";
    }

    // Página de Agenda
    @GetMapping("/Agenda")
    public String mostraAgenda(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("consulta", new Consulta());
        return "agendamento";
    }

    
    @GetMapping("/Login")
    public String mostraLogin(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "index";
    }

    
    @GetMapping("/Listas")
    public String mostraListas(Model model) {
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("consultas", consultas);
        return "listas";
    }

    // Login: Verifica credenciais via GET e redireciona para Cadastro
    @GetMapping("/login")
    public String login(
        @RequestParam String user,
        @RequestParam String senha,
        Model model
    ) {
        Usuario usuarioAutenticado = buscaUsuarioPorLogin(senha, user);

        if (usuarioAutenticado != null) {
            
            return "redirect:/Cadastro";
        } else {
            // Credenciais inválidas: retorna à página de login com erro
            model.addAttribute("erro", "Usuário ou senha inválidos!");
            return "index";
        }
    }

    
    @PostMapping("/salvar-usuario")
    public String adicionarUsuario(Model model, @ModelAttribute Usuario usuario) {
        usuario.setId(usuarios.size() + 1);
        usuarios.add(usuario);

        model.addAttribute("usuarios", usuarios);
        model.addAttribute("mensagem", "Usuário cadastrado com sucesso!");
        return "redirect:/Cadastro";
    }

   
    @PostMapping("/salvar-consulta")
public String adicionarConsulta(Model model, @ModelAttribute Consulta consulta) {
    consulta.setId(consultas.size() + 1); 
    
   
    consultas.add(consulta);
    
    model.addAttribute("consultas", consultas); 
    



        return "redirect:/Agenda";
    }

    
    private Usuario buscaUsuarioPorLogin(String senha, String user) {
        for (Usuario usuario : usuarios) {
            if (usuario.getUser().equals(user) && usuario.getSenha().equals(senha)) {
                return usuario;
            }
        }
        return null; 
    }
}
