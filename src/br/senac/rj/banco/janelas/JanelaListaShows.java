package br.senac.rj.banco.janelas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class JanelaListaShows {
	private static DefaultTableModel model;
	private static JTable table;
	 public static JFrame criarJanelaListaShows() {
		 JFrame janelaListaShow = new JFrame("Shows");
		 janelaListaShow.setResizable(false);
		 janelaListaShow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		 janelaListaShow.setSize(400, 330);
		 janelaListaShow.getContentPane().setBackground(Color.decode("#282829"));    
		 janelaListaShow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		 janelaListaShow.setSize(600, 400);
		 janelaListaShow.setLocation(1000, 250);
		 
			JPanel painelSuperior = new JPanel();
			painelSuperior.setPreferredSize(new Dimension(1, 20));

			model = new DefaultTableModel();
			table = new JTable(model);
			JScrollPane scrollPane = new JScrollPane(table);

			JButton btnAtualizar = new JButton("Atualizar");
			btnAtualizar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					;
				}
			});

			JPanel painelBotoes = new JPanel();
			painelBotoes.add(btnAtualizar);

			janelaListaShow.setLayout(new BorderLayout());
			janelaListaShow.add(painelSuperior, BorderLayout.NORTH);
			janelaListaShow.add(scrollPane, BorderLayout.CENTER);
			janelaListaShow.add(painelBotoes, BorderLayout.SOUTH);
	      return janelaListaShow;
	 }
	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
