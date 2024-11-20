package com.example.parcial.controllers;

import com.example.parcial.dtos.ContratoDTO;
import com.example.parcial.services.ContratoService;

import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/contratos")
public class ContratoController {

    private final ContratoService contratoService;

    @CrossOrigin
    @PostMapping
    public ResponseEntity<ContratoDTO> createContrato(@RequestBody ContratoDTO contratoDTO) {
        ContratoDTO contrato = contratoService.createContrato(contratoDTO);
        return ResponseEntity.ok(contrato);
    }

    @CrossOrigin
    @PutMapping("/{identificador}")
    public ResponseEntity<ContratoDTO> updateContrato(@PathVariable String identificador, @RequestBody ContratoDTO contratoDTO) {
        ContratoDTO contrato = contratoService.updateContrato(identificador, contratoDTO);
        if (contrato != null) {
            return ResponseEntity.ok(contrato);
        }
        return ResponseEntity.notFound().build();
    }

    @CrossOrigin
    @DeleteMapping("/{identificador}")
    public ResponseEntity<Void> deleteContrato(@PathVariable String identificador) {
        contratoService.deleteContrato(identificador);
        return ResponseEntity.noContent().build();
    }

    @CrossOrigin
    @GetMapping
    public ResponseEntity<List<ContratoDTO>> getAllContratos() {
        List<ContratoDTO> contratos = contratoService.getAllContratos();
        return ResponseEntity.ok(contratos);
    }

    @CrossOrigin
    @GetMapping("/{identificador}")
    public ResponseEntity<ContratoDTO> getContratoByIdentificador(@PathVariable String identificador) {
        Optional<ContratoDTO> contrato = contratoService.getContratoByIdentificador(identificador);
        if (contrato.isPresent()) {
            return ResponseEntity.ok(contrato.get());
        }
        return ResponseEntity.notFound().build();
    }
}