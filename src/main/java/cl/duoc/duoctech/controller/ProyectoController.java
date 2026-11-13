package cl.duoc.duoctech.controller;

import cl.duoc.duoctech.model.Proyecto;
import cl.duoc.duoctech.service.ProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.*;

@RestController
@RequestMapping("/api/proyectos")
@Tag(name = "Proyectos", description = "Operaciones para administrar proyectos")
public class ProyectoController {

    @Autowired
    private ProyectoService proyectoService;

    @GetMapping
    @Operation(summary = "Listar proyectos", description = "Obtiene la lista completa de proyectos registrados en el sistema")
    @ApiResponse(responseCode = "200", description = "Lista de proyectos obtenida correctamente")
    public ResponseEntity<List<Proyecto>> listar() {
        return ResponseEntity.ok(proyectoService.listar());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar proyecto por ID", description = "Obtiene la información de un proyecto mediante su identificador")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Proyecto encontrado correctamente"), @ApiResponse(responseCode = "404", description = "No se encontró el proyecto solicitado")})
    public ResponseEntity<Proyecto> buscarPorId(@Parameter(description = "Identificador único del proyecto", example = "1", required = true) @PathVariable Long id) {
        Proyecto proyecto = proyectoService.buscar(id);

        if (proyecto == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(proyecto);
    }

    @PostMapping
    @Operation(summary = "Registrar proyecto", description = "Registra un nuevo proyecto en el sistema")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Proyecto creado correctamente"), @ApiResponse(responseCode = "400", description = "Los datos enviados son inválidos")})
    public ResponseEntity<Proyecto> guardar(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos del proyecto que se desea registrar", required = true) @RequestBody Proyecto proyecto) {
        Proyecto nuevoProyecto = proyectoService.guardar(proyecto);

        return new ResponseEntity<>(nuevoProyecto, HttpStatus.CREATED);
    }
}