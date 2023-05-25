/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsul.cc.lpoo.om;

import br.edu.ifsul.cc.lpoo.om.gui.JFramePrincipal;
import br.edu.ifsul.cc.lpoo.om.gui.autenticacao.JPanelAutenticacao;
import br.edu.ifsul.cc.lpoo.om.model.Funcionario;
import br.edu.ifsul.cc.lpoo.om.model.dao.PersistenciaJDBC;
import javax.swing.JOptionPane;

/**
 *
 * @author 20212pf.cc0010
 */
public class Controle {

    private PersistenciaJDBC conexaoJDBC;
    
    private JFramePrincipal frame;
    
    private JPanelAutenticacao telaAutenticacao; //tela de autentiacao.
    

    
    protected Controle(){
        
    }
    public void initComponents(){
        
        //inicialização do atributo da instância -> frame
        frame = new JFramePrincipal(this);
        
      
        
        telaAutenticacao = new JPanelAutenticacao(this);// inicializa
        frame.addTela(telaAutenticacao, "tela_autenticacao"); //add carta (painel) no baralho (jframe)
        frame.showTela("tela_autenticacao");//coloca em primeiro plano a carta (painel)
        
          frame.setVisible(true);
       
    }
    
    public boolean conectarBD() throws Exception {

            conexaoJDBC = new PersistenciaJDBC();

            if(conexaoJDBC!= null){

                        return conexaoJDBC.conexaoAberta();
            }

            return false;
    }
    
    public void fecharBD(){

        System.out.println("Fechando conexao com o Banco de Dados");
        conexaoJDBC.fecharConexao();

    }
    
      public void autenticar(String cpf, String senha, String tipo) {
        //  implementar o metodo doLogin da classe PersistenciaJDBC
        //  chamar o doLogin e verificar o retorno.
        // se o retorno for nulo, informar ao usuário
        //se nao for nulo, apresentar a tela de boas vindas e o menu.
        try{

            Funcionario f =  conexaoJDBC.doLogin(cpf, senha,tipo);
            
            if(f != null){

                JOptionPane.showMessageDialog(telaAutenticacao, "Jogador "+f.getCpf()+" autenticado com sucesso!", "Autenticação", JOptionPane.INFORMATION_MESSAGE);

                

            }else{

                JOptionPane.showMessageDialog(telaAutenticacao, "Dados inválidos!", "Autenticação", JOptionPane.INFORMATION_MESSAGE);
            }

        }catch(Exception e){

            JOptionPane.showMessageDialog(telaAutenticacao, "Erro ao executar a autenticação no Banco de Dados!", "Autenticação", JOptionPane.ERROR_MESSAGE);
        }
    }
}
