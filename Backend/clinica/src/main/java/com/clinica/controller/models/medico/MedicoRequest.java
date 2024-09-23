package com.clinica.controller.models.medico;

import lombok.Data;

@Data
public class MedicoRequest {
    
    private String nome;
    private Long cpf;
    
    private String especialidade; 

    private double salário;

    private byte[] foto;
}