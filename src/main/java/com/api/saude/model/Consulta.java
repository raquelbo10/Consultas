
package com.api.saude.model;

import java.time.LocalDate;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Consulta {
    private int id;
    private Usuario usuario;
    private String profissional;
    private String tipo;
 @DateTimeFormat(pattern = "dd/MM/yyyy")
 private LocalDate data;
   private String hora;
}
