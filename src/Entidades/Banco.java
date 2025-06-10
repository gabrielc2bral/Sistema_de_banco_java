package Entidades;

import java.util.ArrayList;

public class Banco {
    private ArrayList<Conta> contas;

    public Banco() {
        this.contas = new ArrayList<>();
    }
    public void addConta(Conta conta){
        contas.add(conta);
        System.out.println("Conta criada com sucesso para " + conta.getNome());
    }
    public Conta buscarConta(String conta){
        for (Conta c : contas){
            if(c.getNumeroDaConta().equals(conta)){
                return c;
            }
        }
        return null;
    }
    public boolean buscarContaExistente(String conta){
        for (Conta c : contas){
            if(c.getNumeroDaConta().equals(conta)){
                return true;
            }
        }
        return false;
    }
    public boolean verificarCPF(String cpf){
        for (Conta c : contas){
            if(c.getCpf().equals(cpf)){
                return true;
            }
        }
        return false;
    }
}
