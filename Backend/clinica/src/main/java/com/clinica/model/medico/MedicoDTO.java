package com.clinica.model.medico;

import java.util.UUID;
import lombok.Data;


//classe para tranferir dados entre o service e o controller
@Data
public class MedicoDTO {





    private UUID id;

    private String nome ;

    private Long cpf;
    
    private String especialidade; 

    private double salário;

    private byte[] foto;

}
