package br.senac.rj.banco.janelas;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class JanelaShow {
	public static JFrame criarJanelaShow() { 
		JFrame janelaShow = new JFrame("Show");
		// Define a janela
				JFrame janelaConta = new JFrame("Atualiza��o de conta"); // Janela Normal
				janelaConta.setResizable(false); // A janela n�o poder� ter o tamanho ajustado
				janelaConta.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				janelaConta.setSize(400, 300); // Define tamanho da janela
		return janelaShow;
	}
}
