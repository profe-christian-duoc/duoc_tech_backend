package cl.duoc.duoctech.controller;

import cl.duoc.duoctech.model.Departamento;
import cl.duoc.duoctech.service.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.*;

@RestController
@RequestMapping("/api/departamentos")
@Tag(name = "Departamentos", description = "Operaciones para administrar departamentos")
public class DepartamentoController {

    @Autowired
    private DepartamentoService departamentoService;

    @GetMapping
    @Operation(summary = "Listar departamentos", description = "Obtiene la lista completa de departamentos registrados")
    @ApiResponse(responseCode = "200", description = "Lista de departamentos obtenida correctamente")
    public ResponseEntity<List<Departamento>> listar() {
        return ResponseEntity.ok(departamentoService.listar());
    }

    @GetMapping("/{codigo}")
    @Operation(summary = "Buscar departamento por código", description = "Obtiene los datos de un departamento mediante su código")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Departamento encontrado correctamente"), @ApiResponse(responseCode = "404", description = "No se encontró el departamento solicitado")})
    public ResponseEntity<Departamento> buscarPorId(@Parameter(description = "Código único del departamento", example = "DEP001", required = true) @PathVariable String codigo) {
        Departamento departamento = departamentoService.buscar(codigo);

        if (departamento == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(departamento);
    }

    @PostMapping
    @Operation(summary = "Registrar departamento", description = "Registra un nuevo departamento en el sistema")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Departamento creado correctamente"), @ApiResponse(responseCode = "400", description = "Los datos enviados son inválidos")})
    public ResponseEntity<Departamento> guardar(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos del departamento que se desea registrar", required = true) @RequestBody Departamento departamento) {
        Departamento nuevoDepartamento = departamentoService.guardar(departamento);

        return new ResponseEntity<>(nuevoDepartamento, HttpStatus.CREATED);
    }
}