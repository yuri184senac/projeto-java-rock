package br.senac.rj.banco.janelas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
        
        tabela.setOpaque(true);
        tabela.setBackground(new Color(194,  117, 0));
        // Adiciona colunas ao modelo
        modelo.addColumn("Banda");
        modelo.addColumn("Nome do Show");      
        modelo.addColumn("Pais");
        modelo.addColumn("Data do Show");
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
            String sql = "SELECT * FROM `banda` b LEFT JOIN `shows` s ON b.id_banda=s.id_banda WHERE data_do_show IS NOT NULL ORDER BY data_do_show DESC";            
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            // Limpa as linhas existentes no modelo
            modelo.setRowCount(0);
            
            // Adiciona linhas ao modelo com os dados do banco
            while (rs.next()) {
                String nome_banda = rs.getString("nome");
                String nome = rs.getString("s.nome");
                Date data = rs.getDate("data_do_show");
                String pais = rs.getString("pais");                
                //Formatando data
                SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
                f.setTimeZone(TimeZone.getTimeZone("UTC"));
                String dataFormatada = f.format(data);                               
                modelo.addRow(new Object[]{nome_banda, nome, pais, dataFormatada});
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