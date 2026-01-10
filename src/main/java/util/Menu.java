package util;

import Entidades.Conta;
import Entidades.TipoOperacao;
import Service.ContaService;
import Service.CriarContaService;

import java.math.BigDecimal;
import java.util.Scanner;

public class Menu {
    public static void imprimirMenu() {
        System.out.println("\n==== MENU BANCO ====");
        System.out.println("1 - Criar Conta");
        System.out.println("2 - Depositar / Sacar");
        System.out.println("4 - Exibir Saldo");
        System.out.println("5 - Transferencia");
        System.out.println("6 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    public static void iniciarMenu() {
        int opcao;
        Long id;
        Conta conta;
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
                    CriarContaService.getInstance().criarConta(nomeDoTitular, cpfDoTitular, new BigDecimal("0"));
                    break;
                case 2:
                    System.out.println("Digite 1 para depositar ou 2 para sacar");
                    int i = sc.nextInt();
                    switch (i) {
                        case 1:
                            System.out.println("Digite o ID da conta: ");
                            id = sc.nextLong();
                            conta = ContaService.getInstance().buscaContaPorID(id);
                            sc.nextLine();
                            System.out.println(conta.getId());
                            ContaService.getInstance().operacao(TipoOperacao.DEPOSITO, conta, sc);
                            break;
                        case 2:
                            System.out.println("Digite o ID da conta: ");
                            id = sc.nextLong();
                            sc.nextLine();
                            conta = ContaService.getInstance().buscaContaPorID(id);
                            ContaService.getInstance().operacao(TipoOperacao.SAQUE, conta, sc);
                            break;
                    }

            }
        } while (opcao != 6);
    }

}
