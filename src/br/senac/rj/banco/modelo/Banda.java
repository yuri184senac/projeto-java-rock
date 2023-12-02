package br.senac.rj.banco.modelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
	
	/*CADASTRAR*/
	public boolean cadastrarBanda(Banda banda) {
		Connection conexao = null;
		try {
			conexao = Conexao.conectaBanco();
			String sql = "";
			PreparedStatement ps = conexao.prepareStatement(sql);
			int totalRegistrosAfetados = ps.executeUpdate(); 
		}  catch (SQLException e) {
			System.out.println("Erro ao cadastrar banda: " + e.toString());
			return false;
		}finally {
			Conexao.fechaConexao(conexao);
		}
		
		
	}
	/*ATUALIZAR*/
	/*EXCLUIR*/
