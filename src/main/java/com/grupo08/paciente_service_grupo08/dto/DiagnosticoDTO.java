package com.grupo08.paciente_service_grupo08.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiagnosticoDTO {
    private Long idDiagnostico;
    private String codigoCIE10;
    private String descripcion;
    private LocalDateTime fechaDiagnostico;
    private String gravedad;
}