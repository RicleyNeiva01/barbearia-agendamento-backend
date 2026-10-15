package br.com.barbearia.horario.repository;

import br.com.barbearia.horario.model.Horario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Long> {

    List<Horario> findByDisponivelTrue();
}