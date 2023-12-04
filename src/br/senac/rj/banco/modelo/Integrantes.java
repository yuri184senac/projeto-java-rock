package br.senac.rj.banco.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import br.senac.rj.banco.service.Utilitarios;

public class Integrantes {
	private int id_integrantes;
	private int fk_banda;
	private String vocalista;
	private String baterista;
	private String baixista;
	private String guitarrista1;
	private String guitarrista2;
	//REVER LOGICA
	/*VERIFICA REGISTRO DE INTEGRANTES*/
	private boolean verificarSeExiste(String nome, int id_banda) {		  
		Connection conexao = null;
		try {			
			conexao = Conexao.conectaBanco();
			String sql = "SELECT nome FROM integrantes WHERE nome=? AND id_banda=?";
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setString(1, nome);
			ps.setInt(2, id_banda);
			ResultSet rs = ps.executeQuery();
			if (!rs.isBeforeFirst()) { //Verifica se não está antes do primeiro registro
				System.out.println("verificarBanda() --> Integrante não existe");				
				return true;
			} else {				
				System.out.println("verificarBanda() --> Integrante já existe");
				return false;
			}
		} catch (SQLException e) {
			System.out.println("Erro ao cadastrar banda: " + e.toString());
			return false;
		}finally {
			if (conexao != null) { Conexao.fechaConexao(conexao); }
		}
	}
	//REVER LOGICA
	//CADASTRAR
	public boolean cadastrarIntegrante(int banda) {				
		Connection conexao = null;
		try {
			if (this.verificarSeExiste(nome, banda)) {
				conexao = Conexao.conectaBanco();
				String sql = "INSERT INTO integrantes (id_integrantes, id_banda, vocalista, baterista, baixista, guitarrista1, guitarrista2) values(?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement ps = conexao.prepareStatement(sql);				
				ps.setInt(1, Utilitarios.gerarId());
				ps.setInt(2, this.fk_banda);
				ps.setString(3, this.vocalista);
				ps.setString(4, this.baterista);
				ps.setString(5, this.baixista);
				ps.setString(6, this.guitarrista1);
				ps.setString(7, this.guitarrista2);
				return Utilitarios.verificarRegistro(ps, "Integrantes cadastrados com sucesso!", "Integrantes cadastrados sem sucesso");
			}
			return false;
		}  catch (SQLException e) {
			System.out.println("Erro ao cadastrar banda: " + e.toString());
			return false;
		} finally {
			if (conexao != null) { Conexao.fechaConexao(conexao);}
		}				
	}
	
	//EDITAR
	public boolean atualizarIntegrantes(			
			int id,
			int fk_banda,
			String vocalista,
			String baterista,
			String baixista,
			String guitarrista1,
			String guitarrista2
	) {		
		Connection conexao = null;
		try {											
			conexao = Conexao.conectaBanco();
			String sql = "UPDATE integrantes SET vocalista=?, baterista=?, baixista=?, guitarrista1=?, guitarrista2=? WHERE id_integrantes=?";
			PreparedStatement ps = conexao.prepareStatement(sql);		
			ps.setString(1, this.vocalista);
			ps.setString(2, this.baterista);
			ps.setString(3, this.baixista);
			ps.setString(4, this.guitarrista1);
			ps.setString(5, this.guitarrista2);
			return Utilitarios.verificarRegistro(ps, "Atualização realizada", "Atualização não realizada");									
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar integrantes: " + e.toString());
			return false;
		} finally {
			if (conexao != null) { Conexao.fechaConexao(conexao);}
		}			
	}
	//EXCLUIR
}
