package Med.Voll.Api_Rest.domain.Consulta;

import Med.Voll.Api_Rest.domain.Medico.Medico;
import Med.Voll.Api_Rest.domain.Paciente.Paciente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import Med.Voll.Api_Rest.domain.Validador.MotivoCancelamento;

import java.time.LocalDateTime;


@Table(name = "consultas")
@Entity(name = "Consulta")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_id")
    private Medico medico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    private LocalDateTime data;

    @Column(name = "motivo_cancelamento")
    @Enumerated(EnumType.STRING)
    private MotivoCancelamento cancelamento;

    public void cancelar(MotivoCancelamento motivo){
        this.cancelamento = motivo;
    }
}
