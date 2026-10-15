package br.com.barbearia.agendamento.service;

import br.com.barbearia.agendamento.model.Agendamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AgendamentoIService {
    Agendamento agendar(Long horarioId, Long servicoId, String nomeCliente, String telefoneCliente);
    Page<Agendamento> findAll(Pageable pageable);
    Agendamento findById(Long id);
    void cancelar(Long id);
}