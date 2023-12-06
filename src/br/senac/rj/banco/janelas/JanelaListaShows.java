package br.senac.rj.banco.janelas;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import br.senac.rj.banco.modelo.Conexao;

public class JanelaListaShows extends JFrame {
    private JTable tabela;
    private DefaultTableModel modelo;
    private JFrame janelaPrincipal;

    public JanelaListaShows(JFrame janelaPrincipal) {
        this.janelaPrincipal = janelaPrincipal;

        // Configuração da janela
        setTitle("Lista de Shows");
        setSize(800, 400); // Aumentei o tamanho da janela
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        // Inicializa o modelo da tabela
        modelo = new DefaultTableModel();
        tabela = new JTable(modelo);

        // Adiciona colunas ao modelo
        modelo.addColumn("ID Banda");
        modelo.addColumn("Nome");
        modelo.addColumn("Data do Show");
        modelo.addColumn("Pais");

        // Obtém dados do banco de dados
        obterDadosDoBanco();

        // Adiciona a tabela a um painel de rolagem
        JScrollPane scrollPane = new JScrollPane(tabela);
        getContentPane().add(scrollPane);

        JButton btnAtualizar = new JButton("Atualizar");
        btnAtualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Atualiza as informações na tabela ao clicar no botão "Atualizar"
                obterDadosDoBanco();
            }
        });

        // Adiciona o botão de atualização ao painel de botões
        JPanel painelBotoes = new JPanel();
        painelBotoes.add(btnAtualizar);
        getContentPane().add(painelBotoes, BorderLayout.SOUTH);

        // Exibe a janela
        setVisible(true);
    }

    private void obterDadosDoBanco() {
        Connection conexao = null;
        try {
            conexao = Conexao.conectaBanco();
            String sql = "SELECT * FROM `shows`";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // Limpa as linhas existentes no modelo
            modelo.setRowCount(0);

            // Adiciona linhas ao modelo com os dados do banco
            while (rs.next()) {
                int id_banda = rs.getInt("id_banda");
                String nome = rs.getString("nome");
                Date data = rs.getDate("data_do_show");
                String pais = rs.getString("pais");
                modelo.addRow(new Object[]{id_banda, nome, data, pais});
            }

            // Fecha recursos
            rs.close();
            ps.close();
            conexao.close();
        } catch (SQLException e) {
            System.out.println("Erro ao obter dados do show: " + e.toString());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new JanelaListaShows(new JFrame()));
    }
}