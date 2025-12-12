package Entidades;

import java.math.BigDecimal;

public class Conta{
    private long id;
    private BigDecimal saldo;
    private Titular titular;

    public Conta(Titular titular, BigDecimal saldo) {
    	this.titular = titular;
        this.saldo = saldo;
    }
    
    public Titular getTitular() {
		return titular;
	}


	public long getId() {
		return id;
	}

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    @Override
    public String toString() {
        return "Conta{" +
                "saldo=" + saldo +
                '}';
    }
}
