package br.com.barbearia.horario.service;

import br.com.barbearia.horario.model.Horario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface HorarioIService {
    Horario save(Horario horario);
    Page<Horario> findAll(Pageable pageable);
    List<Horario> listarDisponiveis();
    Horario findById(Long id);
    void delete(Long id);
}