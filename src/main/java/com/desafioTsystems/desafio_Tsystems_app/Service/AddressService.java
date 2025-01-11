package com.desafioTsystems.desafio_Tsystems_app.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafioTsystems.desafio_Tsystems_app.Domain.Address;
import com.desafioTsystems.desafio_Tsystems_app.repositories.AddressRepository;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    public Address saveAddress(Address address) {
        return addressRepository.save(address);
    }

    public Address createAddress(String rua, String cidade, String estado, String cep, String pais) {

        Address address = new Address();
        address.setRua(rua);
        address.setCidade(cidade);
        address.setEstado(estado);
        address.setCep(cep);
        address.setPais(pais);

        return address;
    }

    public void deleteAddress(Address address) {
        addressRepository.delete(address);
    }

    public Optional<Address> findAddress(String postalCode) {
        return addressRepository.findByCep(postalCode);
    }

    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

}
