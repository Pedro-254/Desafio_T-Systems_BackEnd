package com.desafioTsystems.desafio_Tsystems_app.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desafioTsystems.desafio_Tsystems_app.Domain.Student;


public interface StudentRepository extends JpaRepository<Student, UUID> {
    Optional<Student> findByEmail(String email);
}

