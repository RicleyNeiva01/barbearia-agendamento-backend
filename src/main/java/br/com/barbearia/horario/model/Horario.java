package br.com.barbearia.horario.model;

import br.com.barbearia.barbeiro.model.Barbeiro;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "horario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Horario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "barbeiro_id", nullable = false)
    private Barbeiro barbeiro;

    @Column(nullable = false)
    private LocalDateTime dataHora;

    @Column(nullable = false)
    private Boolean disponivel = true;
}