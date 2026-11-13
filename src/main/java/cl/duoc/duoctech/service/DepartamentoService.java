package cl.duoc.duoctech.service;

import cl.duoc.duoctech.model.Departamento;
import cl.duoc.duoctech.repository.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartamentoService {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    public List<Departamento> listar(){
        return departamentoRepository.findAll();
    }
    public Departamento buscar(String codigo){
        return departamentoRepository.findById(codigo).orElse(null);
    }
    public Departamento guardar(Departamento departamento){
        return departamentoRepository.save(departamento);
    }

}
