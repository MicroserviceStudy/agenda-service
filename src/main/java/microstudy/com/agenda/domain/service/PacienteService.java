package microstudy.com.agenda.domain.service;

import lombok.RequiredArgsConstructor;
import microstudy.com.agenda.api.mapper.PacienteMapper;
import microstudy.com.agenda.api.request.PacienteRequest;
import microstudy.com.agenda.api.request.PacienteUpdateRequest;
import microstudy.com.agenda.api.response.PacienteResponse;
import microstudy.com.agenda.domain.entity.Paciente;
import microstudy.com.agenda.domain.repository.PacienteRepository;
import microstudy.com.agenda.exception.BusinessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

import static microstudy.com.agenda.api.mapper.PacienteMapper.toPacienteResponseUpdate;

@Service
@Transactional
@RequiredArgsConstructor
public class PacienteService {

    private final PacienteRepository pacienteRepository;
    private final PacienteMapper pacienteMapper;

    public PacienteResponse salvar(PacienteRequest pacienteRequest) {

        verificaCpf(pacienteRequest.getCpf());
        verificaEmail(pacienteRequest.getEmail());
        var paciente = pacienteMapper.toPaciente(pacienteRequest);

        return pacienteMapper.toPacienteResponse(pacienteRepository.save(paciente));
    }

    public List<PacienteResponse> listarTodos() {

        var pacienteList = pacienteRepository.findAll();

        return pacienteMapper.convertToListResponse(pacienteList);
    }

    public PacienteResponse buscarPorId(Long id) {
        var paciente = verificarPacienteExiste(id);
        return pacienteMapper.toPacienteResponse(paciente);
    }

    public void deletar(Long id) {
        verificarPacienteExiste(id);
        pacienteRepository.deleteById(id);
    }

    public PacienteResponse alterar(Long id, PacienteUpdateRequest pacienteUpdateRequest) {
        var pacienteASerAlterado = verificarPacienteExiste(id);
        pacienteASerAlterado.setNome(pacienteUpdateRequest.getNome() != null ? pacienteUpdateRequest.getNome() : pacienteASerAlterado.getNome());
        pacienteASerAlterado.setSobrenome(pacienteUpdateRequest.getSobrenome() != null ? pacienteUpdateRequest.getSobrenome() : pacienteASerAlterado.getSobrenome());
        var pacienteSalvo = pacienteRepository.save(pacienteASerAlterado);
        return toPacienteResponseUpdate(pacienteSalvo);
    }

    private Paciente verificarPacienteExiste(Long id) {
        return pacienteRepository.findById(id).orElseThrow(() -> new BusinessException("Paciente não encontrado com o id: " + id));
    }

    private void verificaEmail(String email) {
        var paciente = pacienteRepository.findByEmail(email).orElse(null);
        if(Objects.nonNull(paciente)) {
            throw new BusinessException("E-mail já cadastrado");
        }
    }

    private void verificaCpf(String cpf) {
        var paciente = pacienteRepository.findByCpf(cpf).orElse(null);
        if(Objects.nonNull(paciente)) {
            throw new BusinessException("Cpf já cadastrado");
        }
    }
}
