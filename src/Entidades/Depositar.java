package Entidades;

public class Depositar implements OperacaoBancaria{
    @Override
    public void executar(double valor, Conta conta) {
        double saldo = conta.getSaldo();
        conta.setSaldo(saldo + valor);
    }
}
