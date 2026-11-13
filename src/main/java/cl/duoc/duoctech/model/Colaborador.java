package cl.duoc.duoctech.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.*;

@Entity
@Table(name = "colaboradores")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Colaborador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_colaborador")
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nombre;

    @NotBlank
    @Column(nullable = false)
    private String apellido;

    @NotNull
    @PositiveOrZero
    @Column(name = "sueldo_base", nullable = false)
    private Integer sueldoBase;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "codigo_departamento", nullable = false)
    private Departamento departamento;

    @JsonIgnore
    @OneToMany(mappedBy = "colaborador")
    private List<AsignacionProyecto> asignaciones = new ArrayList<>();

}