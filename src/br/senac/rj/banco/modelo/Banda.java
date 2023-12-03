package br.senac.rj.banco.modelo;
import java.sql.Connection;
import java.sql.PreparedStatement;


import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Random;



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
			if (conexao != null) { Conexao.fechaConexao(conexao);}
		}
	}
	/*VERIFICA SE A QUERY FOI EXECUTADA*/
	private boolean verificarRegistro(PreparedStatement ps, String msgSucces, String msgError) {
		try {
			int totalRegistrosAfetados = ps.executeUpdate();
			if (!(totalRegistrosAfetados == 0)) { 
				System.out.println(msgSucces);
				return true; 
			} else {
				System.out.println(msgError);
				return false;		
			}
		} catch (SQLException e) {
			System.out.println("Erro ao verificar registro no banco" +e.toString());
			return false;
		}	
	}
	
	private int gerarId() {
		Random rand = new Random();		
		boolean flag = true;
		int num = rand.nextInt(1,9999);
		Connection conexao = null;
	    try {	    	
	    	conexao = Conexao.conectaBanco();
	    	do {
	    		String sql = "SELECT id_banda FROM banda WHERE id_banda=?";
				PreparedStatement ps = conexao.prepareStatement(sql);				
				ps.setInt(1, num);//verifica se tem uma banda com esse id
				ResultSet result = ps.executeQuery();
				flag = result.isFirst();
				if (flag) { num = rand.nextInt(1,9999); }
	    	} while (flag); //verifica se novoId gerado é igual ao id retornando do banco	    
	    } catch (SQLException e) {
			System.out.println("Erro ao cadastrar banda: " + e.toString());
		} finally {
			if (conexao != null) { Conexao.fechaConexao(conexao);}
		}			
	    return num;
	} 
	   
	
	/*MÉTODOS PÚBLICOS ABAIXO
	*---------------------------------------*/
	
	
	public boolean getBanda(int id) {
		Connection conexao = null;
		try {			
			conexao = Conexao.conectaBanco();
			String sql = "SELECT * FROM banda WHERE id_banda=?";
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				this.id_banda = rs.getInt("id_banda");				
				this.nome = rs.getString("nome");
				this.genero = rs.getString("genero");
				this.pais = rs.getString("pais");
				
			}		
			return true;
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar banda: " + e.toString());
			return false;
		} finally {
			if (conexao != null) { Conexao.fechaConexao(conexao);}
		}		
	}
	
	/*CADASTRAR*/
	public boolean cadastrarBanda(String genero) {
		Random rnd = new Random();		
		Connection conexao = null;
		try {
			if (this.verificarSeExiste(this.nome)) {
				conexao = Conexao.conectaBanco();
				String sql = "INSERT INTO banda (id_banda, nome, genero, pais) values(?, ?, ?, ?)";
				PreparedStatement ps = conexao.prepareStatement(sql);				
				ps.setInt(1, this.gerarId());
				ps.setString(2, this.nome);
				ps.setString(3, genero);
				ps.setString(4, this.pais);
				return this.verificarRegistro(ps, "Banda cadastrada com sucesso!", "Banda cadastrada sem sucesso");
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
	public boolean atualizarBanda(String genero) {		
		Connection conexao = null;
		try {			
			//NÃO ESTÁ ATUALIZANDO
			if (!this.verificarSeExiste(this.nome)) {				
				conexao = Conexao.conectaBanco();
				String sql = "UPDATE banda SET nome=?, genero=?, pais=? WHERE id_banda=?";
				PreparedStatement ps = conexao.prepareStatement(sql);		
				ps.setString(1, this.nome);
				ps.setString(2, genero);
				ps.setString(3, this.pais);
				ps.setInt(4, this.id_banda);				
				return this.verificarRegistro(ps, "Atualização realizada", "Cadastro não realizado");				
			}
			return false;
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar banda: " + e.toString());
			return false;
		} finally {
			if (conexao != null) { Conexao.fechaConexao(conexao);}
		}			
	}
	/*EXCLUIR*/
	public boolean excluirBanda() {
		Connection conexao = null;
		try {
			conexao = Conexao.conectaBanco();
			String sql = "DELETE from banda WHERE id_banda=?";
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, this.id_banda);
			return this.verificarRegistro(ps, "Exclusão realizada", "Exclusão não realizada");			
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar banda: " + e.toString());
			return false;
		} finally {
			if (conexao != null) { Conexao.fechaConexao(conexao);}
		}		
	}
}
	
