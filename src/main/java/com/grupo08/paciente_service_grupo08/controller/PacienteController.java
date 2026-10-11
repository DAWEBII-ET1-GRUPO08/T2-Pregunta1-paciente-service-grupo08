package com.grupo08.paciente_service_grupo08.controller;

import com.grupo08.paciente_service_grupo08.dto.DiagnosticoDTO;
import com.grupo08.paciente_service_grupo08.dto.PacienteHistorialDTO;
import com.grupo08.paciente_service_grupo08.service.PacienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pacientes")
@RequiredArgsConstructor
public class PacienteController {

    private final PacienteService pacienteService;

    // GET /api/v1/pacientes/{pacienteId}
    @GetMapping("/{pacienteId}")
    public ResponseEntity<PacienteHistorialDTO> obtenerPaciente(@PathVariable("pacienteId") Long pacienteId) {
        return ResponseEntity.ok(pacienteService.obtenerHistorial(pacienteId));
    }

    // GET /api/v1/pacientes/{pacienteId}/diagnosticos
    @GetMapping("/{pacienteId}/diagnosticos")
    public ResponseEntity<List<DiagnosticoDTO>> obtenerDiagnosticos(@PathVariable("pacienteId") Long pacienteId) {
        return ResponseEntity.ok(pacienteService.listarDiagnosticos(pacienteId));
    }
}