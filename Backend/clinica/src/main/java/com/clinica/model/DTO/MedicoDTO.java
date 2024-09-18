package com.clinica.model.DTO;

import java.util.UUID;
import lombok.Data;

//classe para tranferir dados entre o service e o controller
public class MedicoDTO {

@Data
public class Medico {


    private UUID id;

    private String nome ;
    
    private String especialidade; 

    private double salário;

    private byte[] foto;
}

public void setId(Object object) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'setId'");
}
}
