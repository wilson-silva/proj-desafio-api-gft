package com.gft.sistema_programa_starter.dto.usuario;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaUsuarioDTO {

    private Long id;
    private String usuario;
    private String nomePerfil;


}
