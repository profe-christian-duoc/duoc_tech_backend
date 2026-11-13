package cl.duoc.duoctech.service;

import cl.duoc.duoctech.model.Colaborador;
import cl.duoc.duoctech.repository.ColaboradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColaboradorService {

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    public List<Colaborador> listar(){
        return colaboradorRepository.findAll();
    }
    public Colaborador buscar(Long id){
        return colaboradorRepository.findById(id).orElse(null);
    }
    public Colaborador guardar(Colaborador colaborador){
        return colaboradorRepository.save(colaborador);
    }

}
