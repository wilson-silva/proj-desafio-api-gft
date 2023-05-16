package com.gft.sistema_programa_starter.dto.starter;


import com.gft.sistema_programa_starter.entities.Starter;

public class StarterMapper {

    public static Starter fromDTO(RegistroStarterDTO dto) {
        return new Starter(null, dto.getNome(), dto.getCpf(), dto.getQuatroLetras(), dto.getEmail());
    }

    //--------------------------------------------------------------------
    public static ConsultaStarterDTO fromEntity(Starter starter) {
        return new ConsultaStarterDTO(starter.getId(),
                starter.getNome(), starter.getCpf(), starter.getQuatroLetras(), starter.getEmail());
    }

}
