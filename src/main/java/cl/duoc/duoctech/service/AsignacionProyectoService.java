package cl.duoc.duoctech.service;

import cl.duoc.duoctech.model.AsignacionProyecto;
import cl.duoc.duoctech.model.Colaborador;
import cl.duoc.duoctech.model.Proyecto;
import cl.duoc.duoctech.repository.AsignacionProyectoRepository;
import cl.duoc.duoctech.repository.ColaboradorRepository;
import cl.duoc.duoctech.repository.ProyectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.time.LocalDate;
import java.util.List;

@Service
public class AsignacionProyectoService {

    @Autowired
    private ColaboradorRepository colaboradorRepository;
    @Autowired
    private ProyectoRepository proyectoRepository;
    @Autowired
    private AsignacionProyectoRepository asignacionRepository;

    public AsignacionProyecto asignarProyecto(
            Long colaboradorId,
            Long proyectoId) {

        Colaborador colaborador = colaboradorRepository.findById(colaboradorId)
                .orElseThrow(() ->new RuntimeException("Colaborador no encontrado"));
        Proyecto proyecto = proyectoRepository.findById(proyectoId)
                .orElseThrow(() ->new RuntimeException("Proyecto no encontrado"));

        // aqui ta con metodo tradicional
        //AsignacionProyecto asignacion = new AsignacionProyecto(null,colaborador,proyecto,LocalDate.now());

        // aqui ta con builder
        AsignacionProyecto asignacion = AsignacionProyecto.builder()
                .colaborador(colaborador)
                .proyecto(proyecto)
                .fechaAsignacion(LocalDate.now())
                .build();

        return asignacionRepository.save(asignacion);
    }

    public List<AsignacionProyecto> listar(){
        return asignacionRepository.findAll();
    }
    public AsignacionProyecto buscar(Long id){
        return asignacionRepository.findById(id).orElse(null);
    }
    public List<AsignacionProyecto> buscarPorColaborador(Long id){
        return asignacionRepository.findByColaboradorId(id);
    }
    public List<AsignacionProyecto> buscarPorProyecto(Long id){
        return asignacionRepository.findByProyectoId(id);
    }

}
