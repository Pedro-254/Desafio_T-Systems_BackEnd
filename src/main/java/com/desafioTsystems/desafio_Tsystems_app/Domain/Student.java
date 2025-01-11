package com.desafioTsystems.desafio_Tsystems_app.Domain;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student extends Person{
    private Integer NumeroEstudante;
    private Float Media;
    private Integer NumeroSeminarios;

    public boolean isEligibleToEnroll() {
        return this.Media >= 50.0;
    }

    public Integer getSeminarsTaken() {
        // Lógica para buscar seminários
        return NumeroSeminarios;
    }
}
