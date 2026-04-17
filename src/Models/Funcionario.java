package Models;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Funcionario extends Pessoa {
    private BigDecimal salario;
    private String funcao;
}
