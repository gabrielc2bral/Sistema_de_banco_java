import Dao.ContaDao;
import Dao.TitularDao;
import Service.ContaService;
import Service.CriarContaService;

import java.math.BigDecimal;

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
		TitularDao titularDao = new TitularDao();
		ContaDao contaDao = new ContaDao();
		CriarContaService criarContaService = new CriarContaService(titularDao, contaDao);
		ContaService contaService = new ContaService(titularDao, contaDao);
		//criarContaService.criarConta("teste", "11112111111", new BigDecimal("500"));
		contaService.mostarContaPorID(1);

	}
}
