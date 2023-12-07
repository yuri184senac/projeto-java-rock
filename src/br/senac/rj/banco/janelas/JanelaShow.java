package br.senac.rj.banco.janelas;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import br.senac.rj.banco.modelo.Banda;
import br.senac.rj.banco.modelo.Show;
import br.senac.rj.banco.service.Utilitarios;

public class JanelaShow {

    public static JFrame criarJanelaShow() {

        JFrame janelaShow = new JFrame("Cadastro de Shows");
        janelaShow.setResizable(false);
        janelaShow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        janelaShow.setSize(440, 325);
        janelaShow.getContentPane().setBackground(Color.decode("#100039"));
        //IMAGEM DE BACKGROUND
        ImageIcon icon = new ImageIcon("./src/imagem/show-bk.gif");
        JLabel background = new JLabel(icon);
        background.setSize(440, 300);
        background.setBounds(0, 0, 440, 300);
        //FIM-IMAGEM-DE-BACKGROUND
        
        Container caixa = janelaShow.getContentPane();
        caixa.setLayout(null);
        
      
        
        // Id do Show
        JLabel labelIdShow = new JLabel("Id do show:");
        labelIdShow.setBounds(50, 40, 120, 20);
        JTextField campoIdShow = new JTextField();
        campoIdShow.setBounds(180, 40, 35, 20);
        
        // Nome Banda
        JLabel labelIdBanda = new JLabel("Nome da banda:");
        labelIdBanda.setBounds(50, 80, 120, 20);
        JTextField campoNomeBanda = new JTextField();
        campoNomeBanda.setBounds(180, 80, 150, 20);
        
        // Nome
        JLabel labelNome = new JLabel("Nome do show:");
        labelNome.setBounds(50, 120, 120, 20);
        JTextField campoNome = new JTextField();
        campoNome.setBounds(180, 120, 150, 20);

        // País
        JLabel labelPais = new JLabel("País:");
        labelPais.setBounds(50, 160, 120, 20);
        JTextField campoPais = new JTextField();
        campoPais.setBounds(180, 160, 150, 20);

        // Data do Show
        JLabel labelDataShow = new JLabel("Data do Show:");
        labelDataShow.setBounds(50, 200, 120, 20);
        
        //DIA - MES - ANO
        JTextField campoDiaData = new JTextField();
        campoDiaData.setBounds(180, 200, 20, 20);
        JLabel separador1 = new JLabel("/");
        separador1.setBounds(210, 200, 120, 20);
        JTextField campoMesData = new JTextField();
        campoMesData.setBounds(220, 200, 20, 20);
        JLabel separador2 = new JLabel("/");
        separador2.setBounds(250, 200, 120, 20);
        JTextField campoAnoData = new JTextField();
        campoAnoData.setBounds(260, 200, 35, 20);
        
        
               
        caixa.add(labelIdShow);
        caixa.add(campoIdShow);
        caixa.add(labelIdBanda);
        caixa.add(campoNomeBanda);
        caixa.add(labelNome);
        caixa.add(campoNome);
        caixa.add(labelPais);
        caixa.add(campoPais);
        caixa.add(labelDataShow);
        caixa.add(campoDiaData);
        caixa.add(campoMesData);
        caixa.add(campoAnoData);
        caixa.add(separador1);
        caixa.add(separador2);
       
        
        labelIdBanda.setForeground(Color.WHITE);
        labelNome.setForeground(Color.WHITE);
        labelPais.setForeground(Color.WHITE);
        labelDataShow.setForeground(Color.WHITE);
        separador1.setForeground(Color.WHITE);
        separador2.setForeground(Color.WHITE);
        // Adicione os botões Cadastrar, Editar, Deletar, conforme antes
        
        JButton botaoPesquisar = new JButton("🔍");
        botaoPesquisar.setBounds(340, 40, 60, 20);
        arredondarBotao(botaoPesquisar);
        janelaShow.add(botaoPesquisar);
        
        JButton botaoCadastrar = new JButton("Cadastrar");
        botaoCadastrar.setBounds(50, 240, 100, 40);
        arredondarBotao(botaoCadastrar);
        janelaShow.add(botaoCadastrar);

        JButton botaoEditar = new JButton("Editar");
        botaoEditar.setBounds(160, 240, 100, 40);
        arredondarBotao(botaoEditar);
        janelaShow.add(botaoEditar);
        
        JButton botaoDeletar = new JButton("Deletar");
        botaoDeletar.setBounds(270, 240, 100, 40);
        arredondarBotao(botaoDeletar);
        janelaShow.add(botaoDeletar);
        
        JButton botaoLimpar= new JButton("Limpar");
        botaoLimpar.setBounds(340, 10, 80, 24);
        arredondarBotao(botaoLimpar);
        janelaShow.add(botaoLimpar);
        
        //BOTOES
        botaoEditar.setEnabled(false);
        
        //BACKGROUND
        caixa.add(background);               
        
        Show show = new Show();       
        botaoPesquisar.addActionListener(new ActionListener() {        	
			public void actionPerformed(ActionEvent arg0) {
				int id_show = Integer.parseInt(campoIdShow.getText());	
				
				Show shw = show.consultarShow(id_show);	//Consulta o show		
				Banda banda = show.getBandaBy(id_show);	 			
				String date[] = Utilitarios.dateSliceDayMonthYear(shw.getDate());
				campoDiaData.setText(date[0]);
				campoMesData.setText(date[1]);
				campoAnoData.setText(date[2]);
				campoPais.setText(shw.getPais());
				campoNomeBanda.setText(banda.getNome());
				campoNome.setText(shw.getNomeShow());
			}
		});
                
        botaoCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (Utilitarios.verificacaoDialogBox(janelaShow, "Deseja confirmar a ação?", null, "Ação cancelada")) {                	                 	
                	
                	
                	String dia = campoDiaData.getText();
                	String mes = campoMesData.getText();
                	String ano =campoAnoData.getText();
                	String sqlData = ano+"-"+mes+"-"+dia;
                	
                	
                	Banda banda = show.getBandaBy(campoNomeBanda.getText());
                	show.setIdBanda(banda.getId_banda());                	
                	show.setNomeShow(campoNome.getText());            	                	                	
                	show.setPais(campoPais.getText());
                	show.setDate(sqlData);
                	
                    // Preencha os dados do show (idBanda, nome, pais, dataShow, etc.)
                    boolean result = show.cadastrarShow();
                    if (result) {
                        JOptionPane.showMessageDialog(janelaShow, "Show Cadastrado com Sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(janelaShow, "Não foi possível realizar o cadastro do show");
                    }
                }
            }
        });

        botaoEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	try {
            		Banda banda = new Banda();
            		if (Utilitarios.verificacaoDialogBox(janelaShow, "Deseja confirmar a ação?", null, "Ação cancelada")) {            			
            			//DATA
            			String dia = campoDiaData.getText();
                    	String mes = campoMesData.getText();
                    	String ano =campoAnoData.getText();
                    	String sqlData = ano+"-"+mes+"-"+dia;
                    	banda = show.getBandaBy(campoNomeBanda.getText());//Pega o id da banda através do nome
                    	//GRAVA OS DADOS NO OBJETO
                    	show.setIdBanda(banda.getId_banda());                    	
                    	show.setNomeShow(campoNome.getText());            	                	                	
                    	show.setPais(campoPais.getText());
                    	show.setDate(sqlData);
                    	//GRAVA OS DADOS NO BANCO
                    	show.editarShow();
            		}    		
            		/*if ( == 0 || vocalista.isEmpty() || baterista.isEmpty() || guitarrista.isEmpty() || guitarrista2.isEmpty()) 
                    	{
    	                    JOptionPane.showMessageDialog(janelaIntegrantes, "Preencha todos os campos antes de cadastrar!",
    	                    "Aviso", JOptionPane.WARNING_MESSAGE);	                    
                    	} else { //O CAMPO NULL É PARA INSERIR O BAIXISTA DEPOIS
                            	intg.atualizarIntegrantes(fkBanda, vocalista, baterista, null, guitarrista, guitarrista2);
    		                    JOptionPane.showMessageDialog(janelaIntegrantes, "Informações do integrante editadas com sucesso! 🎸❤️");
    		                    botaoEditar.setEnabled(false);
                    	}		                	                       		         
            		}*/
                	       	                	               
                } catch (NumberFormatException erro) {
                    JOptionPane.showMessageDialog(janelaShow, "Preencha o campo 'ID da Banda' corretamente!",
                            "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        botaoDeletar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	
                // Adicione lógica para deletar o show
                // Utilize a lógica semelhante à do cadastro, mas para a deleção
            }
        });
        
        botaoLimpar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                campoNomeBanda.setText("");
                campoNome.setText("");
                campoPais.setText(""); 
                campoDiaData.setText("");
            	campoMesData.setText("");
            	campoAnoData.setText("");
            }
        });


        return janelaShow;
    }

    private static void arredondarBotao(JButton botao) {
        int tamanhoBorda = 5;

        botao.setBorder(BorderFactory.createEmptyBorder(tamanhoBorda, tamanhoBorda, tamanhoBorda, tamanhoBorda));
        botao.setBackground(Color.decode("#c27500"));
        botao.setForeground(Color.WHITE); // Cor do texto
        botao.setContentAreaFilled(true); //Permite aparecer a cor

        botao.addActionListener(new ActionListener() {
            @Override // ActionListener É PAI do actionPerformed
            public void actionPerformed(ActionEvent e) {
                // Lógica do botão
            }
        });
    }
  
}