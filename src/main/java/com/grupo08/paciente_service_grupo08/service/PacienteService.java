package com.grupo08.paciente_service_grupo08.service;

import com.grupo08.paciente_service_grupo08.dto.DiagnosticoDTO;
import com.grupo08.paciente_service_grupo08.dto.PacienteHistorialDTO;
import com.grupo08.paciente_service_grupo08.entity.Diagnostico;
import com.grupo08.paciente_service_grupo08.entity.Paciente;
import com.grupo08.paciente_service_grupo08.repository.DiagnosticoRepository;
import com.grupo08.paciente_service_grupo08.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PacienteService {

    private final PacienteRepository pacienteRepository;
    private final DiagnosticoRepository diagnosticoRepository;

    @Transactional(readOnly = true)
    public PacienteHistorialDTO obtenerHistorial(Long pacienteId) {
        Paciente paciente = buscarPaciente(pacienteId);

        List<DiagnosticoDTO> diagnosticosActivos = paciente.getDiagnosticos().stream()
                .filter(d -> Boolean.TRUE.equals(d.getActivo()))
                .sorted(Comparator.comparing(Diagnostico::getFechaDiagnostico).reversed())
                .map(this::toDiagnosticoDTO)
                .toList();

        return PacienteHistorialDTO.builder()
                .pacienteId(paciente.getId())
                .numeroDocumento(paciente.getNumeroDocumento())
                .nombreCompleto(paciente.getNombreCompleto())
                .tipoSeguro(paciente.getTipoSeguro())
                .listaDiagnosticosActivos(diagnosticosActivos)
                .estadoPaciente(paciente.getEstadoPaciente())
                .build();
    }

    @Transactional(readOnly = true)
    public List<DiagnosticoDTO> listarDiagnosticos(Long pacienteId) {
        buscarPaciente(pacienteId); // valida que exista, si no lanza 404

        return diagnosticoRepository.findByPacienteIdOrderByFechaDiagnosticoDesc(pacienteId)
                .stream()
                .map(this::toDiagnosticoDTO)
                .toList();
    }

    private Paciente buscarPaciente(Long pacienteId) {
        return pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Paciente no encontrado con id: " + pacienteId));
    }

    private DiagnosticoDTO toDiagnosticoDTO(Diagnostico d) {
        return DiagnosticoDTO.builder()
                .idDiagnostico(d.getId())
                .codigoCIE10(d.getCodigoCIE10())
                .descripcion(d.getDescripcion())
                .fechaDiagnostico(d.getFechaDiagnostico())
                .gravedad(d.getGravedad())
                .build();
    }
}