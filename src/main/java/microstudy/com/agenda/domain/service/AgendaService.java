package microstudy.com.agenda.domain.service;

import lombok.RequiredArgsConstructor;
import microstudy.com.agenda.api.mapper.AgendaMapper;
import microstudy.com.agenda.api.request.AgendaRequest;
import microstudy.com.agenda.api.response.AgendaResponse;
import microstudy.com.agenda.api.response.PacienteResponse;
import microstudy.com.agenda.domain.entity.Agenda;
import microstudy.com.agenda.domain.repository.AgendaRepository;
import microstudy.com.agenda.exception.BusinessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AgendaService {

    private final AgendaRepository agendaRepository;
    private final PacienteService pacienteService;

    public List<AgendaResponse> buscarTodos() {
        var agendaList = agendaRepository.findAll();
        return AgendaMapper.convertListResponse(agendaList);
    }

    public AgendaResponse buscarPorId(Long id) {
        var agenda = verificaAgendaExiste(id);
        return AgendaMapper.toAgendaResponse(agenda);
    }

    public AgendaResponse salvar(AgendaRequest agendaRequest) {
        var pacienteResponse = verificaPacienteExiste(agendaRequest.getIdPaciente());
        verificaHorario(agendaRequest.getHorario());
        var agenda = agendaRepository.save(AgendaMapper.toAgenda(agendaRequest, pacienteResponse));
        return AgendaMapper.toAgendaResponse(agenda);
    }

    public void deletar(Long id) {
        verificaAgendaExiste(id);
        agendaRepository.deleteById(id);
    }

    private Agenda verificaAgendaExiste(Long id) {
        return agendaRepository.findById(id).orElseThrow(() -> new BusinessException("Agenda não encontrada com o id: " + id));
    }

    private PacienteResponse verificaPacienteExiste(Long id) {
        return pacienteService.buscarPorId(id);
    }

    private void verificaHorario(LocalDateTime horario) {
        var agendaOptional = agendaRepository.findByHorario(horario);
        if(agendaOptional.isPresent()) {
            throw new BusinessException("Horário indisponível.");
        }
    }
}
