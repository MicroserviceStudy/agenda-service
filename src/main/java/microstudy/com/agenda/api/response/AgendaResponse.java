package microstudy.com.agenda.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AgendaResponse {

    private Long id;

    private String descricao;

    private LocalDateTime horario;

    private LocalDateTime dataCriacao;

    private PacienteResponse paciente;
}
