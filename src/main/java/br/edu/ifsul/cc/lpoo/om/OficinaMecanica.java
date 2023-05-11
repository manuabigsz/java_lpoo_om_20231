/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package br.edu.ifsul.cc.lpoo.om;

import javax.swing.JOptionPane;

/**
 *
 * @author 20212pf.cc0010
 */
public class OficinaMecanica {

    /*AtividadeS:
        - Criar um construtor padrão. - ok
        - No método main criar uma instancia de OficinaMecanica. - ok
        - Criar a classe controle em br.edu.ifsul.cc.lpoo.om -ok
        - Na classe controle criar os métodos:
            - método ConectarBD - ok
            - InitComponents - ok
        - Implementar o método conectarBD na classe controle - ok
        - Criar o pacote br.edu.ifsul.cc.lpoo.om.gui e criar a classe JFramePrincipal - ok
        - Implementar a classe JFramePrincipal com base no exemplo do CS::go
        - Implementar o método initComponentes na classe JFramePrincipal com base no exemplo do CS::go
    
     */

    private Controle controle;

    OficinaMecanica() {
        try {
            controle = new Controle();//cria a instancia e atribui para o atributo controle.

            ////primeiramente - tenta estabelecer a conexao com o banco de dados.
            if (controle.conectarBD()) {

                //inicializa a interface gráfica.
                controle.initComponents();

            } else {

                JOptionPane.showMessageDialog(null, "Não conectou no Banco de Dados!", "Banco de Dados", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(null, "Erro ao tentar conectar no Banco de Dados: " + ex.getLocalizedMessage(), "Banco de Dados", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }

    }

    public static void main(String[] args) {
        System.out.println("Hello World!");
        new OficinaMecanica();
    }
}
