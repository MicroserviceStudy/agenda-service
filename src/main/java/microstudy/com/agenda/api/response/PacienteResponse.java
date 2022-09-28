package microstudy.com.agenda.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PacienteResponse {

    private Long id;

    private String nome;

    private String sobrenome;

    private String email;

    private String cpf;
}
