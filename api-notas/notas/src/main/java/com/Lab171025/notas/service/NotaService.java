package com.Lab171025.notas.service;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.Lab171025.notas.model.Nota;
import com.Lab171025.notas.repository.NotaRepository;
import java.util.List;

@Service
public class NotaService {
    private final NotaRepository repo;

    public NotaService(NotaRepository repo) {
        this.repo = repo;
    }

    public Nota crear(Nota nota) {
        return repo.save(nota);
    }

    public List<Nota> listarTodas() {
        return repo.findAll();
    }

    public Nota buscarPorId(Long id) {
        return repo.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró la nota con id " + id));
    }

    public Nota actualizar(Long id, Nota actualizada) {
        Nota n = buscarPorId(id);
        n.setTitulo(actualizada.getTitulo());
        n.setContenido(actualizada.getContenido());
        n.setUltimaActualizacion(LocalDateTime.now());
        return repo.save(n);
    }

    public void eliminar(Long id) {
        if (!repo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró la nota con id " + id);
        }
        repo.deleteById(id);
    }

    public Nota archivar(Long id, boolean archivada) {
        Nota n = buscarPorId(id);
        n.setArchivada(archivada);
        n.setUltimaActualizacion(LocalDateTime.now());
        return repo.save(n);
    }

    public List<Nota> listarPorArchivada(boolean archivada) {
        return repo.findByArchivada(archivada);
    }
}
