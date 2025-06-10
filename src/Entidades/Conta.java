package Entidades;

public class Conta extends Pessoa{
    private String numeroDaConta;
    private double saldo;


    public Conta(String nome, String cpf, String numeroDaConta) {
        super(nome, cpf);
        this.numeroDaConta = numeroDaConta;
    }

    @Override
    public String getCpf() {
        return super.getCpf();
    }

    public String getNumeroDaConta() {
        return numeroDaConta;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getSaldo() {
        return saldo;
    }
    public void sacarSaldo(double saque) {
        if (this.saldo != 0) {
            this.saldo -= saque;
        } else {
            System.out.println("Conta sem saldo");
        }
    }

}
