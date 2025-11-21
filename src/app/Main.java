package app;
import Entidades.Banco;
import Entidades.Conta;
import Entidades.Depositar;
import Entidades.Sacar;

import java.util.Scanner;

public class Main {
	
	public static void imprimirMenu() {
		System.out.println("\n==== MENU BANCO ====");
		System.out.println("1 - Criar Conta");
		System.out.println("2 - Depositar");
		System.out.println("3 - Sacar");
		System.out.println("4 - Exibir Saldo");
		System.out.println("5 - Transferencia");
		System.out.println("6 - Sair");
		System.out.print("Escolha uma opção: ");
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Banco banco = new Banco();
		Sacar sacar = new Sacar();
		Depositar depositar = new Depositar();
		int opcao;
		double valor;
		String numeroContaOrigem, numeroContaDestino, nome, cpf, numeroConta;

		do {
			imprimirMenu();
			opcao = sc.nextInt();
			sc.nextLine();
			
			switch (opcao) {
			case 1:
				banco.criarConta(sc);
				break;
			case 2:
				banco.depositar(sc);
				break;
			case 3:
				banco.sacar(sc);
				break;
			case 4:
				System.out.println("Digite o numero da conta");
				numeroConta = sc.nextLine();
				Conta contaSaldo = banco.buscarConta(numeroConta);
				if (contaSaldo != null) {
					System.out.printf("%nSaldo da conta %.2f", contaSaldo.getSaldo());
				} else {
					System.out.println("Sua conta não existe");
				}
				break;
			case 5:
				System.out.println("Digite o numero da sua conta");
				numeroContaOrigem = sc.nextLine();
				Conta conta = banco.buscarConta(numeroContaOrigem);

				if (conta != null) {

					System.out.println("Digite o numero da conta que você deseja depositar: ");
					numeroContaDestino = sc.nextLine();
					Conta contaDestino = banco.buscarConta(numeroContaDestino);

					if (contaDestino != null) {

						System.out.printf("Digite o valor que deseja transferir para %s: ", contaDestino.getNome());
						valor = sc.nextDouble();
						sc.nextLine();
						if (valor > 0 && valor <= conta.getSaldo()) {
							conta.setSaldo(conta.getSaldo() - valor);
							contaDestino.setSaldo(contaDestino.getSaldo() + valor);
							System.out.println("Sua transferência foi realizada com sucesso");
							System.out.printf("%nSaldo da sua conta %.2f", conta.getSaldo());
						} else {
							System.out.println("Sua conta não tem saldo suficiente");
						}
					} else {
						System.out.println("Essa conta não existe");
					}
				} else {
					System.out.println("Sua conta não existe");
				}
				break;
			case 6:
				System.out.println("Saindo....");
				break;
			default:
				System.out.println("Opção inválida. Por favor, escolha uma opção de 1 a 6.");
				break;
			}

		} while (opcao != 6);

		sc.close();
	}
}
