package Service;

import Dao.ContaDao;
import Dao.TitularDao;
import Entidades.Conta;
import util.CpfUtil;

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

    public Conta buscaContaPorCPF(String cpf){
        String cpfLimpo = CpfUtil.limparCpf(cpf);
        if (!CpfUtil.verificarCpfValido(cpfLimpo)) throw new IllegalArgumentException("Cpf Invalido, cancelando operação!");
        Conta conta = contaDao.getInstance().buscarContaPorCPF(cpfLimpo);
        if (conta == null){
            throw new IllegalStateException("Conta não existe");
        }
        return conta;
    }

    public Conta buscaContaPorID(long id) {
        Conta conta = contaDao.buscarContaPorId(id);
        if (conta == null) {
            throw new IllegalStateException("Conta não existe");
        }
        return conta;
    }

}
