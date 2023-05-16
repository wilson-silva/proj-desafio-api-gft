package com.gft.sistema_programa_starter.dto.categoria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaCategoriaDTO {

    private Long id;
    private String tecnologia;
    private String nome;


}
