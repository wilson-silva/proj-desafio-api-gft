package com.gft.sistema_programa_starter.controllers;


import com.gft.sistema_programa_starter.dto.usuario.ConsultaUsuarioDTO;
import com.gft.sistema_programa_starter.dto.usuario.RegistroUsuarioDTO;
import com.gft.sistema_programa_starter.dto.usuario.UsuarioMapper;
import com.gft.sistema_programa_starter.entities.Usuario;
import com.gft.sistema_programa_starter.services.UsuarioService;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("usuarios")
public class UsuarioController {


    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    //--------------------------------------------------------------------
    @ApiOperation(value = "Retorna lista de usuarios")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Page<ConsultaUsuarioDTO>> buscarTodosOsStarters(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(usuarioService.listarTodosOsUsuarios(pageable)
                .map(UsuarioMapper::fromEntity));
    }

    //--------------------------------------------------------------------
    @ApiOperation(value = "Cria e salva um usuario no sistema")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ConsultaUsuarioDTO> salvarUsuario(@RequestBody RegistroUsuarioDTO dto){
        Usuario usuario = usuarioService.salvarUsuario(UsuarioMapper.fromDTO(dto));
        return ResponseEntity.ok(UsuarioMapper.fromEntity(usuario));

    }

    //--------------------------------------------------------------------

    @ApiOperation(value = "Retorna um usuario por ID")
    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ConsultaUsuarioDTO> buscarUsuario(@PathVariable Long id) {
        Usuario usuario = usuarioService.buscarUsuarioPorId(id);
        return ResponseEntity.ok(UsuarioMapper.fromEntity(usuario));
    }

    //--------------------------------------------------------------------

    @ApiOperation(value = "Altera um usuario pelo ID")
    @PutMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ConsultaUsuarioDTO> alterarUsuario(@RequestBody RegistroUsuarioDTO dto,
                                                             @PathVariable Long id) {
        try {
            Usuario usuario = usuarioService.atualizarUsuario(UsuarioMapper.fromDTO(dto), id);
            return ResponseEntity.ok(UsuarioMapper.fromEntity(usuario));
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    //--------------------------------------------------------------------

    @ApiOperation(value = "Exclui um usuario pelo ID")
    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ConsultaUsuarioDTO> excluirUsuario(@PathVariable Long id) {
        try {
            usuarioService.excluirUsuario(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }


}
