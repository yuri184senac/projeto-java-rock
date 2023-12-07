package br.senac.rj.banco.modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.senac.rj.banco.service.Utilitarios;

public class Show {
	
    private int idShow;
    private int idBanda;
    private String nomeShow; 
    private String pais;
    private String date;
    
    public Show() {
    	
    }
    
    public Show(int idShow, int idBanda, String nomeShow, String pais, String date) {
    	this.idShow = idShow;
    	this.idBanda = idBanda;
    	this.nomeShow = nomeShow;
    	this.pais = pais;
    	this.date = date;
    }

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
    
    
    private boolean verificarCadastro(int id_show, int id_banda, String nome_show) {
    	Connection conexao = null;
		try {							
			conexao = Conexao.conectaBanco();
			String sql1 = "SELECT * FROM `banda` b LEFT JOIN `shows` s ON b.id_banda=s.id_banda WHERE id_show=? AND s.nome=? AND b.id_banda=?";
			PreparedStatement ps = conexao.prepareStatement(sql1);
			ps.setInt(1, id_show);
			ps.setString(2, nome_show);
			ps.setInt(3, id_banda);
			ResultSet rs = ps.executeQuery();
			if (rs.isBeforeFirst()) { //Verifica se não está antes do primeiro registro
				System.out.println("verificarShow() --> Show não existe");
				return true;
			} else {				
				System.out.println("verificarShow() --> Show já existe");
				return false;
			}						
		} catch (SQLException e) {
			System.out.println("Erro ao pegar dados da banda: " + e.toString());
			return false;
		} finally {			
			if (conexao != null) { Conexao.fechaConexao(conexao);}			
		}				
    }
      
    //Pegar id da banda pelo nome
    public Banda getBandaBy(String nome) {    	
		Connection conexao = null;
		try {	
			Banda banda = new Banda();
			conexao = Conexao.conectaBanco();
			String sql =  "SELECT id_banda, nome FROM banda WHERE nome LIKE '%"+nome+"%'";
			PreparedStatement ps = conexao.prepareStatement(sql);			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id_banda");
				this.idBanda = id;
				banda.setId_banda(id);
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
    //OVERLOAD
    public Banda getBandaBy(int id_show) {
    	Connection conexao = null;
		try {				
			Banda banda = new Banda();
			conexao = Conexao.conectaBanco();
			String sql1 = "SELECT * FROM `banda` b LEFT JOIN `shows` s ON b.id_banda=s.id_banda WHERE id_show=?";
			PreparedStatement ps = conexao.prepareStatement(sql1);
			ps.setInt(1, id_show);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				banda.setId_banda(rs.getInt("id_banda")); 
				banda.setNome(rs.getString("b.nome"));
				System.out.println(rs.getString("b.nome")); 
				System.out.println("banda"+banda.getNome());
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
    public Show consultarShow(int id) {
    	Connection conexao = null;
		try {							
			Show show = null;									
			conexao = Conexao.conectaBanco();
			String sql = "SELECT * FROM shows WHERE id_show=?";//pegar todos os shows relacionandos à aquele id da banda
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				  int idShow = rs.getInt("id_show");
				  int idBanda = rs.getInt("id_banda");
				  String nomeShow = rs.getString("nome"); 
				  String pais = rs.getString("pais");
				  Date date = rs.getDate("data_do_show"); 									
				  show = new Show(idShow, idBanda, nomeShow, pais, Utilitarios.dateToStringBrasil(date));				
			}				
			return show;
		} catch (SQLException e) {
			System.out.println("Erro ao pegar dados da banda: " + e.toString());
			return null;
		} finally {			
			if (conexao != null) { Conexao.fechaConexao(conexao);}			
		}		
    }
    
    
    // Cadastrar
    public boolean cadastrarShow() {
        Connection conexao = null;
        try {
        	conexao = Conexao.conectaBanco();
        	int id_show = Utilitarios.gerarId("shows");
        	//CONDIÇÃO DE CADASTRO
        	if(this.verificarCadastro(id_show, this.idBanda, this.nomeShow)) {
        		String sql ="INSERT INTO shows (id_show, id_banda, nome, pais, data_do_show) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement ps = conexao.prepareStatement(sql);
                ps.setInt(1, id_show);
                ps.setInt(2, this.idBanda);
                ps.setString(3, this.nomeShow);
                ps.setString(4, this.pais);
                ps.setString(5, this.date);
                return Utilitarios.verificarRegistro(ps, "Show cadastrado com sucesso!", "Erro ao cadastrar show");
        	}
        	return false;
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
    public boolean deletarShow(int id) {
        Connection conexao = null;
        try {
            conexao = Conexao.conectaBanco();
            String sql = "DELETE FROM shows WHERE id_show=?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);
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