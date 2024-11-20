package com.example.parcial.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.parcial.dtos.ContratoDTO;
import com.example.parcial.entities.Contrato;
import com.example.parcial.repositories.ContratoRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ContratoService {

    private final ContratoRepository contratoRepository;
    private final ModelMapper modelMapper;

    public List<ContratoDTO> getAllContratos() {
        List<Contrato> contratos = (List<Contrato>) contratoRepository.findAll();
        return contratos.stream().map(contrato -> modelMapper.map(contrato, ContratoDTO.class))
                                    .collect(Collectors.toList());
    }

    public ContratoDTO createContrato(ContratoDTO contratoDTO) {
        Contrato newContrato = modelMapper.map(contratoDTO, Contrato.class);
        try{
            newContrato = contratoRepository.save(newContrato);
        }
        catch(Exception e){
            return null;
        }
        return modelMapper.map(newContrato, ContratoDTO.class);
    }

    public ContratoDTO updateContrato(String identificador, ContratoDTO contratoDTO) {
        Optional<Contrato> contrato = contratoRepository.findByIdentificador(identificador);
        if(contrato.isPresent()){
            Contrato updatedContrato = contrato.get();
            modelMapper.map(contratoDTO, updatedContrato);
            updatedContrato = contratoRepository.save(updatedContrato);
            return modelMapper.map(updatedContrato, ContratoDTO.class);
        }
        return null;
    }

    public Optional<ContratoDTO> getContratoByIdentificador(String identificador) {
        Optional<Contrato> contrato = contratoRepository.findByIdentificador(identificador);
        if (contrato.isPresent()) {
            return Optional.of(modelMapper.map(contrato.get(), ContratoDTO.class));
        }
        return Optional.empty();
    }

    public void deleteContrato(String identificador) {
        contratoRepository.deleteByIdentificador(identificador);
    }
}
