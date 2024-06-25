import service.Funcionario;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        //Inserindo manualmente os dados
        List<Funcionario> funcionarios = new ArrayList<>(Arrays.asList(
                new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"),
                new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"),
                new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"),
                new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"),
                new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"),
                new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"),
                new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"),
                new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"),
                new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"),
                new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente")
        ));

        //Remover o funcionário “João” da lista.
        funcionarios.removeIf(remove -> remove.getNome().equals("João"));

        //Imprimir todos os funcionários com todas suas informações.
        System.out.println("TODOS OS FUNCIONÁRIOS COM TODAS AS INFORMAÇÕES, MENOS O JOÃO");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        funcionarios.forEach(f -> {
            System.out.println("Nome: " + f.getNome());
            System.out.println("Data de Nascimento: " + f.getDataNascimento().format(dateFormatter));
            System.out.println("Salário: " + String.format("%,.2f", f.getSalario()));
            System.out.println("Função: " + f.getFuncao());
            System.out.println();
        });

        //Os funcionários receberam 10% de aumento de salário, atualizar a lista de funcionários com novo valor.
        funcionarios.forEach(f -> f.setSalario(f.getSalario().multiply(new BigDecimal("1.1"))));

        //Agrupar os funcionários por função em um MAP.
        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));

        //Imprimir os funcionários, agrupados por função.
        System.out.println("OS FUNCIONÁRIOS, AGRUPADOS PRO FUNÇÃO");
        funcionariosPorFuncao.forEach((funcao, lista) -> {
            System.out.println("Função: " + funcao);
            lista.forEach(f -> System.out.println("  " + f.getNome()));
        });
        System.out.println();
        //Imprimir os funcionários que fazem aniversário no mês 10 e 12.
        System.out.println("FUNCIONÁRIOS QUE FAZEM ANIVERSÁRIO em OUTUBRO e DEZEMBRO:");
        funcionarios.stream()
                .filter(f -> f.getDataNascimento().getMonthValue() == 10 || f.getDataNascimento().getMonthValue() == 12)
                .forEach(f -> System.out.println(f.getNome()));

        System.out.println();
        //Imprimir o funcionário com a maior idade.
        System.out.println("O FUNCIONÁRIO COM A MAIOR IDADE");
        //Imprimir o funcionário com a maior idade.
        Funcionario maisVelho = Collections.min(funcionarios, Comparator.comparing(Funcionario::getDataNascimento));
        int idadeMaisVelho = LocalDate.now().getYear() - maisVelho.getDataNascimento().getYear();
        System.out.println("Funcionário com maior idade: " + maisVelho.getNome() + ", Idade: " + idadeMaisVelho);


        System.out.println();
        //Imprimir a lista de funcionários por ordem alfabética.
        System.out.println("A LISTA DE FUNCIONÁRIOS POR ORDEM ALFABÉTICA");
        System.out.println("Funcionários em ordem alfabética:");
        funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .forEach(f -> System.out.println(f.getNome()));

        System.out.println();
        //Imprimir o total dos salários dos funcionários.
        System.out.println("O TOTAL DOS SALÁRIOS DOS FUNCIONÁRIOS");
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("Total dos salários: " + String.format("%,.2f", totalSalarios));

        System.out.println();
        //Imprimir quantos salários mínimos ganha cada funcionário.
        System.out.println("QUANTOS SALÁRIOS MÍNOMOS GANHA CADA FUNCIONÁRIO");
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        funcionarios.forEach(f -> {
            BigDecimal salariosMinimos = f.getSalario().divide(salarioMinimo, 2, BigDecimal.ROUND_HALF_UP);
            System.out.println(f.getNome() + " ganha " + salariosMinimos + " salários mínimos.");
        });
    }
}