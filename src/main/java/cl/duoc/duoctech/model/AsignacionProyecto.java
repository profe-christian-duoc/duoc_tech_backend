package cl.duoc.duoctech.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(
        name = "asignaciones_proyecto",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"id_colaborador", "id_proyecto"}
                )
        }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AsignacionProyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_asignacion")
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_colaborador", nullable = false)
    private Colaborador colaborador;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_proyecto", nullable = false)
    private Proyecto proyecto;

    @NotNull
    @Column(name = "fecha_asignacion", nullable = false)
    private LocalDate fechaAsignacion;

}
