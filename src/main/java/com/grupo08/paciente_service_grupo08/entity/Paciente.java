package com.grupo08.paciente_service_grupo08.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pacientes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 15)
    private String numeroDocumento;

    @Column(nullable = false, length = 150)
    private String nombreCompleto;

    private LocalDate fechaNacimiento;

    // EPS, ESSALUD, PARTICULAR
    @Column(nullable = false, length = 20)
    private String tipoSeguro;

    // ACTIVO, INACTIVO
    @Column(nullable = false, length = 20)
    private String estadoPaciente;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Diagnostico> diagnosticos = new ArrayList<>();

    public void agregarDiagnostico(Diagnostico diagnostico) {
        diagnostico.setPaciente(this);
        this.diagnosticos.add(diagnostico);
    }
}