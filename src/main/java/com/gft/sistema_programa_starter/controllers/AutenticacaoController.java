package com.gft.sistema_programa_starter.controllers;

import com.gft.sistema_programa_starter.dto.auth.AutenticacaoDTO;
import com.gft.sistema_programa_starter.dto.auth.TokenDTO;
import com.gft.sistema_programa_starter.services.AutenticacaoService;
import com.gft.sistema_programa_starter.services.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AutenticacaoController {

    @Autowired
    AutenticacaoService autenticacaoService;

    @Autowired
    private EmailSenderService emailSenderService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TokenDTO> autenticar(@RequestBody AutenticacaoDTO auth){
        try {
            return ResponseEntity.ok(autenticacaoService.autenticar(auth));
        }catch(AuthenticationException ae) {
            ae.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }

    @EventListener(ApplicationReadyEvent.class)
    public  void enviarEmail() {
        emailSenderService.enviarEmail("wilsongs419@gmail.com",
                "acesso a api sistema programa starter",
                "Seja Bem Vindo a api sistema programa starter");
    }


}