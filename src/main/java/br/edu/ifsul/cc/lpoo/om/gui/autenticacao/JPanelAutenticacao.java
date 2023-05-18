  /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsul.cc.lpoo.om.gui.autenticacao;

import br.edu.ifsul.cc.lpoo.om.Controle;
import br.edu.ifsul.cc.lpoo.om.util.Util;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 *
 * @author 20212pf.cc0010
 */
public class JPanelAutenticacao extends JPanel implements ActionListener{
    private Controle controle;
    private GridBagLayout gridLayout;
    private GridBagConstraints posicionador;
    
     private JLabel  lblCpf;
    private JLabel lblSenha;
    private JTextField txfCpf;
    private JPasswordField psfSenha;
    private JButton btnLogar;
    private Border defaultBorder;
    
     //construtor da classe que recebe um parametro.
    public JPanelAutenticacao(Controle controle){
        
        this.controle = controle;
        initComponents();
    }
    
    
    private void initComponents(){
    
        gridLayout = new GridBagLayout();//inicializando o gerenciador de layout
        this.setLayout(gridLayout);//definie o gerenciador para este painel.
        
        lblCpf = new JLabel("Cpf:");        
        lblCpf.setToolTipText("lblCpf"); //acessibilidade
        posicionador = new GridBagConstraints();
        posicionador.gridy = 0;//policao da linha (vertical)
        posicionador.gridx = 0;// posição da coluna (horizontal)
        this.add(lblCpf, posicionador);//o add adiciona o rotulo no painel
        
        txfCpf = new JTextField(10);
        txfCpf.setFocusable(true);    //acessibilidade    
        txfCpf.setToolTipText("txfNickname"); //acessibilidade
        Util.considerarEnterComoTab(txfCpf);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 0;//policao da linha (vertical)
        posicionador.gridx = 1;// posição da coluna (horizontal)
        defaultBorder = txfCpf.getBorder();
        this.add(txfCpf, posicionador);//o add adiciona o rotulo no painel        
        
        lblSenha = new JLabel("Senha:");        
        lblSenha.setToolTipText("lblSenha"); //acessibilidade        
        posicionador = new GridBagConstraints();
        posicionador.gridy = 1;//policao da linha (vertical)
        posicionador.gridx = 0;// posição da coluna (horizontal)
        this.add(lblSenha, posicionador);//o add adiciona o rotulo no painel
        
        psfSenha = new JPasswordField(10);
        psfSenha.setFocusable(true);    //acessibilidade    
        psfSenha.setToolTipText("psfSenha"); //acessibilidade  
        Util.considerarEnterComoTab(psfSenha);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 1;//policao da linha (vertical)
        posicionador.gridx = 1;// posição da coluna (horizontal)
        this.add(psfSenha, posicionador);//o add adiciona o rotulo no painel  

        btnLogar = new JButton("Autenticar");
        btnLogar.setFocusable(true);    //acessibilidade    
        btnLogar.setToolTipText("btnLogar"); //acessibilidade  
        Util.registraEnterNoBotao(btnLogar);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 2;//policao da linha (vertical)
        posicionador.gridx = 1;// posição da coluna (horizontal)
        btnLogar.addActionListener(this);//registrar o botão no Listener
        btnLogar.setActionCommand("comando_autenticar");
        this.add(btnLogar, posicionador);//o add adiciona o rotulo no painel
                

    }
    
     public void requestFocus(){
        
        txfCpf.requestFocus();
    }
    
    public void cleanForm(){
        
        txfCpf.setText("");
        psfSenha.setText("");        
        
        txfCpf.setBorder(defaultBorder);        
        psfSenha.setBorder(defaultBorder);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
                
            //testa para verificar se o botão btnLogar foi clicado.
            if(e.getActionCommand().equals(btnLogar.getActionCommand())){
            
                //validacao do formulario.
                if(txfCpf.getText().trim().length() > 4){
                                        
                    txfCpf.setBorder(new LineBorder(Color.green,1));
                    
                    if(new String(psfSenha.getPassword()).trim().length() > 3 ){
                                                
                        psfSenha.setBorder(new LineBorder(Color.green,1));
                        
                        controle.autenticar(txfCpf.getText().trim(), new String(psfSenha.getPassword()).trim());
                        
                    }else {
                        
                        JOptionPane.showMessageDialog(this, "Informe Senha com 4 ou mais dígitos", "Autenticação", JOptionPane.ERROR_MESSAGE);
                        psfSenha.setBorder(new LineBorder(Color.red,1));
                        psfSenha.requestFocus();                        
                        
                    }
                    
                }else{
                
                    JOptionPane.showMessageDialog(this, "Informe Nickname com 4 ou mais dígitos", "Autenticação", JOptionPane.ERROR_MESSAGE);                    
                    txfCpf.setBorder(new LineBorder(Color.red,1));
                    txfCpf.requestFocus();
                }
                                      
            
        } 
        
    }
}
