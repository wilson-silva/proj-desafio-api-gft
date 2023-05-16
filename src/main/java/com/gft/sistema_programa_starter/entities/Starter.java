package com.gft.sistema_programa_starter.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Starter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @CPF//Já faz a validação se é um CPF válido
    @NotEmpty(message = "CPF é obrigatório")
    private String cpf;

    @NotEmpty(message = "Quatro letras náo pode estar em braco")
    @Size(min = 4, max = 4, message="Devem ser quatro letras")
    private String quatroLetras;

    @NotEmpty(message = "Email não pode estar em braco")
    private String email;

}
