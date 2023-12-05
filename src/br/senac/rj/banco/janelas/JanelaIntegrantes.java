package br.senac.rj.banco.janelas;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
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
               
        
        //Organizar os campos na tela integrantes
        Container caixa = janelaIntegrantes.getContentPane();
        caixa.setLayout(null);
        janelaIntegrantes.setLayout(null);
        
        // Define os labels dos campos
        JLabel labelFkBanda = new JLabel("ID da Banda:");
        JLabel labelVocalista = new JLabel("Vocalista 🎙️:");
        JLabel labelBaterista = new JLabel("Baterista 🎵:");
        JLabel labelGuitarrista = new JLabel("Guitarrista 🎸:");
        JLabel labelGuitarrista2 = new JLabel("2° Guitarrista 🎻:");     
        
        //Fonte Branco
        labelFkBanda.setForeground(Color.WHITE);
        labelVocalista.setForeground(Color.WHITE);
        labelBaterista.setForeground(Color.WHITE);
        labelGuitarrista.setForeground(Color.WHITE);
        labelGuitarrista2.setForeground(Color.WHITE);
        
        labelFkBanda.setBounds(50, 40, 100, 20);
        labelVocalista.setBounds(50, 80, 100, 20);
        labelBaterista.setBounds(50, 120, 100, 20);
        labelGuitarrista.setBounds(50, 160, 100, 20);
        labelGuitarrista2.setBounds(50, 200, 150, 20);

        //input box
        JTextField jTextFkBanda = new JTextField();
        JTextField jTextVocalista = new JTextField();
        JTextField jTextBaterista = new JTextField();
        JTextField jTextGuitarrista = new JTextField();
        JTextField jTextGuitarrista2 = new JTextField();

        // Posiciona os input box
        jTextFkBanda.setBounds(190, 40, 50, 20);
        jTextVocalista.setBounds(190, 80, 150, 20);
        jTextBaterista.setBounds(190, 120, 150, 20);
        jTextGuitarrista.setBounds(190, 160, 150, 20);
        jTextGuitarrista2.setBounds(190, 200, 150, 20);

        // Adiciona os rótulos e os input box na janela
        janelaIntegrantes.add(labelFkBanda);
        janelaIntegrantes.add(labelVocalista);
        janelaIntegrantes.add(labelBaterista);
        janelaIntegrantes.add(labelGuitarrista);
        janelaIntegrantes.add(labelGuitarrista2);
        janelaIntegrantes.add(jTextFkBanda);
        janelaIntegrantes.add(jTextVocalista);
        janelaIntegrantes.add(jTextBaterista);
        janelaIntegrantes.add(jTextGuitarrista);
        janelaIntegrantes.add(jTextGuitarrista2);

        // Define botões e a localização deles na janela
        JButton botaoCadastrar = new JButton("Cadastrar");
        botaoCadastrar.setBounds(50, 240, 100, 30);
        janelaIntegrantes.add(botaoCadastrar);

        JButton botaoEditar = new JButton("Atualizar");
        botaoEditar.setBounds(160, 240, 100, 30);
        janelaIntegrantes.add(botaoEditar);
       
        JButton botaoLimpar = new JButton("Limpar");
        botaoLimpar.setBounds(250, 40, 100, 20);
        janelaIntegrantes.add(botaoLimpar);

        JButton botaoDeletar = new JButton("Deletar");
        // Corrigi as coordenadas do botão "Deletar" para ficar ao lado do botão "Editar"
        botaoDeletar.setBounds(270, 240, 100, 30);
        janelaIntegrantes.add(botaoDeletar);
        
        //BOTOES
        botaoEditar.setEnabled(false);
        
        //INSTANCIA
        Integrantes intg = new Integrantes();
        //Botao após clicar no cadastrar
        //OK - FALTA O BAIXISTA
        botaoCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int fkBanda = Integer.parseInt(jTextFkBanda.getText());
                    String vocalista = jTextVocalista.getText().trim();
                    String baterista = jTextBaterista.getText().trim();
                    //String baixista = jTextBaixista.getText().trim();
                    String guitarrista = jTextGuitarrista.getText().trim();
                    String guitarrista2 = jTextGuitarrista2.getText().trim();
                    
                    // Verifica se todos os campos estão preenchidos
                    if (fkBanda == 0 || vocalista.isEmpty() || baterista.isEmpty() || guitarrista.isEmpty() || guitarrista2.isEmpty()) {
                        JOptionPane.showMessageDialog(janelaIntegrantes, "Preencha todos os campos antes de cadastrar!",
                        "Aviso", JOptionPane.WARNING_MESSAGE                             
                        ) ;
                    } else {
                    	intg.setFk_banda(fkBanda);
                        intg.setVocalista(vocalista);
                        intg.setBaterista(baterista);
                        intg.setBaixista(guitarrista2);
                        intg.setGuitarrista1(guitarrista);
                        intg.setGuitarrista2(guitarrista2);        
                    	if(Utilitarios.verificacaoDialogBox(janelaIntegrantes, "Confirmar cadastro:", null,"Error ao realizar cadastro")) {
                    		boolean result = intg.cadastrarIntegrante();
                    		//botaoEditar.setEnabled(true); // Habilita o botão de editar após o cadastro                    		
                    		if(!result) { 
                    			JOptionPane.showMessageDialog(janelaIntegrantes, "Erro ao cadastrar integrantes",
                                        "Erro", JOptionPane.ERROR_MESSAGE);
                    		} else {
                    			JOptionPane.showMessageDialog(janelaIntegrantes, "Cadastro realizado com sucesso!");
                    		}
                    	}                                        	                                                                                                        
                    }
                } catch (NumberFormatException erro) {
                    JOptionPane.showMessageDialog(janelaIntegrantes, "Preencha o campo 'ID da Banda' corretamente!",
                            "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        //Desativa e ativa o botão de editar
        jTextFkBanda.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		boolean result  = false; 
        		int id = Integer.parseInt(jTextFkBanda.getText().trim());        			
    			result = intg.buscarIntegrantes(id);
    			System.out.println(result);
        		if ((!jTextFkBanda.getText().trim().isBlank()) && (result)) {        			
        			jTextVocalista.setText(intg.getVocalista());        			
                	jTextBaterista.setText(intg.getBaterista());
                	jTextGuitarrista.setText(intg.getGuitarrista1());
                	jTextGuitarrista2.setText(intg.getGuitarrista2());
        			botaoEditar.setEnabled(true);
        		} else {
        			botaoEditar.setEnabled(false);
        		}        		        		        		       		        			
        }});
        
        //EDITAR
        botaoEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                	int fkBanda = Integer.parseInt(jTextFkBanda.getText());
                    String vocalista = jTextVocalista.getText().trim();
                    String baterista = jTextBaterista.getText().trim();
                    //String baixista = jTextBaixista.getText().trim();
                    String guitarrista = jTextGuitarrista.getText().trim();
                    String guitarrista2 = jTextGuitarrista2.getText().trim();
                    		
                	if (fkBanda == 0 || vocalista.isEmpty() || baterista.isEmpty() || guitarrista.isEmpty() || guitarrista2.isEmpty()) 
                	{
	                    JOptionPane.showMessageDialog(janelaIntegrantes, "Preencha todos os campos antes de cadastrar!",
	                    "Aviso", JOptionPane.WARNING_MESSAGE);	                    
                	} else { //O CAMPO NULL É PARA INSERIR O BAIXISTA DEPOIS
                        	intg.atualizarIntegrantes(fkBanda, vocalista, baterista, null, guitarrista, guitarrista2);
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
                	int idBanda = Integer.parseInt(jTextFkBanda.getText());                	
                	intg.deletarIntegrantes(idBanda);
                    JOptionPane.showMessageDialog(janelaIntegrantes, "Integrantes deletados com sucesso! 🎸❤️");
                    jTextFkBanda.setText("");
                	jTextVocalista.setText("");
                	jTextBaterista.setText("");
                	jTextGuitarrista.setText("");
                	jTextGuitarrista2.setText("");
                    
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
            	jTextGuitarrista2.setText("");
            	botaoEditar.setEnabled(false);
            }
                      
        });
        
        return janelaIntegrantes;
    }

    public static void main(String[] args) {
        JanelaIntegrantes janelaIntegrantes = new JanelaIntegrantes();
        JanelaIntegrantes.criarJanelaIntegrantes().setVisible(true);
    }
}