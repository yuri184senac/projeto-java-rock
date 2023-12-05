package br.senac.rj.banco.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.senac.rj.banco.service.Utilitarios;

public class Show {

    private int idShow;
    private int idBanda;
    private String nomeShow;
    private String cidade;
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

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    // Cadastrar
    public boolean cadastrarShow() {
        Connection conexao = null;
        try {
            conexao = Conexao.conectaBanco();
            String sql = "INSERT INTO shows (id_show, id_banda, nome, pais, data_do_show) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, Utilitarios.gerarId("id_show", "shows"));
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
            String sql = "UPDATE shows SET id_banda=?, cidade=?, pais=? WHERE id_show=?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, this.idBanda);
            ps.setString(2, this.cidade);
            ps.setString(3, this.pais);
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