package com.futbol.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "entrenadores")
public class Entrenador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(nullable = false)
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    @Column(nullable = false)
    private String apellido;

    @Min(value = 18, message = "La edad debe ser mayor a 18")
    private int edad;

    @NotBlank(message = "La nacionalidad es obligatoria")
    private String nacionalidad;

    // Relacion inversa OneToOne con Club
    @OneToOne(mappedBy = "entrenador")
    private Club club;

    public Entrenador() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }
    public String getNacionalidad() { return nacionalidad; }
    public void setNacionalidad(String nacionalidad) { this.nacionalidad = nacionalidad; }
    public Club getClub() { return club; }
    public void setClub(Club club) { this.club = club; }

    public String getNombreCompleto() { return nombre + " " + apellido; }
}
