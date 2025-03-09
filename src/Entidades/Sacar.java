package Entidades;

public class Sacar implements OperacaoBancaria{
    Conta conta;


    @Override
    public void executar(double valor, Conta conta) {
        this.conta = conta;
        double saldo = conta.getSaldo();
        if (valor <= saldo) {
            this.conta.setSaldo(saldo - valor);
        }else {System.out.println("Conta sem saldo");}
    }
}
