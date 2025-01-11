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
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/professores")
@RequiredArgsConstructor
public class ProfessorController {

    private final ProfessorService professorService;
    private final StudentService studentService;
    private final AddressService addressService;


    @GetMapping
    public ResponseEntity<?> getAllProfessores() {
        Iterable<Professor> professores = this.professorService.getAllProfessores();
        return ResponseEntity.ok(professores);
    }

    @SuppressWarnings("rawtypes")
    @PostMapping("/cadastrar")
    public ResponseEntity cadastrarProfessorPost(@RequestBody CadastrarRequestDTO body) {
        Optional<Professor> professor = this.professorService.findProfessor(body.email());
        Optional<Student> student = this.studentService.findStudent(body.email());
        // Optional<Address> address = this.Addressrepository.findByCep(body.cep());


        if(professor.isEmpty() && student.isEmpty()) {

            // Criando novo Endereço
            Address address = addressService.createAddress(body.rua(), body.cidade(), body.estado(), body.cep(), body.pais());

            // Validação do Endereço
            if (address.validate()) {

                // Criando novo Professor
                Professor newProfessor = professorService.createProfessor(body.email(), body.nome(), body.numero(), address);

                // Salva no banco novo professor e endereço
                this.professorService.saveProfessor(newProfessor);
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
}
