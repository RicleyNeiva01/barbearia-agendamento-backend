package br.com.barbearia.barbeiro.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "barbeiro")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Barbeiro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    private String especialidade;
}