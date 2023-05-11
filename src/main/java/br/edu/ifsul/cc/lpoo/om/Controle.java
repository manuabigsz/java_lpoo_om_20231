/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsul.cc.lpoo.om;

import br.edu.ifsul.cc.lpoo.om.gui.JFramePrincipal;
import br.edu.ifsul.cc.lpoo.om.model.dao.PersistenciaJDBC;

/**
 *
 * @author 20212pf.cc0010
 */
public class Controle {

    private PersistenciaJDBC conexaoJDBC;

    private JFramePrincipal frame; // frame principal da minha aplicação gráfica

    protected Controle() {

    }

    public boolean conectarBD() throws Exception {

        conexaoJDBC = new PersistenciaJDBC();

        if (getConexaoJDBC() != null) {

            return getConexaoJDBC().conexaoAberta();
        }

        return false;
    }

    public void initComponents() {

        //para cada nova tela de CRUD inicializar o respectivo objeto
        //para cada nova tela de CRUD inicializar o respectivo objeto
        frame = new JFramePrincipal(this);

        frame.showTela("tela_autenticacao");   //mostra

        frame.setVisible(true); // torna visível o jframe

    }

    public PersistenciaJDBC getConexaoJDBC() {
        return conexaoJDBC;
    }
    
    public void fecharBD(){

        System.out.println("Fechando conexao com o Banco de Dados");
        getConexaoJDBC().fecharConexao();

    }
}
