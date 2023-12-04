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
	public int getId_integrantes() {
		return id_integrantes;
	}
	public void setId_integrantes(int id_integrantes) {
		this.id_integrantes = id_integrantes;
	}
	public int getFk_banda() {
		return fk_banda;
	}
	public void setFk_banda(int fk_banda) {
		this.fk_banda = fk_banda;
	}
	public String getVocalista() {
		return vocalista;
	}
	public void setVocalista(String vocalista) {
		this.vocalista = vocalista;
	}
	public String getBaterista() {
		return baterista;
	}
	public void setBaterista(String baterista) {
		this.baterista = baterista;
	}
	public String getBaixista() {
		return baixista;
	}
	public void setBaixista(String baixista) {
		this.baixista = baixista;
	}
	public String getGuitarrista1() {
		return guitarrista1;
	}
	public void setGuitarrista1(String guitarrista1) {
		this.guitarrista1 = guitarrista1;
	}
	public String getGuitarrista2() {
		return guitarrista2;
	}
	public void setGuitarrista2(String guitarrista2) {
		this.guitarrista2 = guitarrista2;
	}
	
	private boolean verificarSeExiste(int id_banda) {		  
		Connection conexao = null;
		try {			
			conexao = Conexao.conectaBanco();
			String sql = "SELECT id_banda FROM integrantes WHERE id_banda=?";
			PreparedStatement ps = conexao.prepareStatement(sql);			
			ps.setInt(1, id_banda);
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
	
	//GET 
	public boolean buscarIntegrantes(int id) {
		Connection conexao = null;
		try {			
			conexao = Conexao.conectaBanco();
			String sql =  "SELECT * FROM integrantes WHERE id_banda ='"+id+"'";
			PreparedStatement ps = conexao.prepareStatement(sql);			
			ResultSet rs = ps.executeQuery();
			if (!verificarSeExiste(id)) {
				while (rs.next()) {
					this.id_integrantes = rs.getInt("id_integrantes");
					this.fk_banda = rs.getInt("id_banda");
					this.vocalista = rs.getString("vocalista");
					this.baterista = rs.getString("baterista");
					this.baixista = rs.getString("baixista");
					this.guitarrista1 = rs.getString("guitarrista1");
					this.guitarrista2 = rs.getString("guitarrista2");
				}
				return true;
			} else {
				return false;
			}
					
			
		} catch (SQLException e) {
			System.out.println("Erro ao consultar integrantes: " + e.toString());
			return false;
		} finally {
			if (conexao != null) { Conexao.fechaConexao(conexao);}
		}		
	}
	
	//CADASTRAR
	public boolean cadastrarIntegrante() {				
		Connection conexao = null;
		try {			
				conexao = Conexao.conectaBanco();
				String sql = "INSERT INTO integrantes (id_integrantes, id_banda, vocalista, baterista, baixista, guitarrista1, guitarrista2) values(?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement ps = conexao.prepareStatement(sql);				
				ps.setInt(1, Utilitarios.gerarId("id_integrantes", "integrantes"));
				ps.setInt(2, this.fk_banda);
				ps.setString(3, this.vocalista);
				ps.setString(4, this.baterista);
				ps.setString(5, this.baixista);
				ps.setString(6, this.guitarrista1);
				ps.setString(7, this.guitarrista2);
				return Utilitarios.verificarRegistro(ps, "Integrantes cadastrados com sucesso!", "Integrantes cadastrados sem sucesso");						
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
			String vocalista,
			String baterista,
			String baixista,
			String guitarrista1,
			String guitarrista2
	) {		
		Connection conexao = null;
		try {											
			conexao = Conexao.conectaBanco();
			String sql = "UPDATE integrantes SET vocalista=?, baterista=?, baixista=?, guitarrista1=?, guitarrista2=? WHERE id_banda=?";
			PreparedStatement ps = conexao.prepareStatement(sql);		
			ps.setString(1, vocalista);
			ps.setString(2, baterista);
			ps.setString(3, null);
			ps.setString(4, guitarrista1);
			ps.setString(5, guitarrista2);
			ps.setInt(6, id);
			return Utilitarios.verificarRegistro(ps, "Atualização realizada", "Atualização não realizada");									
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar integrantes: " + e.toString());
			return false;
		} finally {
			if (conexao != null) { Conexao.fechaConexao(conexao);}
		}			
	}
	//EXCLUIR
	/*EXCLUIR*/
	public boolean deletarIntegrantes(int id) {
		Connection conexao = null;
		try {
			conexao = Conexao.conectaBanco();
			String sql = "DELETE i FROM integrantes i LEFT JOIN banda b ON i.id_banda=?";
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, id);
			return Utilitarios.verificarRegistro(ps, "Exclusão realizada", "Exclusão não realizada");			
		} catch (SQLException e) {
			System.out.println("Erro ao deletar integrantes: " + e.toString());
			return false;
		} finally {
			if (conexao != null) { Conexao.fechaConexao(conexao);}
		}		
	}
}
