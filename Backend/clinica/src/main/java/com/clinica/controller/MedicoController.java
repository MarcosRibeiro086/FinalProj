package com.clinica.controller;

import java.util.*;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.stream.Collectors;

import com.clinica.controller.models.medico.MedicoRequest;
import com.clinica.controller.models.medico.MedicoResponse;
import com.clinica.model.medico.MedicoDTO;
import com.clinica.services.MedicoService;

@RestController
@RequestMapping("/api/medicos")//rota da url
public class MedicoController {
    @Autowired
    private MedicoService medicoService;


    @GetMapping
    public  ResponseEntity <List<MedicoResponse>> obtertodos(){
        List<MedicoDTO> medicos= medicoService.obterTodos();
        ModelMapper mapper =new ModelMapper();
        List<MedicoResponse> resposta=medicos.stream().map(medicoDTO -> mapper.map(medicoDTO,MedicoResponse.class))
        .collect(Collectors.toList());
        //retorna uma lista de produto response dentro de um responseEntity com status OK ou 200
        return new ResponseEntity<>(resposta,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<MedicoResponse>> obterPorid(@PathVariable UUID id)/* pega o parâmetro e tenta transformar em inteiro */{
        Optional<MedicoDTO> dto = medicoService.obterPorId(id);
        MedicoResponse produto=new ModelMapper().map(dto.get(),MedicoResponse.class);
        return new ResponseEntity<>(Optional.of(produto),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<MedicoResponse >adicionar(@RequestBody MedicoRequest medicoReq){
        ModelMapper mapper=new ModelMapper();

        MedicoDTO medicoDto=mapper.map(medicoReq,MedicoDTO.class);
        medicoDto=medicoService.adicionar((medicoDto));

        
        
        return new ResponseEntity<>(mapper.map(medicoDto,MedicoResponse.class),HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable UUID id){
        medicoService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/{id}")
    public ResponseEntity<MedicoResponse> atualizar(@RequestBody MedicoRequest medicoReq, @PathVariable UUID id){
        ModelMapper mapper=new ModelMapper();
        MedicoDTO medicoDto=mapper.map(medicoReq,MedicoDTO.class);
        medicoDto=medicoService.atualizar(id, medicoDto);
        
        
        return new ResponseEntity<>(mapper.map(medicoDto, MedicoResponse.class),HttpStatus.OK);
    }
    

}
