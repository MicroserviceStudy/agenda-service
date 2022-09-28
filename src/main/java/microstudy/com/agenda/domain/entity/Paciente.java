package microstudy.com.agenda.domain.entity;

import lombok.Data;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "paciente")
public class Paciente {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String nome;

    private String sobrenome;

    private String email;

    private String cpf;
}
