package br.com.barbearia.agendamento.service;

import br.com.barbearia.agendamento.model.Agendamento;
import br.com.barbearia.agendamento.repository.AgendamentoRepository;
import br.com.barbearia.cliente.model.Cliente;
import br.com.barbearia.cliente.service.ClienteIService;
import br.com.barbearia.horario.model.Horario;
import br.com.barbearia.horario.repository.HorarioRepository;
import br.com.barbearia.servico.model.Servico;
import br.com.barbearia.servico.service.ServicoIService;
import br.com.barbearia.infrastructure.exception.BusinessException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AgendamentoService implements AgendamentoIService {

    private final AgendamentoRepository agendamentoRepository;
    private final HorarioRepository horarioRepository;
    private final ServicoIService servicoService;
    private final ClienteIService clienteService;

    @Override
    @Transactional
    public Agendamento agendar(Long horarioId, Long servicoId, String nomeCliente, String telefoneCliente) {

        Horario horario = horarioRepository.findById(horarioId)
                .orElseThrow(() -> new BusinessException("Horário não encontrado. ID: " + horarioId));

        if (!horario.getDisponivel()) {
            throw new BusinessException("Este horário já está reservado");
        }

        Servico servico = servicoService.findById(servicoId);
        Cliente cliente = clienteService.buscarOuCriar(nomeCliente, telefoneCliente);

        horario.setDisponivel(false);
        horarioRepository.save(horario);

        Agendamento agendamento = new Agendamento();
        agendamento.setCliente(cliente);
        agendamento.setHorario(horario);
        agendamento.setServico(servico);
        agendamento.setStatus(Agendamento.StatusAgendamento.CONFIRMADO);

        return agendamentoRepository.save(agendamento);
    }

    @Override
    public Page<Agendamento> findAll(Pageable pageable) {
        return agendamentoRepository.findAll(pageable);
    }

    @Override
    public Agendamento findById(Long id) {
        return agendamentoRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Agendamento não encontrado. ID: " + id));
    }

    @Override
    @Transactional
    public void cancelar(Long id) {
        Agendamento agendamento = findById(id);
        agendamento.setStatus(Agendamento.StatusAgendamento.CANCELADO);

        Horario horario = agendamento.getHorario();
        horario.setDisponivel(true);
        horarioRepository.save(horario);

        agendamentoRepository.save(agendamento);
    }
}