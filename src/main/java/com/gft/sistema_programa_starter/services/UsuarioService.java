package com.gft.sistema_programa_starter.services;


import com.gft.sistema_programa_starter.entities.Usuario;
import com.gft.sistema_programa_starter.repositories.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    //--------------------------------------------------------------------
    public Usuario buscarUsuarioPorEmail(String usuario) {
        Optional<Usuario> optional = usuarioRepository.findByUsuario(usuario);
        if(optional.isEmpty()) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
        return optional.get();
    }
    //--------------------------------------------------------------------
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return buscarUsuarioPorEmail(username);
    }

    //--------------------------------------------------------------------
    public Usuario buscarUsuarioPorId(Long idUsuario) {
        Optional<Usuario> optional = usuarioRepository.findById(idUsuario);
        if(optional.isEmpty()) {
            throw new RuntimeException("Usuário não encontrado");
        }
        return optional.get();
    }

    //--------------------------------------------------------------------
    public Usuario salvarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);

    }
    //--------------------------------------------------------------------
    public Page<Usuario> listarTodosOsUsuarios(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }

    //--------------------------------------------------------------------
    public Usuario atualizarUsuario(Usuario usuario, Long id) {
        Usuario usuarioOriginal = this.buscarUsuarioPorId(id);
        usuario.setId(usuarioOriginal.getId());
        return usuarioRepository.save(usuario);
    }

    //--------------------------------------------------------------------
    public void excluirUsuario(Long id) {
        Usuario usuarioOriginal = this.buscarUsuarioPorId(id);
        usuarioRepository.delete(usuarioOriginal);
    }
}
