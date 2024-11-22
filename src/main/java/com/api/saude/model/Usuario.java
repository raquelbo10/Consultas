
package com.api.saude.model;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    private int id;
    private String nome;
    private String sobrenome;
    private String user;
    private String senha;
    
    private String cpf;
    private String genero;
    private String tel;
     
}
