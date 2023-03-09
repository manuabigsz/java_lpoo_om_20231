/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsul.cc.om.test;

import br.edu.ifsul.cc.lpoo.om.model.Peca;
import br.edu.ifsul.cc.lpoo.om.model.dao.PersistenciaJPA;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;

/**
 *
 * @author 20212pf.cc0010
 */
public class TestPersistenceJPA {

   // @Test
    public void testConexaoGeracaoTabelas() {

        PersistenciaJPA persistencia = new PersistenciaJPA();
        if (persistencia.conexaoAberta()) {
            System.out.println("abriu a conexao com o BD via JPA");

            persistencia.fecharConexao();

            System.out.println("fechou a conexao com o BD via JPA");

        } else {
            System.out.println("Nao abriu a conexao com o BD via JPA");
        }

    }

    @Test
    public void testPersistenciaPeca() throws Exception {

        PersistenciaJPA persistencia = new PersistenciaJPA();
        if (persistencia.conexaoAberta()) {
            System.out.println("abriu a conexao com o BD via JPA");
            //EXERCICIO 1 CRRIAR UM REGISTRO NA TABELA 
            //testar as funções sobre a entidade peça
            Peca p = new Peca();
            //p.set
            p.setFornecedor("Texas");
            p.setNome("BC348");
            p.setValor(3.5f);
           
            persistencia.persist(p);
            //exercicio 2 
            // - 1: Econtrar a peça id=1
            // Caso encontrar, remover
            // Caso não encontrar, criar nova
            p = (Peca) persistencia.find(Peca.class, 1);
            if(p == null){
                System.out.println("Não encontrou o id =1");
                p = new Peca(); 
                p.setId(1);
                //encaminhar o e
                persistencia.persist(p);      
                 System.out.println("Peça com ID=1 encontrada");
            }else{      
                System.out.println("Encontrou o ID=1 para a peça " +p.getId());
                persistencia.remover(p);
                  System.out.println("Peça removida");
                
            }                 
            
        
            
            //Exercicio 3:
            /*
                -1: recuperar lista de peças
                -2: se lista.size>0 listar e remover
                -3: se lista.size ==0 inserir novo registro
                Novo @Test
            
            */
            persistencia.fecharConexao();

            System.out.println("fechou a conexao com o BD via JPA");

        } else {
            System.out.println("Nao abriu a conexao com o BD via JPA");
        }

        
    }
   
/*    @Test
    public void testPersistenciaListPeca() throws Exception {
    
        PersistenciaJPA persistencia = new PersistenciaJPA();
        if(persistencia.conexaoAberta()){
            System.out.println("testPersistenciaEndereco:");            
            
            List<Peca> list = persistencia.listPecas();
            if(!list.isEmpty()){
            
                for(Peca end : list){
                    
                    System.out.println("ID: "+end.getId());
                }
            }
            
            
            persistencia.fecharConexao();           
        }else{
            System.out.println("Nao abriu a conexao com o BD via JPA");
        }        
    }*/
    
    
}
