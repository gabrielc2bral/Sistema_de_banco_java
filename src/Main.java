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
        double valor;
        String numeroContaOrigem, numeroContaDestino, nome, cpf, numeroConta;

        do {
            System.out.println("\n==== MENU BANCO ====");
            System.out.println("1 - Criar Conta");
            System.out.println("2 - Depositar");
            System.out.println("3 - Sacar");
            System.out.println("4 - Exibir Saldo");
            System.out.println("5 - Transferencia");
            System.out.println("6 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = sc.nextInt();
            sc.nextLine();
            switch (opcao){
                case 1:
                    System.out.println("Digite o nome da pessoa");
                    nome = sc.nextLine();
                    System.out.println("Digite o cpf da pessoa");
                    cpf = sc.nextLine();
                    System.out.println("Digite o numero da conta");
                    numeroConta = sc.nextLine();
                    Conta contaNova = new Conta(nome, cpf, numeroConta);
                    banco.addConta(contaNova);
                    break;
                case 2:
                    System.out.println("Digite o numero da conta");
                    numeroConta = sc.nextLine();
                    Conta contaDeposito = banco.buscarConta(numeroConta);
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
                    numeroConta = sc.nextLine();
                    Conta contaSaque = banco.buscarConta(numeroConta);
                    if (contaSaque != null){
                        System.out.println("Digite a quantia do saque");
                        sacar.executar(sc.nextDouble(),contaSaque);
                        System.out.printf("%nSaldo da conta %.2f", contaSaque.getSaldo());
                    }else {
                        System.out.println("Sua conta não existe");
                    }
                    break;
                case 4:
                    System.out.println("Digite o numero da conta");
                    numeroConta = sc.nextLine();
                    Conta contaSaldo = banco.buscarConta(numeroConta);
                    if (contaSaldo != null){
                        System.out.printf("%nSaldo da conta %.2f", contaSaldo.getSaldo());
                    }else {
                        System.out.println("Sua conta não existe");
                    }
                    break;
                case 5:
                    System.out.println("Digite o numero da sua conta");
                    numeroContaOrigem = sc.nextLine();
                    Conta conta = banco.buscarConta(numeroContaOrigem);

                    if (conta != null){

                        System.out.println("Digite o numero da conta que você deseja depositar: ");
                        numeroContaDestino = sc.nextLine();
                        Conta contaDestino  = banco.buscarConta(numeroContaDestino);

                        if (contaDestino != null){

                            System.out.printf("Digite o valor que deseja transferir para %s: ", contaDestino.getNome());
                            valor = sc.nextDouble();
                            sc.nextLine();
                            if(valor > 0 && valor <= conta.getSaldo()){
                                conta.setSaldo(conta.getSaldo() - valor);
                                contaDestino.setSaldo(contaDestino.getSaldo() + valor);
                                System.out.println("Sua transferência foi realizada com sucesso");
                                System.out.printf("%nSaldo da sua conta %.2f", contaDestino.getSaldo());
                            }else {
                                System.out.println("Sua conta não tem saldo suficiente");
                        }
                        }else {
                            System.out.println("Essa conta não existe");
                        }
                    }else {
                        System.out.println("Sua conta não existe");
                    }
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção de 1 a 6.");
                    break;
            }

        }while (opcao !=6);

        sc.close();
    }
}
