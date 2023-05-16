package com.gft.sistema_programa_starter.controllers;


import com.gft.sistema_programa_starter.dto.starter.ConsultaStarterDTO;
import com.gft.sistema_programa_starter.dto.starter.RegistroStarterDTO;
import com.gft.sistema_programa_starter.dto.starter.StarterMapper;
import com.gft.sistema_programa_starter.entities.Starter;
import com.gft.sistema_programa_starter.services.StarterService;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;


@RestController
@RequestMapping("starters")
public class StarterController {




    private final StarterService starterService;

    public StarterController(StarterService starterService) {
        this.starterService = starterService;
    }

    //--------------------------------------------------------------------
    @ApiOperation(value = "Retorna lista de starters")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<ConsultaStarterDTO>> buscarTodosOsStarters(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(starterService.listarTodosOsStarters(pageable)
                .map(StarterMapper::fromEntity));
    }

    @ApiOperation(value = "Retorna lista de starters por ordem alfabética crescente por nome")
    @GetMapping(value = "/asc", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<ConsultaStarterDTO>> buscarTodosOsStartersOrdemAlfabeticaCrescenteNome(
            @PageableDefault(sort = "nome",direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(starterService.listarTodosOsStarters(pageable)
                .map(StarterMapper::fromEntity));
    }

    @ApiOperation(value = "Retorna lista de starters por ordem alfabética decrescente por nome")
    @GetMapping(value = "/desc", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<ConsultaStarterDTO>> buscarTodosOsStartersOrdemAlfabeticaDescrescenteNome(
            @PageableDefault(sort = "nome",direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(starterService.listarTodosOsStarters(pageable)
                .map(StarterMapper::fromEntity));
    }

    //--------------------------------------------------------------------

    @ApiOperation(value = "Cria e salva um starter no sistema")
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(value = "",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ConsultaStarterDTO> salvarStart(
            @RequestPart(value = "file",required = false) final MultipartFile file,
            @RequestPart(value = "json",required = false) RegistroStarterDTO dto) {

        if (!file.isEmpty()) {
            try {

                File convertFile = new File("src/main/resources/static/imagens/" + file.getOriginalFilename());
                convertFile.createNewFile();
                FileOutputStream fout = new FileOutputStream(convertFile);
                fout.write(file.getBytes());
                fout.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Starter starter = starterService.salvarStarter(StarterMapper.fromDTO(dto));
        return ResponseEntity.ok(StarterMapper.fromEntity(starter));

    }

    //--------------------------------------------------------------------
    @ApiOperation(value = "Retorna um start por ID")
    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ConsultaStarterDTO> buscarStarter(@PathVariable Long id) {
        Starter starter = starterService.buscarStarter(id);
        return ResponseEntity.ok(StarterMapper.fromEntity(starter));
    }

    @ApiOperation(value = "Retorna um start por nome")
    @GetMapping(value = "/nome/{nome}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ConsultaStarterDTO> buscarStarterNome(@PathVariable String nome) {
        Starter starter = starterService.buscarStarterNome(nome);
        return ResponseEntity.ok(StarterMapper.fromEntity(starter));
    }

    //--------------------------------------------------------------------
    @ApiOperation(value = "Altera um start pelo ID")
    @PutMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ConsultaStarterDTO> alterarStarter(@RequestBody RegistroStarterDTO dto,
                                                             @PathVariable Long id) {
        try {
            Starter starter = starterService.atualizarStarter(StarterMapper.fromDTO(dto), id);
            return ResponseEntity.ok(StarterMapper.fromEntity(starter));
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    //--------------------------------------------------------------------
    @ApiOperation(value = "Exclui um start pelo ID")
    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ConsultaStarterDTO> excluirStarter(@PathVariable Long id) {
        try {
            starterService.excluirStarter(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

}
