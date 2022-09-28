package microstudy.com.agenda.api.mapper;

import lombok.RequiredArgsConstructor;
import microstudy.com.agenda.api.request.PacienteRequest;
import microstudy.com.agenda.api.response.PacienteResponse;
import microstudy.com.agenda.domain.entity.Paciente;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PacienteMapper {

    private final ModelMapper mapper;

    public Paciente toPaciente(PacienteRequest pacienteRequest) {
        return mapper.map(pacienteRequest, Paciente.class);
    }

    public PacienteResponse toPacienteResponse(Paciente paciente) {
        return mapper.map(paciente, PacienteResponse.class);
    }

//    public static Paciente toPaciente(PacienteRequest pacienteRequest) {
//        var paciente = new Paciente();
//        paciente.setNome(pacienteRequest.getNome());
//        paciente.setSobrenome(pacienteRequest.getSobrenome());
//        paciente.setEmail(pacienteRequest.getEmail());
//        paciente.setCpf(pacienteRequest.getCpf());
//        return paciente;
//    }

//    public static PacienteResponse toPacienteResponse(Paciente paciente) {
//        var pacienteResponse = new PacienteResponse();
//        pacienteResponse.setId(paciente.getId());
//        pacienteResponse.setNome(paciente.getNome());
//        pacienteResponse.setSobrenome(paciente.getSobrenome());
//        pacienteResponse.setEmail(paciente.getEmail());
//        pacienteResponse.setCpf(paciente.getCpf());
//        return pacienteResponse;
//    }

    public static PacienteResponse toPacienteResponseUpdate(Paciente paciente) {
        var pacienteResponse = new PacienteResponse();
        pacienteResponse.setId(paciente.getId());
        pacienteResponse.setNome(paciente.getNome());
        pacienteResponse.setSobrenome(paciente.getSobrenome());
        return pacienteResponse;
    }

    public List<PacienteResponse> convertToListResponse(List<Paciente> pacienteList) {
        return pacienteList.stream().map(this::toPacienteResponse).collect(Collectors.toList());
    }
}
