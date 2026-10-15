package br.com.barbearia.horario.service;

import br.com.barbearia.horario.model.Horario;
import br.com.barbearia.horario.repository.HorarioRepository;
import br.com.barbearia.infrastructure.exception.BusinessException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HorarioService implements HorarioIService{

    private final HorarioRepository horarioRepository;

    @Override
    @Transactional
    public Horario save(Horario horario) {
        return horarioRepository.save(horario);
    }

    @Override
    public Page<Horario> findAll(Pageable pageable) {
        return horarioRepository.findAll(pageable);
    }

    @Override
    public List<Horario> listarDisponiveis() {
        return horarioRepository.findByDisponivelTrue();
    }

    @Override
    public Horario findById(Long id) {
        return horarioRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Horario não encontrado. ID: " + id));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!horarioRepository.existsById(id)){
            throw new BusinessException("Horario não encontrado. ID: " + id);
        }

        horarioRepository.deleteById(id);
    }
}
