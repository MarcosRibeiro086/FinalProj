package com.clinica.model;

import java.util.UUID;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

/*
 * Entity: informa ao spring que essa classe é uma entidade no banco.
 * Data: anotação da biblioteca lombok para gerar getters, setters e construtores automaticamente.
 */
@Entity
@Data
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nome ;
    
    private String especialidade; 

    private double salário;

    private byte[] foto;
}