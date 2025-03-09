package Entidades;

public class Depositar implements OperacaoBancaria{
    Conta conta;
    @Override
    public void executar(double valor, Conta conta) {
        this.conta = conta;
        double saldo = conta.getSaldo();
        this.conta.setSaldo(saldo + valor);
    }
}
