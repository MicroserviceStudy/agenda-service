package microstudy.com.agenda.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "agenda")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Agenda {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String descricao;

    @Column(name = "data_hora")
    private LocalDateTime horario;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @ManyToOne
    private Paciente paciente;
}
