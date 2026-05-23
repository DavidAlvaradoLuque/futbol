package com.futbol.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "competiciones")
public class Competicion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(nullable = false)
    private String nombre;

    @Column(name = "monto_premio")
    private int montoPremio;

    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    // Relacion inversa ManyToMany con Club
    @ManyToMany(mappedBy = "competiciones")
    private List<Club> clubes;

    public Competicion() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public int getMontoPremio() { return montoPremio; }
    public void setMontoPremio(int montoPremio) { this.montoPremio = montoPremio; }
    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }
    public LocalDate getFechaFin() { return fechaFin; }
    public void setFechaFin(LocalDate fechaFin) { this.fechaFin = fechaFin; }
    public List<Club> getClubes() { return clubes; }
    public void setClubes(List<Club> clubes) { this.clubes = clubes; }
}
