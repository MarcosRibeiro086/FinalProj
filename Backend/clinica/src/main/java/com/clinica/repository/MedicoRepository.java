package com.clinica.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clinica.model.Medico;

//informa ao spring que essa é a interface que eu usarei parei para enviar dados ao banco
@Repository
public interface MedicoRepository extends JpaRepository<Medico,UUID> {
    
    //Interface que extende outra interface genérica do jpa, recebe dois parâmetros, o model e o tipo do Id desse objeto
    Optional<Medico> findBycpf(Long cpf);
}
