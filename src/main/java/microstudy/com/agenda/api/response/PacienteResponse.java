package microstudy.com.agenda.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PacienteResponse {

    private Long id;

    private String nome;

    private String sobrenome;

    private String email;

    private String cpf;
}
