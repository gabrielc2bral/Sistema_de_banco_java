package Service;

import Dao.ContaDao;
import Dao.TitularDao;
import Entidades.Conta;
import Entidades.TipoOperacao;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ContaService {

    private TitularDao titularDao;
    private ContaDao contaDao;
    private static ContaService contaService;

    private ContaService() {
        this.titularDao = TitularDao.getInstance();
        this.contaDao = ContaDao.getInstance();
    }

    public static ContaService getInstance() {
        if (contaService == null) {
            contaService = new ContaService();
        }
        return contaService;
    }

    public void mostarContaPorID(long id) {
        Conta conta = contaDao.buscarContaPorId(id);
        if (conta != null) {
            System.out.println(conta.getTitular().toString());
            System.out.println("");
            System.out.println(conta);

        } else {
            System.out.println("Conta não existe");
        }
    }

    public Conta buscaContaPorID(long id) {
        Conta conta = contaDao.buscarContaPorId(id);
        if (conta == null) {
            throw new IllegalStateException("Conta não existe");
        }
        return conta;
    }

    public void operacao(TipoOperacao tipoOperacao, Conta conta, Scanner sc) {
        BigDecimal valor;
        if (tipoOperacao == tipoOperacao.SAQUE) {
            try {
                System.out.println("Digite o valor do saque: ");
                String entrada = sc.nextLine().trim().replace(",", ".");
                valor = new BigDecimal(entrada);
                conta.saque(valor);
            } catch (NumberFormatException e) {
                System.out.println("Valor invalido, cancelando operação.");
                return;
            }catch (IllegalStateException e){
                System.out.println(e.getMessage());
                return;
            }

            ContaDao.getInstance().atualizarSaldoDaConta(conta);
        }
        if (tipoOperacao == tipoOperacao.DEPOSITO) {
            try {
                System.out.println("Digite o valor do Deposito: ");
                String entrada = sc.nextLine().trim().replace(",", ".");
                valor = new BigDecimal(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Valor invalido, cancelando operação.");
                return;
            }
            conta.depositar(valor);
            ContaDao.getInstance().atualizarSaldoDaConta(conta);
        }
    }
}
