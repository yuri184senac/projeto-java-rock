package br.senac.rj.banco.janelas;

import br.senac.rj.banco.modelo.Integrantes;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

public class JanelaIntegrantes {

    public static JFrame criarJanelaIntegrantes() {
    	
		
		ImageIcon imagemDeFundo = new ImageIcon("src/imagem/asas.png");
		JLabel labelComBackground = new JLabel(imagemDeFundo);
		labelComBackground.setPreferredSize(new Dimension(400, 360));
		

        JFrame janelaIntegrantes = new JFrame("Cadastro de Integrantes");
        janelaIntegrantes.setResizable(false);
        janelaIntegrantes.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        janelaIntegrantes.setSize(400, 360);
        janelaIntegrantes.add(labelComBackground);
        
        // Define o layout da janela
        Container caixa = janelaIntegrantes.getContentPane();
        caixa.setLayout(null);
        
        // Define os labels dos campos
        JLabel labelFkBanda = new JLabel("ID da Banda:");
        JLabel labelVocalista = new JLabel("Vocalista 🎙️:");
        JLabel labelBaterista = new JLabel("Baterista 🎵:");
        JLabel labelGuitarrista = new JLabel("Guitarrista 🎸:");
        JLabel labelGuitarrista2 = new JLabel("Segundo Guitarrista 🎻:");      
        JLabel labelBaixista = new JLabel("Baixista 🎻:");  
        
        // Posiciona os labels na janela
        labelFkBanda.setBounds(50, 40, 100, 20);
        labelVocalista.setBounds(50, 80, 100, 20);
        labelBaterista.setBounds(50, 120, 100, 20);
        labelGuitarrista.setBounds(50, 160, 100, 20);
        labelGuitarrista2.setBounds(50, 200, 150, 20);
        labelBaixista.setBounds(50, 240, 100, 20);
        
        // Define os input box
        JTextField jTextFkBanda = new JTextField();
        JTextField jTextVocalista = new JTextField();
        JTextField jTextBaterista = new JTextField();
        JTextField jTextGuitarrista = new JTextField();
        JTextField jTextGuitarrista2 = new JTextField();
        JTextField jTextBaixista = new JTextField();

        // Posiciona os input box
        jTextFkBanda.setBounds(190, 40, 50, 20);
        jTextVocalista.setBounds(190, 80, 150, 20);
        jTextBaterista.setBounds(190, 120, 150, 20);
        jTextGuitarrista.setBounds(190, 160, 150, 20);
        jTextGuitarrista2.setBounds(190, 200, 150, 20);
        jTextBaixista.setBounds(190, 240, 150, 20);
        // Adiciona os rótulos e os input box na janela
        janelaIntegrantes.add(labelFkBanda);
        janelaIntegrantes.add(labelVocalista);
        janelaIntegrantes.add(labelBaterista);
        janelaIntegrantes.add(labelGuitarrista);
        janelaIntegrantes.add(labelGuitarrista2);
        janelaIntegrantes.add(labelBaixista);
        janelaIntegrantes.add(jTextFkBanda);
        janelaIntegrantes.add(jTextVocalista);
        janelaIntegrantes.add(jTextBaterista);
        janelaIntegrantes.add(jTextGuitarrista);
        janelaIntegrantes.add(jTextGuitarrista2);
        janelaIntegrantes.add(jTextBaixista);
        // Define botões e a localização deles na janela
        JButton botaoCadastrar = new JButton("Cadastrar");
        botaoCadastrar.setBounds(50, 280, 100, 30);
        janelaIntegrantes.add(botaoCadastrar);
        
        JButton botaoEditar = new JButton("Editar");
        botaoEditar.setBounds(160, 280, 100, 30);
        janelaIntegrantes.add(botaoEditar);
               
        JButton botaoLimpar = new JButton("Limpar");
        botaoLimpar.setBounds(270, 280, 100, 30);
        janelaIntegrantes.add(botaoLimpar);
        
        // Define objeto integrante para manipulação no banco de dados
        Integrantes integrante = new Integrantes();

        // Define ações dos botões
        
        //Botao após clicar no cadastrar
        botaoCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int fkBanda = Integer.parseInt(jTextFkBanda.getText());
                    String vocalista = jTextVocalista.getText().trim();
                    String baterista = jTextBaterista.getText().trim();
                    String guitarrista = jTextGuitarrista.getText().trim();
                    String guitarrista2 = jTextGuitarrista2.getText().trim();
                    String baixista = jTextBaixista.getText().trim();

                    // Aqui você deve chamar o método de cadastro do integrante com os valores obtidos
                    // integrante.cadastrarIntegrante(...);

                    JOptionPane.showMessageDialog(janelaIntegrantes, "Integrante cadastrado com sucesso! 🎸❤️");
                    limparCampos();
                } catch (Exception erro) {
                    JOptionPane.showMessageDialog(janelaIntegrantes, "Preencha os campos corretamente!");
                }
            }
        });
      //Botao após clicar no editar
        botaoEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int fkBanda = Integer.parseInt(jTextFkBanda.getText());
                    String vocalista = jTextVocalista.getText().trim();
                    String baterista = jTextBaterista.getText().trim();
                    String guitarrista = jTextGuitarrista.getText().trim();
                    String guitarrista2 = jTextGuitarrista2.getText().trim();
                    String baixista = jTextBaixista.getText().trim();
  

                    // Aqui você deve chamar o método de cadastro do integrante com os valores obtidos
                    // integrante.cadastrarIntegrante(...);

                    JOptionPane.showMessageDialog(janelaIntegrantes, "Integrante cadastrado com sucesso! 🎸❤️");
                    limparCampos();
                } catch (Exception erro) {
                    JOptionPane.showMessageDialog(janelaIntegrantes, "Preencha os campos corretamente!");
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
            	jTextBaixista.setText("");
                
            }
                      
        });
		
        return janelaIntegrantes;
    }

    private static void limparCampos() {
        // Método para limpar os campos da janela
        // Adicione aqui a lógica para limpar os campos conforme necessário
    }

    public static void main(String[] args) {
        JanelaIntegrantes janelaIntegrantes = new JanelaIntegrantes();
        JanelaIntegrantes.criarJanelaIntegrantes().setVisible(true);
    }
}

