package com.portfolio.gonzobknd.repository;

import com.portfolio.gonzobknd.entity.Proyectos;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProyectosRepository extends JpaRepository<Proyectos, Long> {
    public Optional<Proyectos> findByNombrePro(String nombrePro);
    public boolean existsByNombrePro(String nombrePro);
    
}
