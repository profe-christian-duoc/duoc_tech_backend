package cl.duoc.duoctech.config;

import cl.duoc.duoctech.model.Colaborador;
import cl.duoc.duoctech.model.Departamento;
import cl.duoc.duoctech.model.Proyecto;
import cl.duoc.duoctech.repository.ColaboradorRepository;
import cl.duoc.duoctech.repository.DepartamentoRepository;
import cl.duoc.duoctech.repository.ProyectoRepository;
import cl.duoc.duoctech.model.*;
import cl.duoc.duoctech.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.*;

import java.time.LocalDate;
import java.util.*;

@Configuration
public class DataInitializer {
    @Autowired
    private DepartamentoRepository departamentoRepository;
    @Autowired
    private ColaboradorRepository colaboradorRepository;
    @Autowired
    private ProyectoRepository proyectoRepository;

    @Bean
    CommandLineRunner initData() {

        return args -> {

            // Evitar insertar datos duplicados
            if (departamentoRepository.count() > 0) {
                return;
            }

            // 1. Crear departamentos
            Departamento ti = Departamento.builder()
                    .codigo("TI")
                    .nombre("Tecnologia e Informatica")
                    .build();

            Departamento rrhh = Departamento.builder()
                    .codigo("RRHH")
                    .nombre("Recursos Humanos")
                    .build();

            Departamento fin = Departamento.builder()
                    .codigo("FIN")
                    .nombre("Finanzas")
                    .build();

            departamentoRepository.saveAll(
                    List.of(ti, rrhh, fin)
            );

            // 2. Crear colaboradores
            List<Colaborador> colaboradores = List.of(

                    Colaborador.builder()
                            .nombre("Juan")
                            .apellido("Perez")
                            .sueldoBase(1200000)
                            .departamento(ti)
                            .build(),

                    Colaborador.builder()
                            .nombre("Maria")
                            .apellido("Gonzalez")
                            .sueldoBase(1450000)
                            .departamento(ti)
                            .build(),

                    Colaborador.builder()
                            .nombre("Carlos")
                            .apellido("Rodriguez")
                            .sueldoBase(1100000)
                            .departamento(ti)
                            .build(),

                    Colaborador.builder()
                            .nombre("Ana")
                            .apellido("Martinez")
                            .sueldoBase(1300000)
                            .departamento(rrhh)
                            .build(),

                    Colaborador.builder()
                            .nombre("Pedro")
                            .apellido("Lopez")
                            .sueldoBase(1150000)
                            .departamento(rrhh)
                            .build(),

                    Colaborador.builder()
                            .nombre("Camila")
                            .apellido("Sanchez")
                            .sueldoBase(1500000)
                            .departamento(fin)
                            .build(),

                    Colaborador.builder()
                            .nombre("Diego")
                            .apellido("Fernandez")
                            .sueldoBase(1250000)
                            .departamento(fin)
                            .build()
            );

            colaboradorRepository.saveAll(colaboradores);

            // 3. Crear proyectos
            List<Proyecto> proyectos = List.of(

                    Proyecto.builder()
                            .nombre("Sistema ERP")
                            .fechaInicio(LocalDate.of(2026, 1, 15))
                            .fechaTermino(LocalDate.of(2026, 12, 30))
                            .build(),

                    Proyecto.builder()
                            .nombre("Portal de Recursos Humanos")
                            .fechaInicio(LocalDate.of(2026, 3, 1))
                            .fechaTermino(LocalDate.of(2026, 9, 30))
                            .build(),

                    Proyecto.builder()
                            .nombre("Sistema de Gestion Financiera")
                            .fechaInicio(LocalDate.of(2026, 5, 1))
                            .fechaTermino(null)
                            .build()
            );

            proyectoRepository.saveAll(proyectos);

            System.out.println("==================================");
            System.out.println("DATOS INICIALES INSERTADOS");
            System.out.println("3 Departamentos");
            System.out.println("7 Colaboradores");
            System.out.println("3 Proyectos");
            System.out.println("==================================");
        };
    }
}
