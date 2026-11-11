package com.agenda.refatoracao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agenda.refatoracao.model.Contato;

import jakarta.transaction.Transactional;

public interface ContatoRepository extends JpaRepository<Contato, Long> {
    
    @Transactional 
    Optional<Contato> findById(Long id);

    
    Contato findByEmail(String email);
}
