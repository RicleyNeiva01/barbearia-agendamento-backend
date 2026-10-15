package br.com.barbearia.servico.service;

import br.com.barbearia.servico.model.Servico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ServicoIService {
    Servico save(Servico servico);
    Page<Servico> findAll(Pageable pageable);
    Servico findById(Long id);
    Servico update(Long id, Servico servico);
    void delete(Long id);
}