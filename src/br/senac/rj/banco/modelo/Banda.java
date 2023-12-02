package br.senac.rj.banco.modelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Banda {
	/*Atributos da classe Banda*/
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
	
	/*CONSTRUTOR*/
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
	
	/*MÉTODOS PÚBLICOS ABAIXO
	*---------------------------------------*/
	
	/*CADASTRAR*/
	public boolean cadastrarBanda(Banda banda) {
		Connection conexao = null;
		try {
			if (this.verificarSeExiste(banda.nome)) {
				conexao = Conexao.conectaBanco();
				String sql = "";
				PreparedStatement ps = conexao.prepareStatement(sql);
				int totalRegistrosAfetados = ps.executeUpdate();
				ps.setString(1, banda.nome);
				/*TERMINAR A ADIÇÃO*/
				
				if (!(totalRegistrosAfetados == 0)) { 
					System.out.println("Cadastro realizado");return true; 
				} else {
					System.out.println("Cadastro não realizado");
					return false;
				}
				
			}
			
			return false;
		}  catch (SQLException e) {
			System.out.println("Erro ao cadastrar banda: " + e.toString());
			return false;
		}finally {
			Conexao.fechaConexao(conexao);
		}
				
	}
}
	/*ATUALIZAR*/
	/*EXCLUIR*/
