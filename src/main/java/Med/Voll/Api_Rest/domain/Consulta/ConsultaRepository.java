package Med.Voll.Api_Rest.domain.Consulta;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {


        Boolean existsByMedicoIdAndData(Long id, LocalDateTime data);


        Boolean existsByPacienteIdAndDataBetween(Long aLong, LocalDateTime primieroHorario, LocalDateTime ultimoHorario);
}
