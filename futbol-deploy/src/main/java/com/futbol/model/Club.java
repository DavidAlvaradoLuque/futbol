package com.futbol.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "clubes")
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(nullable = false)
    private String nombre;

    @NotBlank(message = "La ciudad es obligatoria")
    private String ciudad;

    @Min(value = 1800, message = "Año de fundación inválido")
    private int fundacion;

    private String estadio;

    // @OneToOne - Un club tiene un entrenador
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "entrenador_id")
    private Entrenador entrenador;

    // @ManyToOne - Muchos clubes pertenecen a una asociacion
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "asociacion_id")
    private Asociacion asociacion;

    // @OneToMany - Un club tiene muchos jugadores
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id")
    private List<Jugador> jugadores;

    // @ManyToMany - Un club participa en muchas competiciones
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "clubes_competiciones",
        joinColumns = @JoinColumn(name = "club_id"),
        inverseJoinColumns = @JoinColumn(name = "competicion_id")
    )
    private List<Competicion> competiciones;

    public Club() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }
    public int getFundacion() { return fundacion; }
    public void setFundacion(int fundacion) { this.fundacion = fundacion; }
    public String getEstadio() { return estadio; }
    public void setEstadio(String estadio) { this.estadio = estadio; }
    public Entrenador getEntrenador() { return entrenador; }
    public void setEntrenador(Entrenador entrenador) { this.entrenador = entrenador; }
    public Asociacion getAsociacion() { return asociacion; }
    public void setAsociacion(Asociacion asociacion) { this.asociacion = asociacion; }
    public List<Jugador> getJugadores() { return jugadores; }
    public void setJugadores(List<Jugador> jugadores) { this.jugadores = jugadores; }
    public List<Competicion> getCompeticiones() { return competiciones; }
    public void setCompeticiones(List<Competicion> competiciones) { this.competiciones = competiciones; }
}
