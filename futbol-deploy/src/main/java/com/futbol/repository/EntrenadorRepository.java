package com.futbol.repository;
import com.futbol.model.Entrenador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface EntrenadorRepository extends JpaRepository<Entrenador, Long> {}
