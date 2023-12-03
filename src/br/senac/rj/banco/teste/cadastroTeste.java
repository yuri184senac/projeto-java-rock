package br.senac.rj.banco.teste;

import br.senac.rj.banco.modelo.Banda;

public class cadastroTeste {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Banda banda = new Banda();
		banda.setId_banda(1);
		banda.setNome("Angra");
		banda.setPais("Brasil");
		banda.cadastrarBanda("Heavy Metal");
		
	}

}
