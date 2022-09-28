package microstudy.com.agenda.api.request;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME;

@Data
public class AgendaRequest {

    @NotBlank(message = "Descrição é obrigatório")
    private String descricao;

    @NotNull(message = "Horário é obrigatório")
    @FutureOrPresent(message = "O horário não pode ser menor que o horário e data atual")
    @DateTimeFormat(iso = DATE_TIME)
    private LocalDateTime horario;

    @NotNull(message = "Id do paciente é obrigatório")
    private Long idPaciente;
}
