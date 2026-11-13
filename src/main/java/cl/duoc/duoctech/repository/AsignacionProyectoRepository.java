package cl.duoc.duoctech.repository;

import cl.duoc.duoctech.model.AsignacionProyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AsignacionProyectoRepository extends JpaRepository<AsignacionProyecto, Long> {

    List<AsignacionProyecto> findByColaboradorId(Long colaboradorId);

    List<AsignacionProyecto> findByProyectoId(Long proyectoId);

    boolean existsByColaboradorIdAndProyectoId(Long colaboradorId, Long proyectoId);

}
