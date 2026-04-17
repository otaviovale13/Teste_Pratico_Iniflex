import Models.Funcionario;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.stream.Collectors;
import java.math.RoundingMode;

public class Main {
    private static List<Funcionario> listaFuncionarios = new ArrayList<>();
    private static Map<String, List<Funcionario>> funcionariosAgrupados = new HashMap<>();
    private static Scanner leitor = new Scanner(System.in);
    private static DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        popularLista();
        int opcao = 0;

        do {
            System.out.println("\n--------------------------------------------------------------");
            System.out.println("Bem-Vindo ao Controle de Funcionários da Projedata Informática");
            System.out.println("--------------------------------------------------------------");
            System.out.println("Opções:");
            System.out.println("1 - Inserir Funcionário");
            System.out.println("2 - Remover Funcionário");
            System.out.println("3 - Listar Funcionários");
            System.out.println("4 - Aumentar Salários dos Funcionários");
            System.out.println("5 - Agrupar por Função");
            System.out.println("6 - Listar Agrupados");
            System.out.println("7 - Listar Funcionários que Nasceream no Mês 10 e 12");
            System.out.println("8 - Funcionário Mais Velho");
            System.out.println("9 - Listar Funcionários por Ordem Alfabética");
            System.out.println("10 - Total de Salário de Todos os Funcionários");
            System.out.println("11 - Salários Mínimos por Funcionário");
            System.out.println("12 - Sair");
            System.out.println("--------------------------------------------------------------");
            System.out.print("Digite o número da opção: ");

            opcao = leitor.nextInt();
            leitor.nextLine();

            switch (opcao) {
                case 1:
                    inserir();
                    break;
                case 2:
                    remover();
                    break;
                case 3:
                    listar();
                    break;
                case 4:
                    aumento();
                    break;
                case 5:
                    agruparPorFuncao();
                    break;
                case 6:
                    imprimirAgrupados();
                    break;
                case 7:
                    data();
                    break;
                case 8:
                    idade();
                    break;
                case 9:
                    alfabetica();
                    break;
                case 10:
                    salarios();
                    break;
                case 11:
                    salariosMinimos();
                    break;
                case 12:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 12);
    }

    public static void popularLista() {
        Funcionario f1 = new Funcionario();
        f1.setNome("Maria");
        f1.setDataNascimento(LocalDate.of(2000, 10, 18));
        f1.setSalario(new BigDecimal("2009.44"));
        f1.setFuncao("Operador");
        listaFuncionarios.add(f1);

        Funcionario f2 = new Funcionario();
        f2.setNome("João");
        f2.setDataNascimento(LocalDate.of(1990, 5, 12));
        f2.setSalario(new BigDecimal("2284.38"));
        f2.setFuncao("Operador");
        listaFuncionarios.add(f2);

        Funcionario f3 = new Funcionario();
        f3.setNome("Caio");
        f3.setDataNascimento(LocalDate.of(1961, 5, 2));
        f3.setSalario(new BigDecimal("9836.14"));
        f3.setFuncao("Coordenador");
        listaFuncionarios.add(f3);

        Funcionario f4 = new Funcionario();
        f4.setNome("Miguel");
        f4.setDataNascimento(LocalDate.of(1988, 10, 14));
        f4.setSalario(new BigDecimal("19119.88"));
        f4.setFuncao("Diretor");
        listaFuncionarios.add(f4);

        Funcionario f5 = new Funcionario();
        f5.setNome("Alice");
        f5.setDataNascimento(LocalDate.of(1995, 1, 5));
        f5.setSalario(new BigDecimal("2234.68"));
        f5.setFuncao("Recepcionista");
        listaFuncionarios.add(f5);

        Funcionario f6 = new Funcionario();
        f6.setNome("Heitor");
        f6.setDataNascimento(LocalDate.of(1999, 11, 19));
        f6.setSalario(new BigDecimal("1582.72"));
        f6.setFuncao("Operador");
        listaFuncionarios.add(f6);

        Funcionario f7 = new Funcionario();
        f7.setNome("Arthur");
        f7.setDataNascimento(LocalDate.of(1993, 3, 31));
        f7.setSalario(new BigDecimal("4071.84"));
        f7.setFuncao("Contador");
        listaFuncionarios.add(f7);

        Funcionario f8 = new Funcionario();
        f8.setNome("Laura");
        f8.setDataNascimento(LocalDate.of(1994, 7, 8));
        f8.setSalario(new BigDecimal("3017.45"));
        f8.setFuncao("Gerente");
        listaFuncionarios.add(f8);

        Funcionario f9 = new Funcionario();
        f9.setNome("Heloísa");
        f9.setDataNascimento(LocalDate.of(2003, 5, 24));
        f9.setSalario(new BigDecimal("1606.85"));
        f9.setFuncao("Eletricista");
        listaFuncionarios.add(f9);

        Funcionario f10 = new Funcionario();
        f10.setNome("Helena");
        f10.setDataNascimento(LocalDate.of(1996, 9, 2));
        f10.setSalario(new BigDecimal("2799.93"));
        f10.setFuncao("Gerente");
        listaFuncionarios.add(f10);
    }

    public static void inserir() {
        Funcionario f = new Funcionario();

        System.out.println("\n--- Cadastro de Funcionário ---");

        System.out.print("Nome: ");
        f.setNome(leitor.nextLine());

        System.out.print("Data de Nascimento (dd/mm/aaaa): ");
        String dataStr = leitor.nextLine();
        f.setDataNascimento(LocalDate.parse(dataStr, formatadorData));

        System.out.print("Salário (ex: 1500.50): ");
        f.setSalario(new BigDecimal(leitor.nextLine()));

        System.out.print("Função: ");
        f.setFuncao(leitor.nextLine());

        listaFuncionarios.add(f);

        System.out.println("Funcionário cadastrado com sucesso!");
    }

    public static void remover() {
        System.out.println("\n--- Remoção de Funcionário ---");
        System.out.print("Digite o nome exato para remover: ");
        String nomeParaRemover = leitor.nextLine();

        boolean removido = listaFuncionarios.removeIf(f -> f.getNome().equalsIgnoreCase(nomeParaRemover));

        if (removido) {
            System.out.println("Funcionário removido com sucesso!");
        } else {
            System.out.println("Funcionário não encontrado!");
        }
    }

    public static void listar() {
        System.out.println("\n--- Lista de Funcionários Cadastrados ---");

        DecimalFormat df = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));

        for (Funcionario f : listaFuncionarios) {
            LocalDate data = f.getDataNascimento();
            String dataFormatada = data.format(formatadorData);

            String salarioFormatado = df.format(f.getSalario());

            System.out.println(
                    f.getNome() + " - " +
                            dataFormatada + " - " +
                            f.getFuncao() + " - R$ " +
                            salarioFormatado
            );
        }
    }

    public static void aumento() {
        System.out.println("\n--- Aumento de Salário (10%) ---");
        System.out.print("Digite o nome para dar o aumento: ");
        String nome = leitor.nextLine();

        boolean encontrado = false;

        for (Funcionario f : listaFuncionarios) {
            if (f.getNome().equalsIgnoreCase(nome)) {
                BigDecimal novoSalario = f.getSalario().multiply(new BigDecimal("1.10"));
                f.setSalario(novoSalario);

                encontrado = true;
                System.out.println("Aumento aplicado! Novo salário: R$ " + novoSalario);
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Funcionário não encontrado!");
        }
    }

    public static void agruparPorFuncao() {
        System.out.println("\n--- Agrupando Funcionários por Função ---");

        funcionariosAgrupados = listaFuncionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));

        System.out.println("Funcionários agrupados com sucesso no Map!");
    }

    public static void imprimirAgrupados() {
        System.out.println("\n--- Funcionários Agrupados por Função ---");

        if (funcionariosAgrupados.isEmpty()) {
            System.out.println("Nenhum agrupamento feito ainda. Execute a opção de agrupar primeiro.");
            return;
        }

        DecimalFormat df = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));

        for (String funcao : funcionariosAgrupados.keySet()) {
            System.out.println("\nFunção: " + funcao);
            System.out.println("---------------------------------");

            List<Funcionario> listaDaFuncao = funcionariosAgrupados.get(funcao);

            for (Funcionario f : listaDaFuncao) {
                String dataFormatada = f.getDataNascimento().format(formatadorData);
                String salarioFormatado = df.format(f.getSalario());

                System.out.println("  -> " + f.getNome() + " | Nasc: " + dataFormatada + " | Salário: R$ " + salarioFormatado);
            }
        }
    }

    public static void data() {
        System.out.println("\n--- Lista de Funcionários Cadastrados que Nasceram no Mês 10 e 12 ---");

        List<Funcionario> listaData = listaFuncionarios.stream()
                .filter(f -> f.getDataNascimento().getMonthValue() == 10 ||
                        f.getDataNascimento().getMonthValue() == 12)
                .collect(Collectors.toList());

        for (Funcionario f : listaData) {
            LocalDate data = f.getDataNascimento();
            String dataFormatada = data.format(formatadorData);

            System.out.println(
                    f.getNome() + " - " +
                            dataFormatada
            );
        }
    }

    public static void idade() {
        System.out.println("\n--- Funcionário Cadastrado com Maior Idade ---");

        Funcionario maisVelho = listaFuncionarios.stream()
                .min(Comparator.comparing(Funcionario::getDataNascimento))
                .orElse(null);

        int Idade = Period.between(maisVelho.getDataNascimento(), LocalDate.now()).getYears();

        if (maisVelho != null) {
            System.out.println("Funcionário mais velho: " + maisVelho.getNome());
            System.out.println("Idade: " + Idade + " anos");
        }
    }

    public static void alfabetica() {
        System.out.println("\n--- Listar funcionários por Ordem Alfabética ---");

        listaFuncionarios.sort(Comparator.comparing(Funcionario::getNome));

        for (Funcionario f : listaFuncionarios) {
            System.out.println(f.getNome());
        }
    }

    public static void salarios() {
        System.out.println("\n--- Total de Salário de Todos os Funcionários ---");

        BigDecimal totalSalario = listaFuncionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println("Total de salários: " + totalSalario);
    }

    public static void salariosMinimos() {
        System.out.println("\n--- Quantidade de Salários Mínimos por Funcionário ---");

        BigDecimal salarioMinimo = new BigDecimal("1212.00");

        for (Funcionario f : listaFuncionarios) {
            BigDecimal qtdSalarios = f.getSalario().divide(salarioMinimo, 2, RoundingMode.HALF_UP);

            DecimalFormat df = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));

            System.out.println(f.getNome() + " (R$ " + df.format(f.getSalario()) + ") ganha " + qtdSalarios + " salários mínimos.");
        }
    }
}