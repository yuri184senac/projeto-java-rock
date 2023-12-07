package br.senac.rj.banco.service;
import javax.swing.JOptionPane;

import br.senac.rj.banco.modelo.Conexao;

import javax.swing.JFrame;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.TimeZone;


public class Utilitarios {
	//Spawna uma caixa de mensagem de confirmação da ação a ser realizada.
	public static boolean verificacaoDialogBox(JFrame janela, String pergunta, String msgSuccesso, String msgError) {
		try {
			 int resposta = JOptionPane.showConfirmDialog(janela, pergunta,
                     "Confirmação", JOptionPane.YES_NO_OPTION);
			 
			 if (resposta == JOptionPane.YES_OPTION) {
                 if (msgSuccesso != null) { JOptionPane.showMessageDialog(janela, msgSuccesso); };    
                 return true;
             } else {
            	 return false;
             }
			
			 
		} catch (NumberFormatException erro) {
            JOptionPane.showMessageDialog(janela, msgError,
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
	}
	
	//Gera um id aleatório
	public static int gerarId(String tabela_nome) {
		Random rand = new Random();	
		int num = rand.nextInt(1,9999);
		boolean flag = true;		
		String sql = null;
		Connection conexao = null;
	    try {	    	
	    	conexao = Conexao.conectaBanco();
	    	switch(tabela_nome) {
	    	  case "integrantes":
	    		  sql = "SELECT id_integrantes FROM integrantes WHERE id_integrantes=?";
	    	    break;
	    	  case "banda":
	    	      sql = "SELECT id_banda FROM banda WHERE id_banda=?";
	    	    break;
	    	  case "shows":
	    		  sql = "SELECT id_show FROM shows WHERE id_show=?";
	    		break;
	    	  default:
	    	    System.out.println("gerarId() --> NENHUMA TABELA FOI ENCONTRADA ");
	    	}
	    	
	    	do {	    			    		
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
	
	//VERIFICAR SE A QUERY FOI EXECUTADA
	public static boolean verificarRegistro(PreparedStatement ps, String msgSucces, String msgError) {
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
	
	//formatar a data que vem do banco de dados para o padrão brasileiro
	public static String dateToStringBrasil(Date data) {
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        f.setTimeZone(TimeZone.getTimeZone("UTC"));
        String dataFormatada = f.format(data); 
        return dataFormatada;
	}
	
	public static String[] dateSliceDayMonthYear(String data) {		
		return data.split("/");
	}
		
}
