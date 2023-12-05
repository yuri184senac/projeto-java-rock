package br.senac.rj.banco.modelo;
import java.sql.Connection;
import java.sql.PreparedStatement;


import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Random;

import br.senac.rj.banco.service.Utilitarios;



public class Banda {
	//Atributos da classe Banda
	private int id_banda;	
	private String nome;
	private String pais;
	private String genero;
	
	public static String[] generoList = {
			"",
			"EARLY METAL",
			"ORIGINAL HARD ROCK",
			"SHOCK ROCK",
			"POWER METAL",
			"EARLY PUNK",
			"ORIGINAL HARDCORE",
			"GLAM METAL",
			"POP METAL",
			"PROGRESSIVE METAL",
			"NEW WAVE OF BRITSH HEAVY METAL",
			"THRASH METAL",
			"FIRST WAVE OF BLACK METAL",
			"INDUSTRIAL METAL",
			"STONER METAL",
			"GRUNGE",
			"DEATH METAL",
			"GRINDCORE",
			"NORWEGIAN BLACK METAL",
			"HARD ALTERNATIVE",
			"GOTH METAL",
			"NU METAL",
			"METALCORE",
			"SWEDISH DEATH METAL",
			"NEW WAVE OF AMERICAN METAL"
	};  
	
	//CONSTRUCTOR
	public Banda() { }
	
	public int getId_banda() {
		return id_banda;
	}

	public void setId_banda(int id_banda) {
		this.id_banda = id_banda;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	};
	
	public String getGenero() {
		return genero;
	}
	/*#MÉTODOS PRIVADOS ABAIXO
	* ---------------------------------------*/
	
	/*VERIFICA REGISTRO DE NOME DA BANDA*/
	private boolean verificarSeExiste(String nome) {		  
		Connection conexao = null;
		try {			
			conexao = Conexao.conectaBanco();
			String sql = "SELECT nome FROM banda WHERE nome=?";
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setString(1, nome);
			ResultSet rs = ps.executeQuery();
			if (!rs.isBeforeFirst()) { //Verifica se não está antes do primeiro registro
				System.out.println("verificarBanda() --> Banda não existe");				
				return true;
			} else {				
				System.out.println("verificarBanda() --> Banda já existe");
				return false;
			}
		} catch (SQLException e) {
			System.out.println("Erro ao cadastrar banda: " + e.toString());
			return false;
		}finally {
			if (conexao != null) { Conexao.fechaConexao(conexao); }
		}
	}
			
	   	
	/*MÉTODOS PÚBLICOS ABAIXO
	*---------------------------------------*/
	
	
	public boolean getBanda(String nome) {
		Connection conexao = null;
		try {			
			conexao = Conexao.conectaBanco();
			String sql =  "SELECT * FROM banda WHERE nome LIKE '%"+nome+"%'";
			PreparedStatement ps = conexao.prepareStatement(sql);			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				this.id_banda = rs.getInt("id_banda");				
				this.nome = rs.getString("nome");
				this.genero = rs.getString("genero");
				this.pais = rs.getString("pais");				
			}				
			return true;
		} catch (SQLException e) {
			System.out.println("Erro ao pegar dados da banda: " + e.toString());
			return false;
		} finally {
			if (conexao != null) { Conexao.fechaConexao(conexao);}
		}		
	}
	
	/*CADASTRAR*/
	public boolean cadastrarBanda(String genero) {				
		Connection conexao = null;
		try {
			if (this.verificarSeExiste(this.nome)) {
				conexao = Conexao.conectaBanco();
				String sql = "INSERT INTO banda (id_banda, nome, genero, pais) values(?, ?, ?, ?)";
				PreparedStatement ps = conexao.prepareStatement(sql);				
				ps.setInt(1, Utilitarios.gerarId("banda"));
				ps.setString(2, this.nome);
				ps.setString(3, genero);
				ps.setString(4, this.pais);
				return Utilitarios.verificarRegistro(ps, "Banda cadastrada com sucesso!", "Banda cadastrada sem sucesso");
			}
			return false;
		}  catch (SQLException e) {
			System.out.println("Erro ao cadastrar banda: " + e.toString());
			return false;
		} finally {
			if (conexao != null) { Conexao.fechaConexao(conexao);}
		}				
	}
	/*ATUALIZAR*/
	public boolean atualizarBanda(int id, String nome, String genero, String pais) {		
		Connection conexao = null;
		try {											
			conexao = Conexao.conectaBanco();
			String sql = "UPDATE banda SET nome=?, genero=?, pais=? WHERE id_banda=?";
			PreparedStatement ps = conexao.prepareStatement(sql);		
			ps.setString(1, nome);
			ps.setString(2, genero);
			ps.setString(3, pais);
			ps.setInt(4, id);				
			return Utilitarios.verificarRegistro(ps, "Atualização realizada", "Atualização não realizada");									
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar banda: " + e.toString());
			return false;
		} finally {
			if (conexao != null) { Conexao.fechaConexao(conexao);}
		}			
	}
	/*EXCLUIR*/
	public boolean deletarBanda() {
		Connection conexao = null;
		try {
			conexao = Conexao.conectaBanco();
			String sql = "DELETE from banda WHERE id_banda=?";
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, this.id_banda);
			return Utilitarios.verificarRegistro(ps, "Exclusão realizada", "Exclusão não realizada");			
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar banda: " + e.toString());
			return false;
		} finally {
			if (conexao != null) { Conexao.fechaConexao(conexao);}
		}		
	}
}
	
