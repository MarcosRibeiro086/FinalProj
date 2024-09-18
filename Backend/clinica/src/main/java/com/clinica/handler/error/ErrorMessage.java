package com.clinica.handler.error;



import lombok.Data;

@Data
public class ErrorMessage {
    
    private String titulo;
    private Integer status;
    private String message;

    public ErrorMessage(String titulo,Integer status,String message){
        this.titulo=titulo;
        this.status=status;
        this.message=message;
    }

}
