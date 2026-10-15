package br.com.barbearia.barbeiro.service;

import br.com.barbearia.barbeiro.model.Barbeiro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BarbeiroIService {
    Barbeiro save(Barbeiro barbeiro);
    Page<Barbeiro> findAll(Pageable pageable);
    Barbeiro findById(Long id);
    Barbeiro update(Long id, Barbeiro barbeiro);
    void delete(Long id);
}