package Entidades;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import util.IdGenerator;

public class Banco {
    //private ArrayList<Conta> contas;
    private Map<Integer, Conta> contas;
    private String numeroContaOrigem, numeroContaDestino, nome, cpf;
    private int id;
    private double valor;
    private double saldo;
    
    public Banco() {
        this.contas = new HashMap<>();
    }
    public void criarConta(Scanner sc) {
		System.out.println("Digite o nome da pessoa");
		nome = sc.nextLine();
		System.out.println("Digite o cpf da pessoa");
		cpf = sc.nextLine();
		
		Pessoa pessoa = new Pessoa(nome, cpf);
		Conta conta = new Conta(pessoa, IdGenerator.contaId.incrementAndGet()); 
		
		contas.put(conta.getId(), conta);
		System.out.println("Conta criada com sucesso para " + pessoa.getNome());
    }
    
    public void depositar(Scanner sc) {
    	System.out.println("Digite o id da conta");
    	int id = sc.nextInt();
    	System.out.println("Digite o valor do deposito");
    	valor = sc.nextDouble();
    	if(contaExiste(id)) {
    		if(valor <= 0){
    			System.out.println("Valor invalido para deposito!");
    			return;
    		}
    		Conta c = buscarConta(id);
    		saldo = c.getSaldo() + valor;
    		c.setSaldo(saldo);
    	}else {
    		System.out.println("Conta não existe");
    		return;
    	}
    }
    
    public void sacar(Scanner sc) {
    	System.out.println("Digite o id da conta");
    	id = sc.nextInt();
    	System.out.println("Digite o valor do saque");
    	valor = sc.nextDouble();
    	if(contaExiste(id)) {
    		if(valor <= 0){
    			System.out.println("Valor invalido para saque!");
    			return;
    		}
    		Conta c = buscarConta(id);
    		saldo = c.getSaldo() - valor;
    		c.setSaldo(saldo);
    	}else {
    		System.out.println("Conta não existe");
    		return;
    	}
    }
    
    public Conta buscarConta(int id){
        if(contas.containsKey(id)){
        	return contas.get(id);
        }
        return null;
    }
    public boolean contaExiste(int id){
    	if(contas.containsKey(id)){
        	return true;
        }
        return false;
    }
    public boolean cpfExiste(String cpf){
        for (Conta c : contas.values()){
            if(c.getPessoa().getCpf().equals(cpf)){
                return true;
            }
        }
        return false;
    }
}
