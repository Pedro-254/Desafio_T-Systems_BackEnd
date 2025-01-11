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