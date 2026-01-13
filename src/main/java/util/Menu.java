package util;

import Entidades.Conta;
import Entidades.Operacao;
import Service.ContaService;
import Service.CriarContaService;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    public static void imprimirMenu() {
        System.out.println("\n==== MENU BANCO ====");
        System.out.println("1 - Criar Conta");
        System.out.println("2 - Depositar / Sacar");
        System.out.println("2 - Sacar");
        System.out.println("4 - Exibir Saldo");
        System.out.println("5 - Transferencia");
        System.out.println("6 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    public static void iniciarMenu() {
        int opcao;
        Long id;
        String cpf;
        Scanner sc = new Scanner(System.in);
        do {
            Menu.imprimirMenu();
            opcao = sc.nextInt();
            sc.nextLine();
            switch (opcao) {
                case 1:
                    System.out.println("Digite o nome do titular");
                    String nomeDoTitular = sc.nextLine();
                    System.out.println("Digite o cpf do titular");
                    String cpfDoTitular = sc.nextLine();
                    try {
                        CriarContaService.getInstance().criarConta(nomeDoTitular, cpfDoTitular, new BigDecimal("0"));
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:

                    try {
                        System.out.println("Digite 1 para depositar ou 2 para sacar");
                        opcao = sc.nextInt();
                        sc.nextLine();
                        Operacao operacao = switch (opcao) {
                            case 1 -> Operacao.DEPOSITO;
                            case 2 -> Operacao.SAQUE;
                            default -> throw new IllegalArgumentException("Opção inválida");
                        };
                        System.out.println("Digite o CPF da conta: ");
                        cpf = sc.nextLine();
                        Conta conta = ContaService.getInstance().buscaContaPorCPF(cpf);
                        System.out.print("Digite o valor: ");
                        BigDecimal valor = sc.nextBigDecimal();
                        operacao.executarOperacao(conta, valor);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    } catch (InputMismatchException e) {
                        System.out.println("Operação cancelada"); // melhorar essa mensagem depois :>
                    } catch (IllegalStateException e) {
                        System.out.println(e.getMessage());
                    }


            }
        } while (opcao != 6);
    }

}
