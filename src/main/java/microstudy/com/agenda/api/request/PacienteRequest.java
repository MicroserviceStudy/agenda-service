package microstudy.com.agenda.api.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PacienteRequest {

    @NotBlank(message = "Nome do paciente é obrigatório")
    private String nome;

    @NotBlank(message = "Sobrenome do paciente é obrigatório")
    private String sobrenome;

    private String email;

    @NotBlank(message = "Cpf do paciente é obrigatório")
    private String cpf;
}
