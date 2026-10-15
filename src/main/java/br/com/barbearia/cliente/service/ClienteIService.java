package br.com.barbearia.cliente.service;

import br.com.barbearia.cliente.model.Cliente;

public interface ClienteIService {
    Cliente buscarOuCriar(String nome, String telefone);
    Cliente findById(Long id);
}