package br.senac.rj.banco.service;
import javax.swing.JOptionPane;

import br.senac.rj.banco.modelo.Conexao;

import javax.swing.JFrame;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import javax.swing.JButton;

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
	public static int gerarId(String column,String tabela) {
		Random rand = new Random();		
		boolean flag = true;
		int num = rand.nextInt(1,9999);
		Connection conexao = null;
	    try {	    	
	    	conexao = Conexao.conectaBanco();
	    	do {
	    		String sql = "SELECT ? FROM ? WHERE id_banda=?";
				PreparedStatement ps = conexao.prepareStatement(sql);
				ps.setString(1, column);
				ps.setString(2, tabela);
				ps.setInt(3, num);//verifica se tem uma banda com esse id
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
	
	
	
	
	public static void habilitarBotao(JButton botao, boolean flag) {
		botao.setEnabled(flag);				
	}
	
	
}
