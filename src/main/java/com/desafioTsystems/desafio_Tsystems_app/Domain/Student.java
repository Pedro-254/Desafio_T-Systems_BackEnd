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
    private Integer StudentNumber;
    private Float AvarageMark;
    private Integer NumberOfSeminars;

    public boolean isEligibleToEnroll() {
        return this.AvarageMark >= 50.0;
    }

    public Integer getSeminarsTaken() {
        // Lógica para buscar seminários
        return NumberOfSeminars;
    }
}
