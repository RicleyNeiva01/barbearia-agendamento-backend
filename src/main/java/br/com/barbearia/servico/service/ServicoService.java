package br.com.barbearia.servico.service;

import br.com.barbearia.infrastructure.exception.BusinessException;
import br.com.barbearia.servico.model.Servico;
import br.com.barbearia.servico.repository.ServicoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServicoService implements ServicoIService{

    private final ServicoRepository servicoRepository;

    @Override
    @Transactional
    public Servico save(Servico servico) {
        return servicoRepository.save(servico);
    }

    @Override
    public Page<Servico> findAll(Pageable pageable) {
        return servicoRepository.findAll(pageable);
    }

    @Override
    public Servico findById(Long id) {
        return servicoRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Serviço não encontrado: ID: " + id));
    }

    @Override
    @Transactional
    public Servico update(Long id, Servico servico) {
        Servico existente = findById(id);
        existente.setNome(servico.getNome());
        existente.setDuracaoMinutos(servico.getDuracaoMinutos());
        existente.setPreco(servico.getPreco());

        return servicoRepository.save(existente);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!servicoRepository.existsById(id)){
            throw new BusinessException("Serviço não encontrado. ID: " + id);
        }

        servicoRepository.deleteById(id);
    }
}
