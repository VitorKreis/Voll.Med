package Med.Voll.Api_Rest.domain.Validador;

import Med.Voll.Api_Rest.domain.Consulta.ListaConsultaDto;

public record MotivoCancelamentoDTO(Long consulta_id, ListaConsultaDto.MotivoCancelamento motivo) {
}
