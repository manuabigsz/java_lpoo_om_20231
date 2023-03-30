package br.edu.ifsul.cc.om.test;

import br.edu.ifsul.cc.lpoo.om.model.Cargo;
import br.edu.ifsul.cc.lpoo.om.model.Curso;
import br.edu.ifsul.cc.lpoo.om.model.Peca;
import br.edu.ifsul.cc.lpoo.om.model.dao.PersistenciaJDBC;
import org.junit.Test;

/**
 *
 * @author 20212pf.cc0010 data 30/03/2023
 *
 * Classe contendo testes unitários para persistência de ados utilizando JDBC
 */
public class TestPersistenceJBDC {

    //@Test
    public void testPersistenciaProcedimento() throws Exception {

        //Reavaliação da Primeira Etapa
    }

    // @Test
    public void testPersistenciaConexao() throws Exception {

        //criar um objeto do tipo PersistenciaJDBC.
        PersistenciaJDBC jdbc = new PersistenciaJDBC();
        if (jdbc.conexaoAberta()) {
            System.out.println("conectou no BD via JDBC ...");
            jdbc.fecharConexao();
        } else {
            System.out.println("nao conectou no BD via JDBC ...");

        }

    }

   //@Test
    public void testPersistenciaPeca() throws Exception {
        
        PersistenciaJDBC jdbc = new PersistenciaJDBC();
        
        if (jdbc.conexaoAberta()) {
            System.out.println("conectou no BD via JDBC ...");

            //chama o método find da classe PersistencaiJDBC
            //modelo o retorno de Object para Peca
            Peca p = (Peca) jdbc.find(Peca.class, 12);
            if (p == null) {
                System.out.println("Não encontrou a peca 12");
            } else {
                System.out.println("Encontrou a peca: " + p.getId() + ", nome: " + p.getNome());
            }

            jdbc.fecharConexao();
        } else {
            System.out.println("nao conectou no BD via JDBC ...");

        }

    }
    
  //@Test
    public void testPersistenciaCurso() throws Exception {
        
        PersistenciaJDBC jdbc = new PersistenciaJDBC();
        
        if (jdbc.conexaoAberta()) {
            System.out.println("conectou no BD via JDBC ...");

            //chama o método find da classe PersistencaiJDBC
            //modelo o retorno de Object para Peca
            Curso c = (Curso) jdbc.find(Curso.class, 1);
            if (c == null) {
                System.out.println("Não encontrou o curso 1");
            } else {
                System.out.println("Encontrou o curso: " + c.getId());
            }

            jdbc.fecharConexao();
        } else {
            System.out.println("nao conectou no BD via JDBC ...");

        }
    }
    
    
    // @Test
    public void testPersistenciaCargo() throws Exception {
        
        PersistenciaJDBC jdbc = new PersistenciaJDBC();
        
        if (jdbc.conexaoAberta()) {
            System.out.println("conectou no BD via JDBC ...");

            //chama o método find da classe PersistencaiJDBC
            //modelo o retorno de Object para Peca
            Cargo c = (Cargo) jdbc.find(Cargo.class, 1);
            if (c == null) {
                System.out.println("Não encontrou o cargo 1");
            } else {
                System.out.println("Encontrou o cargo id: " + c.getId() + ", descrição: " + c.getDescricao());
            }

            jdbc.fecharConexao();
        } else {
            System.out.println("nao conectou no BD via JDBC ...");

        }

    }

}
