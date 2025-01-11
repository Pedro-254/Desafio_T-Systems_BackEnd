package com.desafioTsystems.desafio_Tsystems_app.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafioTsystems.desafio_Tsystems_app.Domain.Address;
import com.desafioTsystems.desafio_Tsystems_app.Domain.Professor;
import com.desafioTsystems.desafio_Tsystems_app.repositories.ProfessorRepository;

@Service
public class ProfessorService {
    @Autowired
    private ProfessorRepository professorRepository;

    public Professor saveProfessor(Professor professor) {
        return professorRepository.save(professor);
    }

    public Professor createProfessor(String email, String nome, String numero, Address address){
        Float salarioPadrao = 1000f;

        Professor professor = new Professor(salarioPadrao);
        professor.setEmail(email);
        professor.setNome(nome);
        professor.setNumero(numero);
        professor.setAddress(address);

        return professor;
    }

    public void deleteProfessor(Professor professor) {
        professorRepository.delete(professor);
    }

    public Optional<Professor> findProfessor(String email) {
        return professorRepository.findByEmail(email);
    }

    public List<Professor> getAllProfessores() {
        return professorRepository.findAll();
    }

}
