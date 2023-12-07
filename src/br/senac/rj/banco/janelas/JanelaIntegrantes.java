package br.senac.rj.banco.janelas;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import br.senac.rj.banco.modelo.Integrantes;
import br.senac.rj.banco.service.Utilitarios;

public class JanelaIntegrantes {

    public static JFrame criarJanelaIntegrantes() {
    	
    
    	JFrame janelaIntegrantes = new JFrame("Cadastro de Integrantes");
        janelaIntegrantes.setResizable(false);
        janelaIntegrantes.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        janelaIntegrantes.setSize(400, 330);
        janelaIntegrantes.getContentPane().setBackground(Color.decode("#282829"));
               
      //IMAGEM DE BACKGROUND
        ImageIcon icon = new ImageIcon("./src/imagem/integrantes-bk.gif");
        JLabel background = new JLabel(icon);
        background.setSize(440, 300);
        background.setBounds(0,  0, 440, 300);
        //FIM-IMAGEM-DE-BACKGROUND
        
        
        //Organizar os campos na tela integrantes
        Container caixa = janelaIntegrantes.getContentPane();
        caixa.setLayout(null);
        janelaIntegrantes.setLayout(null);
        
        // Define os labels dos campos
        JLabel labelFkBanda = new JLabel("Buscar banda:");
        JLabel labelVocalista = new JLabel("Vocalista 🎙️:");
        JLabel labelBaterista = new JLabel("Baterista 🎵:");
        JLabel labelGuitarrista = new JLabel("Guitarrista 🎸:");
        JLabel labelBaixista = new JLabel("Baixista 🎻:");     
        
        //Fonte Branco
        labelFkBanda.setForeground(Color.WHITE);
        labelVocalista.setForeground(Color.WHITE);
        labelBaterista.setForeground(Color.WHITE);
        labelGuitarrista.setForeground(Color.WHITE);
        labelBaixista.setForeground(Color.WHITE);
        
        //Ajusta o tamanho e posição
        labelFkBanda.setBounds(50, 40, 100, 20);
        labelVocalista.setBounds(50, 80, 100, 20);
        labelBaterista.setBounds(50, 120, 100, 20);
        labelGuitarrista.setBounds(50, 160, 100, 20);
        labelBaixista.setBounds(50, 200, 150, 20);

        //input box
        JTextField jTextFkBanda = new JTextField();
        JTextField jTextVocalista = new JTextField();
        JTextField jTextBaterista = new JTextField();
        JTextField jTextGuitarrista = new JTextField();
        JTextField jTextBaixista = new JTextField();

        // Posiciona os input box
        jTextFkBanda.setBounds(165, 40, 150, 20);
        jTextVocalista.setBounds(165, 80, 150, 20);
        jTextBaterista.setBounds(165, 120, 150, 20);
        jTextGuitarrista.setBounds(165, 160, 150, 20);
        jTextBaixista.setBounds(165, 200, 150, 20);

        // Adiciona os rótulos e os input box na janela
        janelaIntegrantes.add(labelFkBanda);
        janelaIntegrantes.add(labelVocalista);
        janelaIntegrantes.add(labelBaterista);
        janelaIntegrantes.add(labelGuitarrista);
        janelaIntegrantes.add(labelBaixista);
        janelaIntegrantes.add(jTextFkBanda);
        janelaIntegrantes.add(jTextVocalista);
        janelaIntegrantes.add(jTextBaterista);
        janelaIntegrantes.add(jTextGuitarrista);
        janelaIntegrantes.add(jTextBaixista);

        // Define botões e a localização deles na janela
        JButton botaoPesquisar = new JButton("🔍");
        botaoPesquisar.setBounds(320, 40, 60, 20);
        arredondarBotao(botaoPesquisar);
        janelaIntegrantes.add(botaoPesquisar);
        
        JButton botaoCadastrar = new JButton("Cadastrar");
        botaoCadastrar.setBounds(50, 240, 100, 30);        
        janelaIntegrantes.add(botaoCadastrar);
        arredondarBotao(botaoCadastrar);
        
        JButton botaoEditar = new JButton("Atualizar");
        botaoEditar.setBounds(160, 240, 100, 30);
        janelaIntegrantes.add(botaoEditar);
        arredondarBotao(botaoEditar);
       
        JButton botaoLimpar = new JButton("Limpar");
        botaoLimpar.setBounds(300, 10, 80, 24);
        janelaIntegrantes.add(botaoLimpar);
        arredondarBotao(botaoLimpar);

        JButton botaoDeletar = new JButton("Deletar");
        // Corrigi as coordenadas do botão "Deletar" para ficar ao lado do botão "Editar"
        botaoDeletar.setBounds(270, 240, 100, 30);
        janelaIntegrantes.add(botaoDeletar);
        arredondarBotao(botaoDeletar);
        
        //BOTOES
        botaoEditar.setEnabled(false);
        botaoDeletar.setEnabled(false);
        //BACKGROUND
        caixa.add(background);
        
       
        Integrantes intg = new Integrantes();
        
        //Desativa e ativa o botão de editar
        botaoPesquisar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		boolean result  = false; 
        		String nome_banda = jTextFkBanda.getText();          		
    			result = intg.buscarIntegrantes(nome_banda); // TRABALHANDO AQUI    			
        		if ((!jTextFkBanda.getText().trim().isBlank()) && (result)) {   
        			jTextFkBanda.setText(intg.getNome_banda()); 
        			jTextVocalista.setText(intg.getVocalista());        			
                	jTextBaterista.setText(intg.getBaterista());
                	jTextGuitarrista.setText(intg.getGuitarrista1());
                	jTextBaixista.setText(intg.getBaixista());
                	botaoDeletar.setEnabled(true);
					botaoEditar.setEnabled(true);
        		} else {
        			jTextFkBanda.setText(intg.getNome_banda());        			
        			botaoCadastrar.setEnabled(true);
					botaoDeletar.setEnabled(false);
					botaoEditar.setEnabled(false);
        		}        		        		        		       		        			
        }});
        
        //Botao após clicar no cadastrar
        
        botaoCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String fkBanda = jTextFkBanda.getText();
                    String vocalista = jTextVocalista.getText().trim();
                    String baterista = jTextBaterista.getText().trim();                    
                    String guitarrista = jTextGuitarrista.getText().trim();
                    String baixista = jTextBaixista.getText().trim();
                    
                    // Verifica se todos os campos estão preenchidos
                    if (fkBanda.isEmpty() || vocalista.isEmpty() || baterista.isEmpty() || guitarrista.isEmpty() || baixista.isEmpty()) {
                        JOptionPane.showMessageDialog(janelaIntegrantes, "Preencha todos os campos antes de cadastrar!",
                        "Aviso", JOptionPane.WARNING_MESSAGE                             
                        ) ;
                    } else {
                    	//intg.setFk_banda(fkBanda);
                        intg.setVocalista(vocalista);
                        intg.setBaterista(baterista);                       
                        intg.setGuitarrista1(guitarrista);
                        intg.setBaixista(baixista);        
                    	if(Utilitarios.verificacaoDialogBox(janelaIntegrantes, "Confirmar cadastro:", null,"Error ao realizar cadastro")) {
                    		boolean result = intg.cadastrarIntegrante();
                    		//botaoEditar.setEnabled(true); // Habilita o botão de editar após o cadastro                    		
                    		if(!result) { 
                    			JOptionPane.showMessageDialog(janelaIntegrantes, "Erro ao cadastrar integrantes",
                                        "Erro", JOptionPane.ERROR_MESSAGE);
                    		} else {
                    			JOptionPane.showMessageDialog(janelaIntegrantes, "Cadastro realizado com sucesso!");
                    			botaoEditar.setEnabled(true);
                    		}
                    	}                                        	                                                                                                        
                    }
                } catch (NumberFormatException erro) {
                    JOptionPane.showMessageDialog(janelaIntegrantes, "Preencha o campo 'ID da Banda' corretamente!",
                            "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
               
        
        //EDITAR
        botaoEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                	String nBanda = jTextFkBanda.getText();
                    String vocalista = jTextVocalista.getText().trim();
                    String baterista = jTextBaterista.getText().trim();                   
                    String guitarrista = jTextGuitarrista.getText().trim();
                    String baixista = jTextBaixista.getText().trim();
                    		
                	if (nBanda.isEmpty() || vocalista.isEmpty() || baterista.isEmpty() || guitarrista.isEmpty() || baixista.isEmpty()) 
                	{
	                    JOptionPane.showMessageDialog(janelaIntegrantes, "Preencha todos os campos antes de cadastrar!",
	                    "Aviso", JOptionPane.WARNING_MESSAGE);	                    
                	} else { //O CAMPO NULL É PARA INSERIR O BAIXISTA DEPOIS
                        	intg.atualizarIntegrantes(nBanda, vocalista, baterista, guitarrista, baixista);
		                    JOptionPane.showMessageDialog(janelaIntegrantes, "Informações do integrante editadas com sucesso! 🎸❤️");
		                    botaoEditar.setEnabled(false);
                      }		                	                       		                	                	               
                } catch (NumberFormatException erro) {
                    JOptionPane.showMessageDialog(janelaIntegrantes, "Preencha o campo 'ID da Banda' corretamente!",
                            "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        //OK
        botaoDeletar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {        
                	String nBanda = jTextFkBanda.getText();                	
                	intg.deletarIntegrantes(nBanda);
                    JOptionPane.showMessageDialog(janelaIntegrantes, "Integrantes deletados com sucesso! 🎸❤️");
                    jTextFkBanda.setText("");
                	jTextVocalista.setText("");
                	jTextBaterista.setText("");
                	jTextGuitarrista.setText("");
                	jTextBaixista.setText("");
                    
                } catch (NumberFormatException erro) {
                    JOptionPane.showMessageDialog(janelaIntegrantes, "Preencha o campo 'ID da Banda' corretamente!",
                            "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        

      //Botao após clicar no limpar
        botaoLimpar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	jTextFkBanda.setText("");
            	jTextVocalista.setText("");
            	jTextBaterista.setText("");
            	jTextGuitarrista.setText("");
            	jTextBaixista.setText("");
            	botaoCadastrar.setEnabled(true);
				botaoDeletar.setEnabled(false);
				botaoEditar.setEnabled(false);
            }
                      
        });
        
        return janelaIntegrantes;
    }
    private static void arredondarBotao(JButton botao) {
        int tamanhoBorda = 5;

        botao.setBorder(BorderFactory.createEmptyBorder(tamanhoBorda, tamanhoBorda, tamanhoBorda, tamanhoBorda));
        botao.setBackground(Color.decode("#c27500"));
        botao.setForeground(Color.WHITE); // Cor do texto
        botao.setContentAreaFilled(true); //Permite aparecer a cor

        botao.addActionListener(new ActionListener() {
            @Override // ActionListener É PAI do actionPerformed
            public void actionPerformed(ActionEvent e) {
                // Lógica do botão
            }
        });
    }

    public static void main(String[] args) {
        JanelaIntegrantes janelaIntegrantes = new JanelaIntegrantes();
        JanelaIntegrantes.criarJanelaIntegrantes().setVisible(true);
    }
}