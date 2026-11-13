package cl.duoc.duoctech.service;

import cl.duoc.duoctech.model.Proyecto;
import cl.duoc.duoctech.repository.ProyectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProyectoService {

    @Autowired
    private ProyectoRepository proyectoRepository;

    public List<Proyecto> listar(){
        return proyectoRepository.findAll();
    }
    public Proyecto buscar(Long id){
        return proyectoRepository.findById(id).orElse(null);
    }
    public Proyecto guardar(Proyecto proyecto){
        return proyectoRepository.save(proyecto);
    }

}
