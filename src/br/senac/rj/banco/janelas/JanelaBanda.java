package br.senac.rj.banco.janelas;

import javax.swing.*;


import br.senac.rj.banco.modelo.Banda;
import br.senac.rj.banco.service.Utilitarios;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class JanelaBanda {
	
    public static JFrame criarJanelaBanda() {    	    
    	
        JFrame janelaBanda = new JFrame("Cadastro de Bandas");                        		  		    
        janelaBanda.setResizable(false);
        janelaBanda.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        janelaBanda.setSize(440, 325);
        
        janelaBanda.getContentPane().setBackground(Color.decode("#282829"));
        
      //IMAGEM DE BACKGROUND
        ImageIcon icon = new ImageIcon("./src/imagem/guitarrapreta.jpg");
        JLabel background = new JLabel(icon);
        background.setSize(440, 300);
        background.setBounds(0, 0, 440, 300);
        
        Container caixa = janelaBanda.getContentPane();
        caixa.setLayout(null);
        
        // Nome banda
        JLabel labelNomeBanda = new JLabel("Nome da Banda:");
        labelNomeBanda.setBounds(50, 40, 120, 20);
        JTextField campoNomeBanda = new JTextField();
        campoNomeBanda.setBounds(180, 40, 150, 20);
        
        // País
        JLabel labelPais = new JLabel("País:");
        labelPais.setBounds(50, 80, 120, 20);
        JTextField campoPais = new JTextField();
        campoPais.setBounds(180, 80, 150, 20);

        // Gênero da Banda (Dropdown)
        JLabel labelGenero = new JLabel("Gênero:");
        labelGenero.setBounds(50, 120, 120, 20);
        String[] generos = Banda.generoList;
        JComboBox<String> dropdownGenero = new JComboBox<>(generos);
        dropdownGenero.setBounds(180, 120, 150, 20);

        caixa.add(labelNomeBanda);
        caixa.add(campoNomeBanda);
        caixa.add(labelPais);
        caixa.add(campoPais);        
        caixa.add(labelGenero);
        caixa.add(dropdownGenero);
        
        // CORES label
        labelNomeBanda.setForeground(new Color(255, 255, 255));
        labelPais.setForeground(new Color(255, 255, 255));
        
        labelPais.setForeground(new Color(255, 255, 255));
        labelGenero.setForeground(new Color(255, 255, 255));
        //COR BACKGROUND
       
        JButton botaoPesquisar = new JButton("🔍");
        botaoPesquisar.setBounds(340, 40, 60, 20);
        arredondarBotao(botaoPesquisar);
        janelaBanda.add(botaoPesquisar);
        
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
        
        //BACKGROUND
        caixa.add(background);
              
        //BOTOES
        botaoEditar.setEnabled(false);
        botaoDeletar.setEnabled(false);
        /*INSTANCIAS*/
        Banda banda = new Banda();
        
        botaoPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nome = campoNomeBanda.getText();				
					if (banda.getBanda(nome, false)) {
						campoNomeBanda.setText(banda.getNome());				
						campoPais.setText(banda.getPais());							
						dropdownGenero.setSelectedItem(banda.getGenero());
						botaoDeletar.setEnabled(true);
						botaoEditar.setEnabled(true);
					} else {//se houver banda com mesmo nome, desativa os botoes deletar e editar
						botaoCadastrar.setEnabled(true);
						botaoDeletar.setEnabled(false);
						botaoEditar.setEnabled(false);
					}											
			}
		});
        
        
        botaoCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if ( 
					(campoNomeBanda.getText() == null  || campoNomeBanda.getText().trim().isEmpty()) ||
					(campoPais.getText() == null  || campoPais.getText().trim().isEmpty())||					
					(dropdownGenero.getSelectedItem().toString().trim().isEmpty())							
				) { 
					JOptionPane.showMessageDialog(janelaBanda, "Preencha todos os campos antes de cadastrar!",
	                        "Quem avisa amigo é", JOptionPane.WARNING_MESSAGE); 
				} else {
				//Executa ação  se nenhum dos campos estiverem vazios
					if(Utilitarios.verificacaoDialogBox(janelaBanda, "Deseja confirmar a ação?", null, "Ação cancelada")) {
						banda.setNome(campoNomeBanda.getText());
						banda.setPais(campoPais.getText());
						Boolean result = banda.cadastrarBanda(dropdownGenero.getSelectedItem().toString());
						if (result) {
							JOptionPane.showMessageDialog(janelaBanda, "Banda Cadastrada com Sucesso!");
							botaoEditar.setEnabled(true);
						} else {
							JOptionPane.showMessageDialog(janelaBanda, "Não foi possível realizar cadastro,\n nome da banda já existente");
						}			
					}	
				}													
			}
		});
        
        
        botaoEditar.addActionListener(new ActionListener() {        	
			public void actionPerformed(ActionEvent e) {	
				try {					
					if ( 
						(campoNomeBanda.getText() == null  || campoNomeBanda.getText().trim().isEmpty()) ||
						(campoPais.getText() == null  || campoPais.getText().trim().isEmpty()) ||					
						(dropdownGenero.getSelectedItem().toString().trim().isEmpty())							
					){						
						 JOptionPane.showMessageDialog(janelaBanda, "Preencha os campos corretamente!","Quem avisa amigo é",JOptionPane.WARNING_MESSAGE);
					} else {											//Aqui passamos o valor null para não mostrar a mensagem
						if(Utilitarios.verificacaoDialogBox(janelaBanda, "Deseja confirmar a ação?", null, "Ação cancelada")) {
							//BLOCO COM OS CAMPOS PREENCHIDOS
							String nome = campoNomeBanda.getText();
							String pais = campoPais.getText();
							String genero = dropdownGenero.getSelectedItem().toString();
							banda.getBanda(nome, false); //Pega as informações da banda quando clica em atualizar.
							if(banda.atualizarBanda(banda.getId_banda(), nome, genero, pais)) {
								JOptionPane.showMessageDialog(janelaBanda, "Banda atualizada com sucesso!");
							} else {
								JOptionPane.showMessageDialog(janelaBanda, "Banda não cadastrada");
							}
						} else {
							System.out.println("ação cancelada");
						}
					}																				
				} catch (Exception erro) {
					 JOptionPane.showMessageDialog(janelaBanda, "Preencha os campos corretamente!","Error",JOptionPane.ERROR_MESSAGE);
                } 									
			}			
		});
        
        
        botaoDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if ( 
					(campoNomeBanda.getText() == null  || campoNomeBanda.getText().trim().isEmpty()) ||
					(campoPais.getText() == null  || campoPais.getText().trim().isEmpty())||					
					(dropdownGenero.getSelectedItem().toString().trim().isEmpty())							
				   ) { 
						JOptionPane.showMessageDialog(janelaBanda, "Preencha todos os campos antes de cadastrar!",
		                "Quem avisa amigo é", JOptionPane.WARNING_MESSAGE);
				} else {
					if(Utilitarios.verificacaoDialogBox(janelaBanda, "Deseja realizar a ação?", null, "Ação cancelada")) {
						String nome = campoNomeBanda.getText();
						banda.getBanda(nome, true);					
						Boolean result = banda.deletarBanda();
						if (result) {
							JOptionPane.showMessageDialog(janelaBanda, "Banda Deletada com sucesso!");
							campoNomeBanda.setText("");
			                campoPais.setText("");
			                dropdownGenero.setSelectedItem("");
						} else {
							JOptionPane.showMessageDialog(janelaBanda, "Banda não existe!", "Error",JOptionPane.ERROR_MESSAGE);
						  }
					}
						}											                                                                                           		
				}
		});
        
        botaoLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {			
				campoNomeBanda.setText("");				
				campoPais.setText("");				
				dropdownGenero.setSelectedItem("");
				banda.setNome(null);
				banda.setPais(null);
				botaoCadastrar.setEnabled(true);
				botaoDeletar.setEnabled(false);
				botaoEditar.setEnabled(false);
			}
		});
        
        return janelaBanda;                
    }
  
    private static void arredondarBotao(JButton botao) {
        int tamanhoBorda = 5;

        botao.setBorder(BorderFactory.createEmptyBorder(tamanhoBorda, tamanhoBorda, tamanhoBorda, tamanhoBorda));
        botao.setBackground(Color.BLACK);
        botao.setForeground(Color.WHITE); // Cor do texto
        botao.setContentAreaFilled(true); //Permite aparecer a cor       
    }

}