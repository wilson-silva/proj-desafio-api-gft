package com.gft.sistema_programa_starter.dto.categoria;

import com.gft.sistema_programa_starter.entities.Categoria;

public class CategoriaMapper {

    public static Categoria fromDTO(RegistroCategoriaDTO dto) {
        return new Categoria(null, dto.getTecnologia(), dto.getNome());
    }

    //--------------------------------------------------------------------
    public static ConsultaCategoriaDTO fromEntity(Categoria categoria) {
        return new ConsultaCategoriaDTO(categoria.getId(),
                categoria.getTecnologia(), categoria.getNome());
    }

}
