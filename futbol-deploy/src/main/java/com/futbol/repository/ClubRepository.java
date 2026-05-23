package com.futbol.repository;
import com.futbol.model.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {}
