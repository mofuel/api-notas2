package com.Lab171025.notas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Lab171025.notas.model.Nota;
import java.util.List;

public interface NotaRepository extends JpaRepository<Nota, Long> {
    List<Nota> findByArchivada(boolean archivada);
}