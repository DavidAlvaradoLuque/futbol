package com.futbol.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "asociaciones")
public class Asociacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(nullable = false)
    private String nombre;

    @NotBlank(message = "El país es obligatorio")
    private String pais;

    @NotBlank(message = "El presidente es obligatorio")
    private String presidente;

    // Una asociacion tiene muchos clubes
    @OneToMany(mappedBy = "asociacion")
    private List<Club> clubes;

    public Asociacion() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getPais() { return pais; }
    public void setPais(String pais) { this.pais = pais; }
    public String getPresidente() { return presidente; }
    public void setPresidente(String presidente) { this.presidente = presidente; }
    public List<Club> getClubes() { return clubes; }
    public void setClubes(List<Club> clubes) { this.clubes = clubes; }
}
