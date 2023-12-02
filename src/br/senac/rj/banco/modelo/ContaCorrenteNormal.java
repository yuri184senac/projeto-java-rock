package br.senac.rj.banco.modelo;

public class ContaCorrenteNormal extends Conta {
	public boolean saca (double valor) {
		double novoSaldo = this.saldo - valor;
		if (novoSaldo < 0) 
			return false;
		this.saldo = novoSaldo;
		return true;
	}
}