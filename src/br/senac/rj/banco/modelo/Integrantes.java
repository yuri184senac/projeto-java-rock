package br.senac.rj.banco.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import br.senac.rj.banco.service.Utilitarios;

public class Integrantes {	
	private int id_integrantes;
	private int fk_banda;
	private String nome_banda;
	private String vocalista;
	private String baterista;
	private String baixista;
	private String guitarrista1;

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
	
	public String getNome_banda() {
		return nome_banda;
	}
	public void setNome_banda(String nome_banda) {
		this.nome_banda = nome_banda;
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
	
	//transforma o nome da banda para id_banda
	private int getIdBandaByName(String nome_banda) {
		int id_banda = -1;
		Connection conexao = null;
		try {			
			conexao = Conexao.conectaBanco();
			String sql =  "SELECT id_banda FROM banda WHERE nome LIKE '%"+nome_banda+"%'";
			PreparedStatement ps = conexao.prepareStatement(sql);						
			ResultSet rs = ps.executeQuery();						
			while (rs.next()) {
				id_banda = rs.getInt("id_banda");
			}		
			return id_banda;
		} catch (SQLException e) {
			System.out.println("Erro ao cadastrar banda: " + e.toString());
			return -1;
		}finally {
			if (conexao != null) { Conexao.fechaConexao(conexao); }
		}
	}
	
	
	
	private boolean verificarSeExiste(int id_banda) {		  
		Connection conexao = null;
		try {			
			System.out.println(id_banda);
			conexao = Conexao.conectaBanco();
			String sql = "SELECT id_banda FROM integrantes WHERE id_banda=?";
			PreparedStatement ps = conexao.prepareStatement(sql);			
			ps.setInt(1, id_banda);
			ResultSet rs = ps.executeQuery();
			if (!rs.isBeforeFirst()) { //Verifica se não está antes do primeiro registro
				System.out.println("verificarBanda() --> Integrantes da banda não existem");				
				return false; //BANDA NÃO TEM INTEGRANTES
			} else {				
				System.out.println("verificarBanda() --> Integrantes da banda existem");
				return true; //BANDA TEM INTEGRANTES
			}
		} catch (SQLException e) {
			System.out.println("Erro ao cadastrar banda: " + e.toString());
			return false;
		}finally {
			if (conexao != null) { Conexao.fechaConexao(conexao); }
		}
	}
	
	//GET
	
	//PAREI AQUI!
	public boolean buscarIntegrantes(String nome_banda) {
		Connection conexao = null;
		
		try {						
			int id_banda = this.getIdBandaByName(nome_banda);
			conexao = Conexao.conectaBanco();
			System.out.println(id_banda);
			String sql1 = "SELECT * FROM `banda` b LEFT JOIN `integrantes` i ON b.id_banda=i.id_banda WHERE b.id_banda=?";	
			PreparedStatement ps = conexao.prepareStatement(sql1);
			ps.setInt(1, id_banda);
			ResultSet rs = ps.executeQuery();			
					while (rs.next()) {
						this.id_integrantes = rs.getInt("id_integrantes");
						this.nome_banda = rs.getString("b.nome");
						this.fk_banda = rs.getInt("id_banda");
						this.vocalista = rs.getString("vocalista");
						this.baterista = rs.getString("baterista");					
						this.guitarrista1 = rs.getString("guitarrista1");
						this.baixista = rs.getString("baixista");						
					}
					return true;
																												
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
				String sql = "INSERT INTO integrantes (id_integrantes, id_banda, vocalista, baterista, guitarrista1, baixista) values(?, ?, ?, ?, ?, ?)";
				PreparedStatement ps = conexao.prepareStatement(sql);				
				ps.setInt(1, Utilitarios.gerarId("integrantes"));
				ps.setInt(2, this.fk_banda);
				ps.setString(3, this.vocalista);
				ps.setString(4, this.baterista);			
				ps.setString(5, this.guitarrista1);	
				ps.setString(6, this.baixista);
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
			String guitarrista1,
			String baixista
	) {		
		Connection conexao = null;
		try {											
			conexao = Conexao.conectaBanco();
			String sql = "UPDATE integrantes SET vocalista=?, baterista=?, guitarrista1=?, baixista=? WHERE id_banda=?";
			PreparedStatement ps = conexao.prepareStatement(sql);		
			ps.setString(1, vocalista);
			ps.setString(2, baterista);		
			ps.setString(3, guitarrista1);
			ps.setString(4, baixista);
			ps.setInt(5, id);
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
