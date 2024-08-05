package Med.Voll.Api_Rest.Medico;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

public interface MedicoRepository extends JpaRepository<Medico, Long> {


    List<Medico> findByAtivoTrue();
}
