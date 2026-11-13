package cl.duoc.duoctech.controller;

import cl.duoc.duoctech.model.Colaborador;
import cl.duoc.duoctech.service.ColaboradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.*;

@RestController
@RequestMapping("/api/colaboradores")
@Tag(name = "Colaboradores", description = "Operaciones para administrar colaboradores")
public class ColaboradorController {

    @Autowired
    private ColaboradorService colaboradorService;

    @GetMapping
    @Operation(summary = "Listar colaboradores", description = "Obtiene la lista completa de colaboradores registrados")
    @ApiResponse(responseCode = "200", description = "Lista de colaboradores obtenida correctamente")
    public ResponseEntity<List<Colaborador>> listar() {
        return ResponseEntity.ok(colaboradorService.listar());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar colaborador por ID", description = "Obtiene los datos de un colaborador mediante su identificador")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Colaborador encontrado correctamente"), @ApiResponse(responseCode = "404", description = "No se encontró el colaborador solicitado")})
    public ResponseEntity<Colaborador> buscarPorId(@Parameter(description = "Identificador único del colaborador", example = "1", required = true) @PathVariable Long id) {
        Colaborador colaborador = colaboradorService.buscar(id);

        if (colaborador == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(colaborador);
    }

    @PostMapping
    @Operation(summary = "Registrar colaborador", description = "Registra un nuevo colaborador en el sistema")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Colaborador creado correctamente"), @ApiResponse(responseCode = "400", description = "Los datos enviados son inválidos")})
    public ResponseEntity<Colaborador> guardar(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos del colaborador que se desea registrar", required = true) @RequestBody Colaborador colaborador) {
        Colaborador nuevoColaborador = colaboradorService.guardar(colaborador);

        return new ResponseEntity<>(nuevoColaborador, HttpStatus.CREATED);
    }
}
