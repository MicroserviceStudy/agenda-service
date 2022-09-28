package microstudy.com.agenda.api.controller;

import lombok.RequiredArgsConstructor;
import microstudy.com.agenda.api.request.PacienteRequest;
import microstudy.com.agenda.api.request.PacienteUpdateRequest;
import microstudy.com.agenda.api.response.PacienteResponse;
import microstudy.com.agenda.domain.entity.Paciente;
import microstudy.com.agenda.domain.service.PacienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/paciente")
@RequiredArgsConstructor
public class PacienteController {

    private final PacienteService pacienteService;

    @PostMapping
    public ResponseEntity<PacienteResponse> salvar(@Valid @RequestBody PacienteRequest pacienteRequest) {
        var pacienteSalvo = pacienteService.salvar(pacienteRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteSalvo);
    }

    @GetMapping
    public ResponseEntity<List<PacienteResponse>> buscarTodos() {
        return ResponseEntity.ok(pacienteService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponse> buscarPorId(@PathVariable Long id) {
        var paciente = pacienteService.buscarPorId(id);
        if(!Objects.nonNull(paciente)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(paciente);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        pacienteService.deletar(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteResponse> alterar(@PathVariable Long id, @RequestBody PacienteUpdateRequest pacienteUpdateRequest) {
        var pacienteSalvo = pacienteService.alterar(id, pacienteUpdateRequest);
        return ResponseEntity.ok(pacienteSalvo);
    }
}
