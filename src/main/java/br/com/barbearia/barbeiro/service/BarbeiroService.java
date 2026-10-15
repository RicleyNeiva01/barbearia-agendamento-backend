package br.com.barbearia.barbeiro.service;

import br.com.barbearia.barbeiro.model.Barbeiro;
import br.com.barbearia.barbeiro.repository.BarbeiroRepository;
import br.com.barbearia.infrastructure.exception.BusinessException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BarbeiroService implements BarbeiroIService{

    private final BarbeiroRepository barbeiroRepository;

    @Override
    @Transactional
    public Barbeiro save(Barbeiro barbeiro) {
        return barbeiroRepository.save(barbeiro);
    }

    @Override
    public Page<Barbeiro> findAll(Pageable pageable) {
        return barbeiroRepository.findAll(pageable);
    }

    @Override
    public Barbeiro findById(Long id) {
        return barbeiroRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Barbeiro não encontrado. ID: " + id));
    }

    @Override
    @Transactional
    public Barbeiro update(Long id, Barbeiro barbeiro) {
        Barbeiro existente = findById(id);
        existente.setNome(barbeiro.getNome());
        existente.setEspecialidade(barbeiro.getEspecialidade());
        return barbeiroRepository.save(existente);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!barbeiroRepository.existsById(id)) {
            throw new BusinessException("Barbeiro não encontrado. ID: " + id);
        }
        barbeiroRepository.deleteById(id);
    }
}
