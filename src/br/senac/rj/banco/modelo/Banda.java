package br.senac.rj.banco.modelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Banda {
	//Atributos da classe Banda
	private int id_banda;
	private String nome;
	private String pais;
	public static String[] genero = {
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
			Conexao.fechaConexao(conexao);
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
	
	/*MÉTODOS PÚBLICOS ABAIXO
	*---------------------------------------*/
	
	/*CADASTRAR*/
	public boolean cadastrarBanda(Banda banda, String genero) {
		Connection conexao = null;
		try {
			if (this.verificarSeExiste(banda.nome)) {
				conexao = Conexao.conectaBanco();
				String sql = "INSERT INTO banda (id_banda, nome, genero, pais) values(?, ?, ?, ?)";
				PreparedStatement ps = conexao.prepareStatement(sql);				
				ps.setInt(1, banda.id_banda);
				ps.setString(2, banda.nome);
				ps.setString(3, genero);
				ps.setString(4, banda.pais);
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
	public boolean atualizarBanda(Banda banda, String genero) {
		Connection conexao = null;
		try {			
			if (this.verificarSeExiste(banda.nome)) {
				conexao = Conexao.conectaBanco();
				String sql = "UPDATE banda SET nome=?, genero=?, pais=? WHERE id_banda=?";
				PreparedStatement ps = conexao.prepareStatement(sql);		
				ps.setString(1, banda.nome);
				ps.setString(2, genero);
				ps.setString(3, banda.pais);
				ps.setInt(4, banda.id_banda);				
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
	
