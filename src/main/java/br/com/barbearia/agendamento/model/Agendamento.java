package br.com.barbearia.agendamento.model;

import br.com.barbearia.cliente.model.Cliente;
import br.com.barbearia.horario.model.Horario;
import br.com.barbearia.servico.model.Servico;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "agendamento")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @OneToOne
    @JoinColumn(name = "horario_id", nullable = false)
    private Horario horario;

    @ManyToOne
    @JoinColumn(name = "servico_id", nullable = false)
    private Servico servico;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusAgendamento status = StatusAgendamento.CONFIRMADO;

    public enum StatusAgendamento {
        CONFIRMADO, CANCELADO
    }
}