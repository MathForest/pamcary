package br.com.teste.pamcary.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@lombok.Setter
@lombok.Getter
@Entity
@Table(name = "aluno")
@AllArgsConstructor
@NoArgsConstructor
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @NotNull(message = "nome não pode ser nulo")
    public String nome;
    @NotNull(message = "idade não pode ser nula")
    public Integer idade;

}
