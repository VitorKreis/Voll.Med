package Med.Voll.Api_Rest.Paciente;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    List<Paciente> findByAtivoTrue();
}
