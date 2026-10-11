package com.grupo08.paciente_service_grupo08.repository;

import com.grupo08.paciente_service_grupo08.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
