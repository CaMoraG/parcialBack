package com.example.parcial.repositories;


import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.parcial.entities.Contrato;

public interface ContratoRepository extends CrudRepository<Contrato, Long> {
    Optional<Contrato> findByIdentificador(String identificador);
    void deleteByIdentificador(String identificador);
}
