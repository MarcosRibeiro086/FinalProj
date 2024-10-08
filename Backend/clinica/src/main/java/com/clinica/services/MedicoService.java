package com.clinica.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinica.handler.exception.ResourceNotFoundException;
import com.clinica.model.medico.Medico;
import com.clinica.model.medico.MedicoDTO;
import com.clinica.repository.MedicoRepository;

//Indica ao Spring que é uma classe de serviço
@Service
public class MedicoService {
    
    //Injetando dependencias
    @Autowired
    private MedicoRepository medicoRepository;


    /**
     * Repositorio retorna para mim dentro de uma lista de medicos e pra cada produto converta ele para medico.dto
     * @return lista de produtos
     */
    public List<MedicoDTO> obterTodos(){
        List<Medico>medicos=medicoRepository.findAll();
        return medicos.stream().map(medico-> new ModelMapper().map(medico,MedicoDTO.class)).collect(Collectors.toList());
    }


       /**
     * Método para obter medico por id
     * @param id
     * @return
    */
    public Optional<MedicoDTO> obterPorId(UUID id){

        Optional<Medico> medico=medicoRepository.findById(id);
        if(medico.isEmpty()){
            throw new ResourceNotFoundException("Produto com o id " + id +" não encontrado");
        }
        MedicoDTO dto= new ModelMapper().map(medico.get(),MedicoDTO.class);
        return Optional.of(dto);
    }
      /**
     * Método para adicionar novos produtos
     * @param produto
     * @return
     */
    public MedicoDTO adicionar(MedicoDTO medicoDto){
        //removendo o id para conseguir fazer o cadastro
        medicoDto.setId(null);
        //criar um objeto de mapeamento
        ModelMapper mapper =new ModelMapper();
        //converter o medicoDTO em um medico
        Medico medico =mapper.map(medicoDto, Medico.class);
        //salvar o medico no banco
        medico=medicoRepository.save(medico);
        medicoDto.setId(medico.getId());
        //retornar medicodto atualizado
        return medicoDto;
    }
    
    public void deletar(UUID id){
        Optional <Medico> medico=medicoRepository.findById(id);
        if(medico.isEmpty()){
            throw new ResourceNotFoundException("Não foi possívek deletar o médico com o id: "+ id +", médico não encontrado");
        }
        medicoRepository.deleteById(id);
    }

    /**
     * Método para atualizar produto 
     * @param id parametro usado para buscar o médio
     * @return
     */
    public MedicoDTO atualizar(UUID id, MedicoDTO medicoDto){
        
        //passar o id para o medicoDto
        medicoDto.setId(id);

        //cria o objeto de mapeamento
        ModelMapper mapper = new ModelMapper();

        //converter o medicoDTO em medico para ser salvo no banco
        Medico medico=mapper.map(medicoDto,Medico.class);

        //Atualizar o medico no banco de dados
        medicoRepository.save(medico);
        
        //retorna o medicoDto atualizado
        return medicoDto;
    }

    



}
