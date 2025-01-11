package com.desafioTsystems.desafio_Tsystems_app.Domain;

import java.util.UUID;
import java.util.regex.Pattern;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    private String rua;
    private String cidade;
    private String estado;
    private String cep;
    private String pais;

    public boolean validate() {
        // Lógica de validação
        return isValidCep(this.cep);
    }

    public String outputAsLabel() {
        return rua + ", " + cidade + ", " + estado + ", " + cep + ", " + pais;
    }

    private boolean isValidCep(String cep) {
        if (cep == null) return false;
        // Regex para CEP brasileiro: 5 dígitos, hífen opcional, 3 dígitos (ex: 12345-678)
        String cepRegex = "\\d{5}-?\\d{3}";
        return Pattern.matches(cepRegex, cep);
    }
}
