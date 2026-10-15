package br.com.barbearia.barbeiro.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BarbeiroPostRequestDTO {

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    private String especialidade;
}
