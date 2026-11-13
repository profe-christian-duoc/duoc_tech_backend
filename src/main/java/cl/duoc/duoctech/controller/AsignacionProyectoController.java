package cl.duoc.duoctech.controller;

import cl.duoc.duoctech.model.AsignacionProyecto;
import cl.duoc.duoctech.service.AsignacionProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

@RestController
@RequestMapping("/api/asignaciones")
@Tag(
        name = "Asignaciones de Proyectos",
        description = "Operaciones para administrar las asignaciones de colaboradores a proyectos"
)
public class AsignacionProyectoController {

    @Autowired
    private AsignacionProyectoService asignacionProyectoService;

    @PostMapping
    @Operation(
            summary = "Asignar colaborador a proyecto",
            description = "Registra una asignación entre un colaborador y un proyecto utilizando sus identificadores"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Colaborador asignado al proyecto correctamente"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "No fue posible realizar la asignación debido a datos inválidos o un error de negocio"
            )
    })
    public ResponseEntity<AsignacionProyecto> asignar(
            @Parameter(
                    description = "Identificador del colaborador que será asignado",
                    example = "1",
                    required = true
            )
            @RequestParam Long colaboradorId,

            @Parameter(
                    description = "Identificador del proyecto al que será asignado el colaborador",
                    example = "1",
                    required = true
            )
            @RequestParam Long proyectoId
    ) {
        try {
            AsignacionProyecto asignacionProyecto =
                    asignacionProyectoService.asignarProyecto(colaboradorId, proyectoId);

            return new ResponseEntity<>(asignacionProyecto, HttpStatus.CREATED);

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    @Operation(
            summary = "Listar asignaciones",
            description = "Obtiene la lista completa de asignaciones de colaboradores a proyectos"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Lista de asignaciones obtenida correctamente"
    )
    public ResponseEntity<List<AsignacionProyecto>> listar() {
        return ResponseEntity.ok(asignacionProyectoService.listar());
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Buscar asignación por ID",
            description = "Obtiene una asignación específica mediante su identificador"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Asignación encontrada correctamente"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No se encontró la asignación solicitada"
            )
    })
    public ResponseEntity<AsignacionProyecto> buscarPorId(
            @Parameter(
                    description = "Identificador único de la asignación",
                    example = "1",
                    required = true
            )
            @PathVariable Long id
    ) {
        AsignacionProyecto asignacion = asignacionProyectoService.buscar(id);

        if (asignacion == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(asignacion);
    }

    @GetMapping("/buscar-por-colaborador/{id}")
    @Operation(
            summary = "Buscar asignaciones por colaborador",
            description = "Obtiene todas las asignaciones asociadas a un colaborador específico"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Asignaciones del colaborador obtenidas correctamente"
    )
    public ResponseEntity<List<AsignacionProyecto>> buscarPorColaborador(
            @Parameter(
                    description = "Identificador del colaborador",
                    example = "1",
                    required = true
            )
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(
                asignacionProyectoService.buscarPorColaborador(id)
        );
    }

    @GetMapping("/buscar-por-proyecto/{id}")
    @Operation(
            summary = "Buscar asignaciones por proyecto",
            description = "Obtiene todas las asignaciones asociadas a un proyecto específico"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Asignaciones del proyecto obtenidas correctamente"
    )
    public ResponseEntity<List<AsignacionProyecto>> buscarPorProyecto(
            @Parameter(
                    description = "Identificador del proyecto",
                    example = "1",
                    required = true
            )
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(
                asignacionProyectoService.buscarPorProyecto(id)
        );
    }
}