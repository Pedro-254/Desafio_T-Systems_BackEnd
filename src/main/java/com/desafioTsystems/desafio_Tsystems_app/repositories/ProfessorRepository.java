package com.desafioTsystems.desafio_Tsystems_app.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desafioTsystems.desafio_Tsystems_app.Domain.Professor;


public interface ProfessorRepository extends JpaRepository<Professor, UUID> {
    Optional<Professor> findByEmail(String email);
}

