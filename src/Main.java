import Entidades.Banco;
import Entidades.Conta;
import Entidades.Depositar;
import Entidades.Sacar;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Banco banco = new Banco();
        Sacar sacar = new Sacar();
        Depositar depositar = new Depositar();
        int opcao;

        do {
            System.out.println("\n==== MENU BANCO ====");
            System.out.println("1 - Criar Conta");
            System.out.println("2 - Depositar");
            System.out.println("3 - Sacar");
            System.out.println("4 - Exibir Saldo");
            System.out.println("5 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = sc.nextInt();
            sc.nextLine();
            switch (opcao){
                case 1:
                    System.out.println("Digite o nome da pessoa");
                    String nome = sc.nextLine();
                    System.out.println("Digite o cpf da pessoa");
                    String cpf = sc.nextLine();
                    System.out.println("Digite o numero da conta");
                    String numeroDaConta = sc.nextLine();
                    Conta contaNova = new Conta(nome, cpf, numeroDaConta);
                    banco.addConta(contaNova);
                    break;
                case 2:
                    System.out.println("Digite o numero da conta");
                    String numerocontaDeposito = sc.nextLine();
                    Conta contaDeposito = banco.buscarConta(numerocontaDeposito);
                    if (contaDeposito != null){
                        System.out.println("Digite a quantia do deposito");
                        depositar.executar(sc.nextDouble(), contaDeposito);
                        System.out.printf("%nSaldo da conta %.2f", contaDeposito.getSaldo());
                    }else {
                        System.out.println("Sua conta não existe");
                    }
                    break;
                case 3:
                    System.out.println("Digite o numero da conta");
                    String contaSaque = sc.nextLine();
                    Conta contaSaq = banco.buscarConta(contaSaque);
                    if (contaSaq != null){
                        System.out.println("Digite a quantia do saque");
                        sacar.executar(sc.nextDouble(),contaSaq);
                        System.out.printf("%nSaldo da conta %.2f", contaSaq.getSaldo());
                    }else {
                        System.out.println("Sua conta não existe");
                    }
                    break;
                case 4:
                    System.out.println("Digite o numero da conta");
                    String contaSaldo = sc.nextLine();
                    Conta contaSald = banco.buscarConta(contaSaldo);
                    if (contaSald != null){
                        System.out.printf("%nSaldo da conta %.2f", contaSald.getSaldo());
                    }else {
                        System.out.println("Sua conta não existe");
                    }
                    break;

            }



        }while (opcao !=5);

        sc.close();
    }
}
