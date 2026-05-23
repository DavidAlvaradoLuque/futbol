package com.futbol.repository;
import com.futbol.model.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface JugadorRepository extends JpaRepository<Jugador, Long> {
    List<Jugador> findByClubId(Long clubId);
}
