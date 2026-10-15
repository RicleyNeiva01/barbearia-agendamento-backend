package br.com.barbearia.cliente.service;

import br.com.barbearia.cliente.model.Cliente;
import br.com.barbearia.cliente.repository.ClienteRepository;
import br.com.barbearia.infrastructure.exception.BusinessException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteService implements ClienteIService{

    private final ClienteRepository clienteRepository;

    @Override
    @Transactional
    public Cliente buscarOuCriar(String nome, String telefone) {
        return clienteRepository.findByTelefone(telefone)
                .orElseGet(() -> clienteRepository.save(new Cliente(null, nome, telefone)));
    }

    @Override
    public Cliente findById(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Cliente não encontrado. ID: " + id));
    }
}
