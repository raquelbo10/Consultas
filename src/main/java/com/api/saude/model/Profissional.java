
package com.api.saude.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Profissional {
    private int id;
    private String nome;
    private String categoria;
    private String registro;
    
    
}
