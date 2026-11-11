package com.agenda.refatoracao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.agenda.refatoracao.model.Contato;
import com.agenda.refatoracao.service.ContatoService;

@RestController
@RequestMapping("contatos")
public class ContatoController {

    @Autowired
    private ContatoService service;

    @PostMapping("/salvar")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> salvar(Contato contato) // throws ContatoJaExiste
    {
        service.salvar(contato);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/contatos")
    public ResponseEntity<Contato> obterPorEmail(@PathVariable String email) {
        Contato novoContato = service.obterPorEmail(email);
        return ResponseEntity.ok(novoContato);
    }

    @GetMapping("/todos")
    public ResponseEntity<List<Contato>> obterTodos() {
        List<Contato> listaContatos = service.obterTodos();
        return ResponseEntity.ok(listaContatos);

    }

    @PutMapping
    public ResponseEntity<Void> atualizar (@PathVariable String email, @RequestBody Contato contato) {
        service.atualizar(email, contato);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping 
    public ResponseEntity<Void> remover(@PathVariable String email) {
        service.remover(email);
        return ResponseEntity.ok().build();
    }
}
