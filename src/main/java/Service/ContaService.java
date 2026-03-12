package Service;

import Dao.ContaDao;
import Dao.TitularDao;
import Entidades.Conta;
import util.CpfUtil;
import util.JDBCUtil;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

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

    public void transferir(Conta contaOrigem, Conta contaDestino, BigDecimal valor){

        Connection conn = null;

        try {
            conn = JDBCUtil.getInstance().getConnection();

            conn.setAutoCommit(false);
            contaOrigem.debitar(valor);
            contaDestino.creditar(valor);

            contaDao.debitar(contaOrigem, conn);
            contaDao.creditar(contaDestino, conn);
            conn.commit();
        } catch (Exception e) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            throw new RuntimeException("Erro na transferência", e);

        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }

    public Conta buscaContaPorCPF(String cpf) {
        String cpfLimpo = CpfUtil.limparCpf(cpf);
        if (!CpfUtil.verificarCpfValido(cpfLimpo))
            throw new IllegalArgumentException("Cpf Invalido, cancelando operação!");
        Conta conta = contaDao.buscarContaPorCPF(cpfLimpo);
        if (conta == null) {
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
