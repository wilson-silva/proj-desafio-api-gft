package com.gft.sistema_programa_starter.dto.starter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaStarterDTO {

    private Long id;
    private String nome;
    private String cpf;
    private String quatroLetras;
    private String email;




}
