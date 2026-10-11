package com.grupo08.paciente_service_grupo08.config;

import com.grupo08.paciente_service_grupo08.entity.Diagnostico;
import com.grupo08.paciente_service_grupo08.entity.Paciente;
import com.grupo08.paciente_service_grupo08.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final PacienteRepository pacienteRepository;

    @Override
    public void run(String... args) {
        if (pacienteRepository.count() > 0) {
            return;
        }

        // Paciente 1 - EPS - BAJO_RIESGO
        Paciente p1 = Paciente.builder()
                .numeroDocumento("70123456")
                .nombreCompleto("Juan Carlos Pérez Rojas")
                .fechaNacimiento(LocalDate.of(1985, 3, 15))
                .tipoSeguro("EPS")
                .estadoPaciente("ACTIVO")
                .build();
        p1.agregarDiagnostico(diagnostico("J06.9", "Infección aguda de las vías respiratorias superiores",
                LocalDateTime.of(2026, 8, 10, 9, 30), "LEVE", true));
        p1.agregarDiagnostico(diagnostico("I10", "Hipertensión esencial (primaria)",
                LocalDateTime.of(2026, 9, 5, 11, 0), "MODERADO", true));

        // Paciente 2 - ESSALUD - ALTO_RIESGO (tiene un SEVERO)
        Paciente p2 = Paciente.builder()
                .numeroDocumento("45678912")
                .nombreCompleto("María Elena Quispe Huamán")
                .fechaNacimiento(LocalDate.of(1962, 11, 2))
                .tipoSeguro("ESSALUD")
                .estadoPaciente("ACTIVO")
                .build();
        p2.agregarDiagnostico(diagnostico("E11.9", "Diabetes mellitus tipo 2 sin complicaciones",
                LocalDateTime.of(2026, 7, 20, 10, 15), "SEVERO", true));
        p2.agregarDiagnostico(diagnostico("K29.7", "Gastritis no especificada",
                LocalDateTime.of(2025, 12, 1, 16, 45), "LEVE", false));

        // Paciente 3 - PARTICULAR - BAJO_RIESGO
        Paciente p3 = Paciente.builder()
                .numeroDocumento("72345678")
                .nombreCompleto("Carlos Alberto Ramírez Torres")
                .fechaNacimiento(LocalDate.of(1998, 6, 25))
                .tipoSeguro("PARTICULAR")
                .estadoPaciente("ACTIVO")
                .build();
        p3.agregarDiagnostico(diagnostico("M54.5", "Lumbago no especificado",
                LocalDateTime.of(2026, 9, 28, 8, 0), "LEVE", true));

        pacienteRepository.save(p1);
        pacienteRepository.save(p2);
        pacienteRepository.save(p3);
    }

    private Diagnostico diagnostico(String codigo, String descripcion,
                                    LocalDateTime fecha, String gravedad, boolean activo) {
        return Diagnostico.builder()
                .codigoCIE10(codigo)
                .descripcion(descripcion)
                .fechaDiagnostico(fecha)
                .gravedad(gravedad)
                .activo(activo)
                .build();
    }
}
