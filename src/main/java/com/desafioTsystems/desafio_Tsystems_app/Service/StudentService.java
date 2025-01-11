package com.desafioTsystems.desafio_Tsystems_app.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafioTsystems.desafio_Tsystems_app.Domain.Address;
import com.desafioTsystems.desafio_Tsystems_app.Domain.Student;
import com.desafioTsystems.desafio_Tsystems_app.repositories.StudentRepository;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student createStudent(String email, String nome, String numero, Address address) {

        // Criando novo Estudante
        Student student = new Student(createID(), 0f, 0);
        student.setEmail(email);
        student.setNome(nome);
        student.setNumero(numero);
        student.setAddress(address);

        return student;
    }

    public void editStudent(Student student, String nome, String numero, String email, String seminarios, String media, String rua, String cidade, String estado, String cep, String pais) {

        if (nome != null) {
            student.setNome(nome);
        }
        if (numero != null) {
            student.setNumero(numero);
        }
        if (email != null) {
            student.setEmail(email);
        }
        if (seminarios != null) {
            student.setNumeroSeminarios(Integer.parseInt(seminarios));
        }
        if (media != null) {
            student.setMedia(Float.parseFloat(media));
        }
        if (rua != null) {
            student.getAddress().setRua(rua);
        }
        if (cidade != null) {
            student.getAddress().setCidade(cidade);
        }
        if (estado != null) {
            student.getAddress().setEstado(estado);
        }
        if (cep != null) {
            student.getAddress().setCep(cep);
        }
        if (pais != null) {
            student.getAddress().setPais(pais);
        }

        saveStudent(student);
    }

    public void deleteStudent(Student student) {
        studentRepository.delete(student);
    }

    public Optional<Student> findStudent(String email) {
        return studentRepository.findByEmail(email);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Integer createID() {
        return getAllStudents().size() + 1;
    }
}