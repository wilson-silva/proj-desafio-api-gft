package com.gft.sistema_programa_starter.controllers;

import com.gft.sistema_programa_starter.dto.categoria.CategoriaMapper;
import com.gft.sistema_programa_starter.dto.categoria.ConsultaCategoriaDTO;
import com.gft.sistema_programa_starter.dto.categoria.RegistroCategoriaDTO;
import com.gft.sistema_programa_starter.entities.Categoria;
import com.gft.sistema_programa_starter.services.CategoriaService;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    //--------------------------------------------------------------------
    @ApiOperation(value = "Retorna lista das categorias")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<ConsultaCategoriaDTO>> buscarTodasAsCategorias(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(categoriaService.listarTodosAsCategorias(pageable)
                .map(CategoriaMapper::fromEntity));
    }

    //--------------------------------------------------------------------
    @ApiOperation(value = "Cria e salva uma categoria no sistema")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ConsultaCategoriaDTO> salvarFilial(@RequestBody RegistroCategoriaDTO dto) {
        Categoria categoria = categoriaService.salvarCategoria(CategoriaMapper.fromDTO(dto));
        return ResponseEntity.ok(CategoriaMapper.fromEntity(categoria));
    }

    //--------------------------------------------------------------------
    @ApiOperation(value = "Retorna uma categora por ID")
    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ConsultaCategoriaDTO> buscarCategoria(@PathVariable Long id) {
        Categoria categoria = categoriaService.buscarCategoria(id);
        return ResponseEntity.ok(CategoriaMapper.fromEntity(categoria));
    }

    //--------------------------------------------------------------------
    @ApiOperation(value = "Altera uma categoria pelo ID")
    @PutMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ConsultaCategoriaDTO> alterarCategoria(@RequestBody RegistroCategoriaDTO dto,
                                                                 @PathVariable Long id) {
        try {
            Categoria categoria = categoriaService.atualizarCategoria(CategoriaMapper.fromDTO(dto), id);
            return ResponseEntity.ok(CategoriaMapper.fromEntity(categoria));
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    //--------------------------------------------------------------------
    @ApiOperation(value = "Exclui uma categoria pelo ID")
    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ConsultaCategoriaDTO> excluirCategoria(@PathVariable Long id) {
        try {
            categoriaService.excluirCategoria(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

}
