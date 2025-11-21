package Entidades;

public class Conta{
    private int id;
    private double saldo;
    private Pessoa pessoa;

    public Conta(Pessoa pessoa, int id) {
    	this.pessoa = pessoa;
        this.id = id;
    }
    
    public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public int getId() {
		return id;
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
