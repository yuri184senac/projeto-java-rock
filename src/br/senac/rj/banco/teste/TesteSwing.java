package br.senac.rj.banco.teste;


import java.awt.Container;
import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import br.senac.rj.banco.janelas.JanelaBanda;
import br.senac.rj.banco.janelas.JanelaIntegrantes;
import br.senac.rj.banco.janelas.JanelaListaShows;
import br.senac.rj.banco.janelas.JanelaShow;

public class TesteSwing {
	private static 	JanelaListaShows janelaListaShows;
	public static void apresentarMenu() {
		// -------------JANELA PRINCIPAL----------------
		//VARIAVEIS GLOBAIS
		String path_img1 = "./src/imagem/wings2.png";		
		//Background
		ImageIcon imagemDeFundo = new ImageIcon(path_img1);
		JLabel labelComBackground = new JLabel(imagemDeFundo);
		labelComBackground.setPreferredSize(new Dimension(400, 330));
		
		
        
		
		
		JFrame janelaPrincipal = new JFrame("Shows de Rock"); // Janela Normal
		janelaPrincipal.setTitle("Gestão de Bandas de Rock");
		janelaPrincipal.setResizable(false); 
		janelaPrincipal.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		janelaPrincipal.setSize(400, 330); // Define tamanho da janela		
		janelaPrincipal.add(labelComBackground);				                       
		
		UIManager.put("OptionPane.yesButtonText", "Sim"); 
		UIManager.put("OptionPane.noButtonText", "Não");
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
		JMenuItem subMenuAtualizarBanda = new JMenuItem("Atualizar");
		menuBanda.add(subMenuAtualizarBanda);
		
		//----------------INTEGRANTES---------------
		//Janela Integrantes		
		JFrame janelaIntegrantes = JanelaIntegrantes.criarJanelaIntegrantes();	
		
		//Menu Integrantes
		JMenu menuIntegrantes = new JMenu("Integrantes");
		menuBar.add(menuIntegrantes);	
		
		//SubMenus Integrantes
		JMenuItem subMenuAtualizarIntegrantes = new JMenuItem("Atualizar");
		menuIntegrantes.add(subMenuAtualizarIntegrantes);
		
		//-------------------SHOW---------------------------
		//Janela Show		 	
		JFrame janelaShow = JanelaShow.criarJanelaShow();
		
		//Menu Show		
		JMenu menuShow = new JMenu("Show");
		menuBar.add(menuShow);
		
		//SubMenus Show				
		JMenuItem subMenuAtualizarShow = new JMenuItem("Atualizar");		
		menuShow.add(subMenuAtualizarShow);
		JMenuItem subMenuListarShow = new JMenuItem("Visualizar");		
		menuShow.add(subMenuListarShow);
		
		//Ações dos SubMenus de atualização
		subMenuAtualizarBanda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				janelaBanda.setVisible(true);
			}
		});
		
		subMenuAtualizarIntegrantes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				janelaIntegrantes.setVisible(true);
			}
		});
		
		subMenuAtualizarShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				janelaShow.setVisible(true);
			}
		});
		
		subMenuListarShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					janelaListaShows = new JanelaListaShows(janelaPrincipal);
					janelaListaShows.setEnabled(true);
				
				} catch (Exception erro) {
					 JOptionPane.showMessageDialog(janelaBanda, "Não foi possível se conectar ao banco de dados","Error",JOptionPane.ERROR_MESSAGE);
                }
			} 			
		});
		
		//JANELA PRINCIPAL
		janelaPrincipal.setVisible(true);
	}

	public static void main(String[] args) {
		apresentarMenu();
	}
}