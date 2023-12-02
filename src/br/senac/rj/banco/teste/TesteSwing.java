package br.senac.rj.banco.teste;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import br.senac.rj.banco.janelas.JanelaBanda;
import br.senac.rj.banco.janelas.JanelaConta;
import br.senac.rj.banco.janelas.JanelaIntegrantes;
import br.senac.rj.banco.janelas.JanelaShow;

public class TesteSwing {

	public static void apresentarMenu() {
		// -------------JANELA PRINCIPAL----------------
		JFrame janelaPrincipal = new JFrame("Shows de Rock"); // Janela Normal
		janelaPrincipal.setTitle("Gestão de Bandas de Rock");
		janelaPrincipal.setResizable(false); // A janela n�o poder� ter o tamanho ajustado
		janelaPrincipal.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		janelaPrincipal.setSize(400, 300); // Define tamanho da janela
		UIManager.put("OptionPane.yesButtonText", "Sim"); 
		UIManager.put("OptionPane.noButtonText", "N�o");
		// Janela principal
		JMenuBar menuBar = new JMenuBar();		
		janelaPrincipal.setJMenuBar(menuBar);
		//----------------BANDA-----------------
		//Janela Banda
		JFrame janelaBanda = JanelaBanda.criarJanelaBanda();
		
		//Menu Banda
		JMenu menuBanda = new JMenu("Banda");
		menuBar.add(menuBanda);
		
		//SubMenus Banda
		JMenuItem subMenuAtualizarBanda = new JMenu("Atualizar");
		menuBanda.add(subMenuAtualizarBanda);
		
		//----------------INTEGRANTES---------------
		//Janela Integrantes		
		JFrame janelaIntegrantes = JanelaIntegrantes.criarJanelaIntegrantes();	
		
		//Menu Integrantes
		JMenu menuIntegrantes = new JMenu("Integrantes");
		menuBar.add(menuIntegrantes);	
		
		//SubMenus Integrantes
		JMenuItem subMenuAtualizarIntegrantes = new JMenu("Atualizar");
		menuIntegrantes.add(subMenuAtualizarIntegrantes);
		
		//-------------------SHOW---------------------------
		//Janela Show		 	
		JFrame janelaShow = JanelaShow.criarJanelaShow();
		
		//Menu Show		
		JMenu menuShow = new JMenu("Show");
		menuBar.add(menuShow);
		
		//SubMenus Show				
		JMenuItem subMenuAtualizarShow = new JMenu("Atualizar");		
		menuShow.add(subMenuAtualizarShow);
		
		
		//Ações dos SubMenus de atualização
		subMenuAtualizarBanda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JanelaBanda.criarJanelaBanda();
			}
		});
		
		//JANELA PRINCIPAL
		janelaPrincipal.setVisible(true);
	}

	public static void main(String[] args) {
		apresentarMenu();
	}
}
