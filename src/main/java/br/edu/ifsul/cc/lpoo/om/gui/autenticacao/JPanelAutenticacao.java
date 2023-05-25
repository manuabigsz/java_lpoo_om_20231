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
public class JPanelAutenticacao extends JPanel implements ActionListener {

    private Controle controle;
    private GridBagLayout gridLayout;
    private GridBagConstraints posicionador;

    private JLabel lblCpf;
    private JLabel lblSenha;
    private JTextField txfCpf;
    private JPasswordField psfSenha;
    private JButton btnLogar;
    private Border defaultBorder;
    private JLabel lblTipo;
   private JTextField txtTipo;

    //construtor da classe que recebe um parametro.
    public JPanelAutenticacao(Controle controle) {

        this.controle = controle;
        initComponents();
    }

    private void initComponents() {

        gridLayout = new GridBagLayout();//inicializando o gerenciador de layout
        this.setLayout(gridLayout);//definie o gerenciador para este painel.

        lblCpf = new JLabel("Cpf:");
        lblCpf.setToolTipText("Campo para digitação do CPF"); //acessibilidade
        posicionador = new GridBagConstraints();
        posicionador.gridy = 0;//policao da linha (vertical)
        posicionador.gridx = 0;// posição da coluna (horizontal)
        this.add(lblCpf, posicionador);//o add adiciona o rotulo no painel

        txfCpf = new JTextField(10); //columns é a largura do campo de texto
        txfCpf.setFocusable(true);    //acessibilidade    
        txfCpf.setToolTipText("Campo para digitação do CPF"); //acessibilidade
        Util.considerarEnterComoTab(txfCpf);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 0;//policao da linha (vertical)
        posicionador.gridx = 1;// posição da coluna (horizontal)
        defaultBorder = txfCpf.getBorder();
        this.add(txfCpf, posicionador);//o add adiciona o rotulo no painel        

        lblSenha = new JLabel("Senha:");
        lblSenha.setToolTipText("Campo para digitação da senha"); //acessibilidade        
        posicionador = new GridBagConstraints();
        posicionador.gridy = 1;//policao da linha (vertical)
        posicionador.gridx = 0;// posição da coluna (horizontal)
        this.add(lblSenha, posicionador);//o add adiciona o rotulo no painel

        psfSenha = new JPasswordField(10);
        psfSenha.setFocusable(true);    //acessibilidade    
        psfSenha.setToolTipText("Campo para digitação da senha"); //acessibilidade  
        Util.considerarEnterComoTab(psfSenha);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 1;//policao da linha (vertical)
        posicionador.gridx = 1;// posição da coluna (horizontal)
        this.add(psfSenha, posicionador);//o add adiciona o rotulo no painel  

       
        
        //lbl TIPO
        
         lblTipo = new JLabel("Tipo:");
        lblTipo.setToolTipText("Campo para digitação do Tipo"); //acessibilidade
        posicionador = new GridBagConstraints();
        posicionador.gridy = 2;//policao da linha (vertical)
        posicionador.gridx = 0;// posição da coluna (horizontal)
        this.add(lblTipo, posicionador);//o add adiciona o rotulo no painel

        txtTipo = new JTextField(10); //columns é a largura do campo de texto
        txtTipo.setFocusable(true);    //acessibilidade    
        txtTipo.setToolTipText("Campo para digitação do Tipo"); //acessibilidade
        Util.considerarEnterComoTab(txtTipo);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 2;//policao da linha (vertical)
        posicionador.gridx = 1;// posição da coluna (horizontal)
        defaultBorder = txtTipo.getBorder();
        this.add(txtTipo, posicionador);//o add adiciona o rotulo no painel  
        
        
         btnLogar = new JButton("Autenticar");
        btnLogar.setFocusable(true);    //acessibilidade    
        btnLogar.setToolTipText("btnLogar"); //acessibilidade  
        Util.registraEnterNoBotao(btnLogar);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 3;//policao da linha (vertical)
        posicionador.gridx = 1;// posição da coluna (horizontal)
        btnLogar.addActionListener(this);//registrar o botão no Listener
        btnLogar.setActionCommand("comando_autenticar");
        this.add(btnLogar, posicionador);//o add adiciona o rotulo no painel

    }

    public void requestFocus() {

        txfCpf.requestFocus();
    }

    public void cleanForm() {

        txfCpf.setText("");
        psfSenha.setText("");

        txfCpf.setBorder(defaultBorder);
        psfSenha.setBorder(defaultBorder);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        System.out.println("evento: " + e.getActionCommand());

        if (btnLogar.getActionCommand().equals(e.getActionCommand())) {
            System.out.println("evento gerado pelo btnLogar");
        }
        
          txfCpf.setBorder(new LineBorder(Color.black, 1));
            psfSenha.setBorder(new LineBorder(Color.black, 1));

        //testa para verificar se o botão btnLogar foi clicado.
        if (e.getActionCommand().equals(btnLogar.getActionCommand())) {

            if (txfCpf.getText().trim().length() == 11) {
                txfCpf.setBorder(new LineBorder(Color.green, 1));
                if (new String(psfSenha.getPassword()).trim().length() > 6) {

                    psfSenha.setBorder(new LineBorder(Color.green, 1));

                       if(txtTipo.getText().trim().length() == 'f'  ){
                         psfSenha.setBorder(new LineBorder(Color.green, 1));
                        controle.autenticar(txfCpf.getText().trim(), new String(psfSenha.getPassword()).trim(), txtTipo.getText().trim() );

                }else{
                       JOptionPane.showMessageDialog(this, "Apenas Funfionarios podem autenticar", "Autenticação", JOptionPane.ERROR_MESSAGE);
                    psfSenha.setBorder(new LineBorder(Color.red, 2));
                    psfSenha.requestFocus();
                       }

                } else {

                    JOptionPane.showMessageDialog(this, "Informe Senha com 6 ou mais dígitos", "Autenticação", JOptionPane.ERROR_MESSAGE);
                    psfSenha.setBorder(new LineBorder(Color.red, 1));
                    psfSenha.requestFocus();

                }
                
               
            } else {
                JOptionPane.showMessageDialog(this, "Informe CPF com 11 digitos", "Autenticação", JOptionPane.ERROR_MESSAGE);
                psfSenha.setBorder(new LineBorder(Color.red, 1));
                psfSenha.requestFocus();
            }

        }

    }
}
