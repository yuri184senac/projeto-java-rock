package br.senac.rj.banco.janelas;

import javax.swing.*;

import br.senac.rj.banco.modelo.Show;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JanelaShow {

    public static JFrame criarJanelaShow() {
        JFrame janelaShow = new JFrame("Cadastro de Shows");
        janelaShow.setResizable(false);
        janelaShow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        janelaShow.setSize(400, 330);
        janelaShow.getContentPane().setBackground(Color.decode("#282829"));      
        
        //Organizar os campos na tela integrantes
        Container caixa = janelaShow.getContentPane();
        caixa.setLayout(null);
        janelaShow.setLayout(null);
        

        // Define os labels dos campos
        JLabel labelIdBanda = new JLabel("ID da Banda:");
        JLabel labelNome = new JLabel("Nome:");
        JLabel labelPais = new JLabel("País:");
        JLabel labelDataShow = new JLabel("Data do Show:");
        
        //Fonte Branco
        labelIdBanda.setForeground(Color.WHITE);
        labelNome.setForeground(Color.WHITE);
        labelPais.setForeground(Color.WHITE);
        labelDataShow.setForeground(Color.WHITE);
        
        // Posiciona os labels na janela
        labelIdBanda.setBounds(50, 40, 100, 20);
        labelNome.setBounds(50, 80, 100, 20);
        labelPais.setBounds(50, 120, 100, 20);
        labelDataShow.setBounds(50, 160, 100, 20);

        // Define os input box
        JTextField jTextIdBanda = new JTextField();
        JTextField jTextNome = new JTextField();
        JTextField jTextDataShow = new JTextField();

        // Posiciona os input box
        jTextIdBanda.setBounds(190, 40, 150, 20);
        jTextNome.setBounds(190, 80, 150, 20);
        jTextDataShow.setBounds(190, 160, 150, 20);

        // Adiciona os rótulos e os input box na janela
        janelaShow.add(labelIdBanda);
        janelaShow.add(labelNome);
        janelaShow.add(labelPais);
        janelaShow.add(labelDataShow);
        janelaShow.add(jTextIdBanda);
        janelaShow.add(jTextNome);
        janelaShow.add(jTextDataShow);

        // Define o JComboBox para o campo "País"
        String[] paises = Show.paisList;
        JComboBox<String> dropdownPais = new JComboBox<>(paises);
        dropdownPais.setBounds(190, 120, 150, 20);

        // Adiciona o JComboBox na janela
        janelaShow.add(dropdownPais);
        
        JButton botaoCadastrar = new JButton("Cadastrar");
        botaoCadastrar.setBounds(50, 240, 100, 30);
        janelaShow.add(botaoCadastrar);
        
        // Define botões e a localização deles na janela
        JButton botaoAtualizar = new JButton("Editar");
        botaoAtualizar.setBounds(160, 240, 100, 30);
        janelaShow.add(botaoAtualizar);
        
        JButton botaoExcluir = new JButton("Excluir");
        botaoExcluir.setBounds(270, 240, 100, 30);
        janelaShow.add(botaoExcluir);

        // Define ações dos botões
        botaoAtualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Implemente a lógica para atualizar o show
                JOptionPane.showMessageDialog(janelaShow, "Show atualizado com sucesso! 🎤🎶");
                limparCampos();
            }
        });

        botaoExcluir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Implemente a lógica para excluir o show
                JOptionPane.showMessageDialog(janelaShow, "Show excluído com sucesso! 🎤🎶");
                limparCampos();
            }
        });

        // ... (seu código posterior)

        return janelaShow;
    }

    private static void limparCampos() {
        // Método para limpar os campos da janela
        // Adicione aqui a lógica para limpar os campos conforme necessário
    }

    public static void main(String[] args) {
        JanelaShow janelaShow = new JanelaShow();
        JanelaShow.criarJanelaShow().setVisible(true);
    }
}