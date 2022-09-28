package microstudy.com.agenda.domain.repository;

import microstudy.com.agenda.domain.entity.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendaRepository extends JpaRepository<Agenda, Long> {
}
