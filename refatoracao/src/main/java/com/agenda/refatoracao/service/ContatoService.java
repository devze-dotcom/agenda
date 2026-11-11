package com.agenda.refatoracao.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agenda.refatoracao.model.Contato;
import com.agenda.refatoracao.repository.ContatoRepository;

import tools.jackson.databind.annotation.JsonAppend.Attr;

@Service
public class ContatoService {

    @Autowired
    private ContatoRepository repository;

    public ContatoService(ContatoRepository repository) {
        this.repository = repository;
    }

    public void salvar(Contato contato) {
        if (contato == null) {
            throw new IllegalArgumentException("O Contato não pode ser nulo");
        }

        repository.save(contato);
    }

    public List<Contato> obterTodos() {
        return repository.findAll();
    }

    public Contato obterPorEmail(String email) { // throws ContatoNaoExiste
        if (email == null) {
            throw new IllegalArgumentException("O email não pode ser nulo");
        }

        return repository.findByEmail(email);
    };

    public void atualizar(String email, Contato novoContato) { // throws ContatoNaoExiste, ContatoJaExiste;
        if (email == null || novoContato == null) {
            throw new IllegalArgumentException("O email ou o contato não podem ser nulos");
        }

        Contato contatoExistente = repository.findByEmail(email);

        if (contatoExistente == null) {
            throw new IllegalArgumentException("O contato não existe");
        }

        String novoEmail = novoContato.getEmail();
        if (!email.equals(novoEmail)) {

            Contato novoContatoEmail = repository.findByEmail(novoEmail);

            if (novoContatoEmail != null) {
                throw new IllegalArgumentException("O contato ja existe");

            }
        }

        contatoExistente.setNome(novoContato.getNome());
        contatoExistente.setEmail(novoContato.getEmail());
        contatoExistente.setNome(novoContato.getNome());

        repository.save(novoContato);
    }

    public void remover(String email) { // throws ContatoNaoExiste;
        Contato deletarContato = repository.findByEmail(email);
        if (deletarContato != null) {
            repository.delete(deletarContato);
        }
    }
}
