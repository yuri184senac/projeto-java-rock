package br.senac.rj.banco.janelas;

import javax.swing.*;
import javax.swing.border.Border;

import br.senac.rj.banco.modelo.Banda;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class JanelaBanda {

    public static JFrame criarJanelaBanda() {
        JFrame janelaBanda = new JFrame("Cadastro de Bandas");
        janelaBanda.setResizable(false);
        janelaBanda.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        janelaBanda.setSize(440, 325);

        Container caixa = janelaBanda.getContentPane();
        caixa.setLayout(null);

        JLabel labelNomeBanda = new JLabel("Nome da Banda:");
        labelNomeBanda.setBounds(50, 40, 120, 20);
        JTextField campoNomeBanda = new JTextField();
        campoNomeBanda.setBounds(180, 40, 150, 20);

        // País
        JLabel labelPais = new JLabel("País:");
        labelPais.setBounds(50, 80, 120, 20);
        JTextField campoPais = new JTextField();
        campoPais.setBounds(180, 80, 150, 20);

        // Integrantes
        JLabel labelIntegrantes = new JLabel("Integrantes:");
        labelIntegrantes.setBounds(50, 120, 120, 20);
        JTextField campoIntegrantes = new JTextField();
        campoIntegrantes.setBounds(180, 120, 150, 20);

        // Gênero da Banda (Dropdown)
        JLabel labelGenero = new JLabel("Gênero:");
        labelGenero.setBounds(50, 160, 120, 20);
        String[] generos = Banda.generoList;
        JComboBox<String> dropdownGenero = new JComboBox<>(generos);
        dropdownGenero.setBounds(180, 160, 150, 20);

        caixa.add(labelNomeBanda);
        caixa.add(campoNomeBanda);
        caixa.add(labelPais);
        caixa.add(campoPais);
        caixa.add(labelIntegrantes);
        caixa.add(campoIntegrantes);
        caixa.add(labelGenero);
        caixa.add(dropdownGenero);
        
        JButton botaoCadastrar = new JButton("Cadastrar");
        botaoCadastrar.setBounds(50, 240, 100, 40);
        arredondarBotao(botaoCadastrar);
        janelaBanda.add(botaoCadastrar);

        JButton botaoEditar = new JButton("Editar");
        botaoEditar.setBounds(160, 240, 100, 40);
        arredondarBotao(botaoEditar);
        janelaBanda.add(botaoEditar);
        
        JButton botaoDeletar = new JButton("Deletar");
        botaoDeletar.setBounds(270, 240, 100, 40);
        arredondarBotao(botaoDeletar);
        janelaBanda.add(botaoDeletar);

        JButton botaoLimpar = new JButton("Limpar");
        botaoLimpar.setBounds(270, 190, 60, 20);
        arredondarBotao(botaoLimpar);
        janelaBanda.add(botaoLimpar);
        
        /*INSTANCIAS*/
        Banda banda = new Banda();
      
        botaoCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {							
				banda.setNome(campoNomeBanda.getText());
				banda.setPais(campoPais.getText());
				Boolean result = banda.cadastrarBanda(dropdownGenero.getSelectedItem().toString());
				if (result) {
					System.out.println("");
				} else {
					System.out.println("Não foi possível cadastrar");
				}
								
			}
		});
        
        
        botaoEditar.addActionListener(new ActionListener() {        	
			public void actionPerformed(ActionEvent e) {	
				try {
					//BLOCO SE OS CAMPOS TIVEREM VAZIOS
					if ( 
						(campoNomeBanda.getText() == null  || campoNomeBanda.getText().trim().isEmpty()) ||
						(campoPais.getText() == null  || campoPais.getText().trim().isEmpty()) ||
						(campoIntegrantes.getText() == null  || campoIntegrantes.getText().trim().isEmpty()) ||
						(dropdownGenero.getSelectedItem().toString().trim().isEmpty())							
					){
						int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o id da banda para editar:"));
						banda.getBanda(id);
						campoNomeBanda.setText(banda.getNome());				
						campoPais.setText(banda.getPais());									
						dropdownGenero.setSelectedItem(banda.getGenero());
					} else {
						//BLOCO COM OS CAMPOS PREENCHIDOS
						banda.setNome(campoNomeBanda.getText());
						banda.setPais(campoPais.getText());
						banda.atualizarBanda(dropdownGenero.getSelectedItem().toString());
						JOptionPane.showMessageDialog(janelaBanda, "Atualização realizada com sucesso!");
					}
				} catch (Exception erro) {
					 JOptionPane.showMessageDialog(janelaBanda, "Preencha os campos corretamente!");
                } 									
			}			
		});
        
        botaoLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {			
				campoNomeBanda.setText("");				
				campoPais.setText("");				
				campoIntegrantes.setText("");
				dropdownGenero.setSelectedItem("");
			}
		});
        
        return janelaBanda;                
    }
  
    private static void arredondarBotao(JButton botao) {
        int tamanhoBorda = 5;
        Border bordaArredondada = BorderFactory.createEmptyBorder(tamanhoBorda, tamanhoBorda, tamanhoBorda, tamanhoBorda);
        botao.setBorder(bordaArredondada);
        botao.setFocusPainted(true);
    }

}