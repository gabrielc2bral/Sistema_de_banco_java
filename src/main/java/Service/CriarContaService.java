package Service;

import Dao.ContaDao;
import Dao.TitularDao;
import Entidades.Conta;
import Entidades.Titular;

import java.math.BigDecimal;

public class CriarContaService {
    private TitularDao titularDao;
    private ContaDao contaDao;
    private static CriarContaService criarContaService;

    private CriarContaService() {
        this.titularDao = TitularDao.getInstance();
        this.contaDao = ContaDao.getInstance();
    }

    public static CriarContaService getInstance() {
        if (criarContaService == null) {
            criarContaService = new CriarContaService();
        }
        return criarContaService;
    }

    public void criarConta(String nome, String cpf, BigDecimal saldo) {
        Titular titular = titularDao.buscarPorCpf(cpf);
        if (titular == null) {
            titular = new Titular(nome, cpf);
            titularDao.salvar(titular);
            Conta conta = new Conta(titular, saldo);
            contaDao.salvar(conta);
            System.out.println("Conta salva");
        } else System.out.println("Conta ja existe");
    }
}
