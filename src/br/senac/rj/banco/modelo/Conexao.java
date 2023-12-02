package br.senac.rj.banco.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Conexao {
	public static Connection conectaBanco() {
		Connection conexao = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost/rockfestival"; // URL do banco de dados
			String user = "root"; // nome do usu�rio do banco
			String password = ""; // senha do banco
			conexao = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException erro) {
			System.out.println("Driver n�o encontrado: " + erro);
		} catch (SQLException erro) {
			System.out.println("Erro de conex�o ao banco de dados: " + erro.toString());
		} catch (Exception erro) {
			System.out.println("Erro n�o identificado: " + erro.toString());
		} 
		return conexao;
	}
	public static void fechaConexao(Connection conexao) {
		try {
			conexao.close();
		} catch (Exception erro) {
			System.out.println("Erro ao fechar a conex�o: " + erro.toString());
		}
	}
}