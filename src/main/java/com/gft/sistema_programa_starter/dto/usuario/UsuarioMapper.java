package com.gft.sistema_programa_starter.dto.usuario;


import com.gft.sistema_programa_starter.entities.Perfil;
import com.gft.sistema_programa_starter.entities.Usuario;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UsuarioMapper {

    public static Usuario fromDTO(RegistroUsuarioDTO dto) {
        Perfil perfil = new Perfil();
        perfil.setId(dto.getPerfilId());
        return new Usuario(null, dto.getUsuario(), new BCryptPasswordEncoder().encode(dto.getSenha()), perfil);
    }

    //--------------------------------------------------------------------
    public static ConsultaUsuarioDTO fromEntity(Usuario usuario) {
        return new ConsultaUsuarioDTO(usuario.getId(),usuario.getUsuario(), usuario.getPerfil().getNome());
    }

}
