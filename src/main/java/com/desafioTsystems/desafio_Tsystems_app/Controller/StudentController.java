package com.desafioTsystems.desafio_Tsystems_app.Controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.desafioTsystems.desafio_Tsystems_app.Domain.Address;
import com.desafioTsystems.desafio_Tsystems_app.Domain.Professor;
import com.desafioTsystems.desafio_Tsystems_app.Domain.Student;
import com.desafioTsystems.desafio_Tsystems_app.Service.AddressService;
import com.desafioTsystems.desafio_Tsystems_app.Service.ProfessorService;
import com.desafioTsystems.desafio_Tsystems_app.Service.StudentService;
import com.desafioTsystems.desafio_Tsystems_app.dto.CadastrarRequestDTO;
import com.desafioTsystems.desafio_Tsystems_app.dto.DeletarRequestDTO;
import com.desafioTsystems.desafio_Tsystems_app.dto.EditarRequestDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/alunos")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;
    private final ProfessorService professorService;
    private final AddressService addressService;

    @GetMapping
    public ResponseEntity<?> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @SuppressWarnings("rawtypes")
    @PostMapping("/cadastrar")
    public ResponseEntity cadastrarAlunoPost(@RequestBody CadastrarRequestDTO body) {
        Optional<Professor> professor = this.professorService.findProfessor(body.email());
        Optional<Student> student = this.studentService.findStudent(body.email());
        // Optional<Address> address = this.Addressrepository.findByCep(body.cep());

        // Confere se email já esta cadastrado
        if(student.isEmpty() && professor.isEmpty()) {

            // Criando novo Endereço
            Address address = addressService.createAddress(body.rua(), body.cidade(), body.estado(), body.cep(), body.pais());

            // Validação do Endereço
            if (address.validate()) {

                // Criando novo Estudante
                Student newStudent = studentService.createStudent(body.email(), body.nome(), body.numero(), address);

                // Salva no banco novo estudante e endereço
                this.studentService.saveStudent(newStudent);
                this.addressService.saveAddress(address);
                
                return ResponseEntity.ok("Cadastro recebido com sucesso!");
            }else{
                return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Cep Inválido");
            }
            

        }

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Usuário já existente");
    }

    @PostMapping("/editar")
    public ResponseEntity<String> editarPost(@RequestBody EditarRequestDTO body) {
        Optional<Student> student = this.studentService.findStudent(body.email());

        if (student.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Usuário não existe");
        } else {
            studentService.editStudent(
                student.get(),
                body.nome(),
                body.numero(),
                body.email(),
                body.numeroSeminarios(),
                body.media(),
                body.rua(),
                body.cidade(),
                body.estado(),
                body.cep(),
                body.pais()
            );
            
            return ResponseEntity.ok("Usuário atualizado!");
        }
    }

    @PostMapping("/deletar")
    public ResponseEntity<String> deletarPost(@RequestBody DeletarRequestDTO body) {
        Optional<Student> student = this.studentService.findStudent(body.email());

        if (student.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Usuário não existe");
        } else {
            this.studentService.deleteStudent(student.get());
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Usuário deletado com sucesso");
        }
    }
}
