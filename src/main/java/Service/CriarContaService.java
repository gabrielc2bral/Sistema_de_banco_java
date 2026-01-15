package Service;

import Dao.ContaDao;
import Dao.TitularDao;
import Entidades.Conta;
import Entidades.Titular;
import util.CpfUtil;

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
        String cpfLimpo = CpfUtil.limparCpf(cpf);
        if (!CpfUtil.verificarCpfValido(cpfLimpo)) throw new IllegalArgumentException("Cpf Invalido, cancelando operação!");
        Titular titular = titularDao.buscarPorCpf(cpfLimpo);
        if (titular == null) {
            titular = new Titular(nome, cpfLimpo);
            titularDao.salvar(titular);
            Conta conta = new Conta(titular, saldo);
            contaDao.salvar(conta);
            System.out.println("Conta criada com sucesso");
        } else System.out.println("Conta ja existe");
    }
}
