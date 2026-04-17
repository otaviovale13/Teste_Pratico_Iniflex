package Models;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Pessoa {
    private String nome;
    private LocalDate dataNascimento;
}
