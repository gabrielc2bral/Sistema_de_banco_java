package Entidades;

import Dao.ContaDao;

import java.math.BigDecimal;

public enum Operacao {
    SAQUE {
        @Override
        public void executarOperacao(Conta conta, BigDecimal valor) {
            conta.saque(valor);
            ContaDao.getInstance().atualizarSaldoDaConta(conta);
        }
    },
    DEPOSITO {
        @Override
        public void executarOperacao(Conta conta, BigDecimal valor) {
            conta.depositar(valor);
            ContaDao.getInstance().atualizarSaldoDaConta(conta);
        }
    };

    public abstract void executarOperacao(Conta conta, BigDecimal valor);
}
