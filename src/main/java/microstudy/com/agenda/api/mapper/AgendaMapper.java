package microstudy.com.agenda.api.mapper;

import lombok.experimental.UtilityClass;
import microstudy.com.agenda.api.request.AgendaRequest;
import microstudy.com.agenda.api.response.AgendaResponse;
import microstudy.com.agenda.api.response.PacienteResponse;
import microstudy.com.agenda.domain.entity.Agenda;
import microstudy.com.agenda.domain.entity.Paciente;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@UtilityClass
public class AgendaMapper {

    public static AgendaResponse toAgendaResponse(Agenda agenda) {
        return AgendaResponse.builder()
                .id(agenda.getId())
                .descricao(agenda.getDescricao())
                .horario(agenda.getHorario())
                .dataCriacao(agenda.getDataCriacao())
                .paciente(PacienteResponse.builder()
                        .id(agenda.getPaciente().getId())
                        .cpf(agenda.getPaciente().getCpf())
                        .nome(agenda.getPaciente().getNome() + " " + agenda.getPaciente().getSobrenome())
                        .email(Objects.nonNull(agenda.getPaciente().getEmail()) ? agenda.getPaciente().getEmail() : null)
                        .build())
                .build();
    }

    public Agenda toAgenda(AgendaRequest agendaRequest, PacienteResponse pacienteResponse) {
        return Agenda.builder()
                .descricao(agendaRequest.getDescricao())
                .dataCriacao(LocalDateTime.now())
                .horario(agendaRequest.getHorario())
                .paciente(Paciente.builder()
                        .id(pacienteResponse.getId())
                        .nome(pacienteResponse.getNome())
                        .sobrenome(pacienteResponse.getSobrenome())
                        .cpf(pacienteResponse.getCpf())
                        .email(pacienteResponse.getEmail())
                        .build())
                .build();
    }

    public List<AgendaResponse> convertListResponse(List<Agenda> agendaList) {
        return agendaList.stream().map(AgendaMapper::toAgendaResponse).collect(Collectors.toList());
    }
}
