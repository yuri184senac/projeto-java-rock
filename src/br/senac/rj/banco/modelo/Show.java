package br.senac.rj.banco.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



import br.senac.rj.banco.service.Utilitarios;

public class Show {
	
    private int idShow;
    private int idBanda;
    private String nomeShow; 
    private String pais;
    private String date;

    public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public static String[] paisList = {
            "Brasil",
            "Alemanha",
            "FranÃ§a"
    };

    public String[] getPaisList() {
        return paisList;
    }

    public String getNomeShow() {
		return nomeShow;
	}

	public void setNomeShow(String nomeShow) {
		this.nomeShow = nomeShow;
	}

	public int getIdShow() {
        return idShow;
    }

    public void setIdShow(int idShow) {
        this.idShow = idShow;
    }

    public int getIdBanda() {
        return idBanda;
    }

    public void setIdBanda(int idBanda) {
        this.idBanda = idBanda;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
    
    
    //CONSULTAR
    /**public boolean obterShowsPorDataRecente() {
    	Connection conexao = null;
		try {			
			conexao = Conexao.conectaBanco();
			String sql =  "SELECT * FROM show";
			PreparedStatement ps = conexao.prepareStatement(sql);			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Show show
			}						
			
		} catch (SQLException e) {
			System.out.println("Erro ao pegar dados da banda: " + e.toString());
			
		} finally {			
			if (conexao != null) { Conexao.fechaConexao(conexao);}			
		}		
	}**/
    	
    
    
    //Pegar id da banda pelo nome
    public Banda getBanda(String nome) {    	
		Connection conexao = null;
		try {	
			Banda banda = new Banda();
			conexao = Conexao.conectaBanco();
			String sql =  "SELECT id_banda, nome FROM banda WHERE nome LIKE '%"+nome+"%'";
			PreparedStatement ps = conexao.prepareStatement(sql);			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				banda.setId_banda(rs.getInt("id_banda")); 
				banda.setNome(rs.getString("nome"));			   
			}						
			return banda;
		} catch (SQLException e) {
			System.out.println("Erro ao pegar dados da banda: " + e.toString());
			return null;
		} finally {			
			if (conexao != null) { Conexao.fechaConexao(conexao);}			
		}		
	}
    
    // Retornar dados show
    
    // Cadastrar
    public boolean cadastrarShow() {
        Connection conexao = null;
        try {
            conexao = Conexao.conectaBanco();
            String sql ="INSERT INTO shows (id_show, id_banda, nome, pais, data_do_show) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, Utilitarios.gerarId("shows"));
            ps.setInt(2, this.idBanda);
            ps.setString(3, this.nomeShow);
            ps.setString(4, this.pais);
            ps.setString(5, this.date);
            return Utilitarios.verificarRegistro(ps, "Show cadastrado com sucesso!", "Erro ao cadastrar show");
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar show: " + e.toString());
            return false;
        } finally {
            if (conexao != null) {
                Conexao.fechaConexao(conexao);
            }
        }
    }

    // Editar
    public boolean editarShow() {
        Connection conexao = null;
        try {
            conexao = Conexao.conectaBanco();
            String sql = "UPDATE shows SET id_banda=?, pais=?, data_do_show=? WHERE id_show=?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, this.idBanda);        
            ps.setString(2, this.pais);
            ps.setString(3, this.date);
            ps.setInt(4, this.idShow);

            return Utilitarios.verificarRegistro(ps, "Show atualizado com sucesso!", "Erro ao atualizar show");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar show: " + e.toString());
            return false;
        } finally {
            if (conexao != null) {
                Conexao.fechaConexao(conexao);
            }
        }
    }

    // Deletar
    public boolean deletarShow() {
        Connection conexao = null;
        try {
            conexao = Conexao.conectaBanco();
            String sql = "DELETE FROM shows WHERE id_show=?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, this.idShow);
            return Utilitarios.verificarRegistro(ps, "Show deletado com sucesso!", "Erro ao deletar show");
        } catch (SQLException e) {
            System.out.println("Erro ao deletar show: " + e.toString());
            return false;
        } finally {
            if (conexao != null) {
                Conexao.fechaConexao(conexao);
            }
        }
    }
}