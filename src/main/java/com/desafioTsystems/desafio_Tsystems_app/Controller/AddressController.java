package com.desafioTsystems.desafio_Tsystems_app.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.desafioTsystems.desafio_Tsystems_app.Domain.Address;
import com.desafioTsystems.desafio_Tsystems_app.Service.AddressService;
import com.desafioTsystems.desafio_Tsystems_app.dto.CadastrarRequestDTO;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/enderecos")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @SuppressWarnings("rawtypes")
    @PostMapping("/cadastrar")
    public ResponseEntity cadastrarEnderecoPost(@RequestBody CadastrarRequestDTO body) {

        Address address = addressService.createAddress(body.rua(), body.cidade(), body.estado(), body.cep(), body.pais());

        if (address.validate()) {
            this.addressService.saveAddress(address);
            return ResponseEntity.ok("Endereço cadastrado com sucesso!");

        } else {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Cep Inválido");

        }
    }
}
