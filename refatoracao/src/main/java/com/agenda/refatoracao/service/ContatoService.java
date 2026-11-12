package com.agenda.refatoracao.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agenda.refatoracao.exception.ContatoJaExiste;
import com.agenda.refatoracao.exception.ContatoNaoExiste;
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

        if (repository.findByEmail(contato.getEmail()) != null) {
            throw new ContatoJaExiste("O contato ja existe");
        }

        repository.save(contato);
    }

    public List<Contato> obterTodos() {
        return repository.findAll();
    }

    public Contato obterPorEmail(String email) {
        if (email == null) {
            throw new IllegalArgumentException("O email não pode ser nulo");
        }

        if (repository.findByEmail(email) == null) {
            throw new ContatoNaoExiste("O contato não existe");
        }

        return repository.findByEmail(email);
    };

    public void atualizar(String email, Contato novoContato) {
        if (email == null || novoContato == null) {
            throw new IllegalArgumentException("O email ou o contato não podem ser nulos");
        }

        Contato contatoExistente = repository.findByEmail(email);

        if (contatoExistente == null) {
            throw new ContatoNaoExiste("O contato não existe");
        }

        String novoEmail = novoContato.getEmail();
        if (!email.equals(novoEmail)) {

            Contato novoContatoEmail = repository.findByEmail(novoEmail);

            if (novoContatoEmail != null) {
                throw new ContatoJaExiste("O contato ja existe");

            }
        }

        contatoExistente.setNome(novoContato.getNome());
        contatoExistente.setEmail(novoContato.getEmail());
        contatoExistente.setNome(novoContato.getNome());

        repository.save(novoContato);
    }

    public void remover(String email) {
        Contato deletarContato = repository.findByEmail(email);
        if (deletarContato == null) {
            throw new ContatoNaoExiste("O contato não existe");
        }
        repository.delete(deletarContato);
    }
}
