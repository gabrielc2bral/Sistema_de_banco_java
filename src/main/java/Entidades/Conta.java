package Entidades;

import java.math.BigDecimal;

public class Conta {
    private long id;
    private BigDecimal saldo;
    private Titular titular;

    public Conta(Titular titular, BigDecimal saldo) {
        this.titular = titular;
        this.saldo = saldo;
    }

    public void saque(BigDecimal valor) {
        validarValor(valor);
        if (saldo.compareTo(valor) < 0) {
            throw new IllegalStateException("Saldo insuficiente ");
        }
        saldo = saldo.subtract(valor);
    }

    public void depositar(BigDecimal valor) {
        validarValor(valor);
        saldo = saldo.add(valor);
    }

    public void validarValor(BigDecimal valor) {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valor inválido");
        }
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

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public void setTitular(Titular titular) {
        this.titular = titular;
    }

    @Override
    public String toString() {
        return "Conta{" +
                "saldo=" + saldo +
                '}';
    }
}
