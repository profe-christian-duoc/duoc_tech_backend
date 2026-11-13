package cl.duoc.duoctech.repository;

import cl.duoc.duoctech.model.Colaborador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColaboradorRepository extends JpaRepository<Colaborador,Long>{
}
