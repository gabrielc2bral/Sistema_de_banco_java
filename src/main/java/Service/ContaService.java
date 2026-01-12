package Service;

import Dao.ContaDao;
import Dao.TitularDao;
import Entidades.Conta;
import Entidades.Operacao;

import java.math.BigDecimal;
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

}
