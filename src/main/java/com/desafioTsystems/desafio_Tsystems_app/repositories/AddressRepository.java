package com.desafioTsystems.desafio_Tsystems_app.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desafioTsystems.desafio_Tsystems_app.Domain.Address;

public interface AddressRepository extends JpaRepository<Address, UUID>  {
    Optional<Address> findByCep(String cep);
}
