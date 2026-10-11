package com.grupo08.paciente_service_grupo08.repository;

import com.grupo08.paciente_service_grupo08.entity.Diagnostico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiagnosticoRepository extends JpaRepository<Diagnostico, Long> {

    List<Diagnostico> findByPacienteIdOrderByFechaDiagnosticoDesc(Long pacienteId);

}
