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

    public void editProfessor(Professor professor, String nome, String numero, String email, String rua, String cidade, String estado, String cep, String pais, String salario) {

        if (nome != null) {
            professor.setNome(nome);
        }
        if (numero != null) {
            professor.setNumero(numero);
        }
        if (email != null) {
            professor.setEmail(email);
        }
        if (rua != null) {
            professor.getAddress().setRua(rua);
        }
        if (cidade != null) {
            professor.getAddress().setCidade(cidade);
        }
        if (estado != null) {
            professor.getAddress().setEstado(estado);
        }
        if (cep != null) {
            professor.getAddress().setCep(cep);
        }
        if (pais != null) {
            professor.getAddress().setPais(pais);
        }
        if (salario != null) {
            professor.setSalary(Float.parseFloat(salario));
        }

        saveProfessor(professor);
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
