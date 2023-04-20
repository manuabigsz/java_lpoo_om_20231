package br.edu.ifsul.cc.om.test;

import br.edu.ifsul.cc.lpoo.om.model.Cargo;
import br.edu.ifsul.cc.lpoo.om.model.Curso;
import br.edu.ifsul.cc.lpoo.om.model.Funcionario;
import br.edu.ifsul.cc.lpoo.om.model.Peca;
import br.edu.ifsul.cc.lpoo.om.model.dao.PersistenciaJDBC;
import java.util.List;
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
            Peca p = (Peca) jdbc.find(Peca.class, 15);

            if (p == null) {
                System.out.println("Não encontrou a peca ");

                p = new Peca();

                p.setFornecedor("fornecedor de peca");
                p.setNome("correia");
                p.setValor(250.0f);

                jdbc.persist(p);

                System.out.println("Inseriu a peça " + p.getId());

            } else {
                System.out.println("Encontrou a peca: " + p.getId() + ", nome: " + p.getNome());
                p.setNome("nome alterado");
                jdbc.persist(p);
                
                System.out.println("Alterou a peça: " + p.getId());
                
                System.out.println("Removendo a peca: " + p.getId());
                jdbc.remover(p);
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

    //@Test
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
    
    
    /*
    Atividade prática 13_04_23
    recuperar lista de peças
    se a lista nao estiver vazia, percorrer e imprimir o id
    de cada peca e remover.
    se a lista estiver vazia, criar uma peça
    */
   //@Test
    public void testPersistenciaListaPeca() throws Exception {

        PersistenciaJDBC jdbc = new PersistenciaJDBC();

        if (jdbc.conexaoAberta()) {
            
             List<Peca> lista = jdbc.listPecas();
            
            if(!lista.isEmpty()){
            
                for(Peca p : lista){

                    System.out.println("Peca: "+p.getId()+" Nome: "+p.getNome());
                                        
                    jdbc.remover(p);
                }

            }else{
                
                System.out.println("Não encontrou o patente");
                
                Peca p = new Peca();
                
                p.setFornecedor("fornecedor de peca");
                p.setNome("correia");
                p.setValor(250.0f);
                
                jdbc.persist(p); //insert na tabela.                
                System.out.println("Cadastrou a Peca "+p.getId());

                
           
                System.out.println("Cadastrou a Peca "+p.getId());
                
            }
            
            jdbc.fecharConexao();
         
            
        }else{
              System.out.println("Não conectou no BD via JDBC ...");

            
        }
    
    }
    
    @Test
    public void testPersistenciaListFuncionario() throws Exception {
    
    /*
       ##### Exercicio de Preparação para a Avaliação ####
      1) Recuperar a lista de Funcionarios com seus respectivos cursos.
      2) Se a lista não for vazia, imprimir cpf e cargo de cada funcionario 
            e os seus respectivos cursos (descrição), alterá-lo (cargo) e remove-lo.
      3) Se a lista estiver vazia, cadastrar um novo funcionario e associar um curso.
    */
    
    PersistenciaJDBC jdbc = new PersistenciaJDBC();

        if (jdbc.conexaoAberta()) {
            
             List<Funcionario> listaF = jdbc.listFuncionario();
            
            if(!listaF.isEmpty()){
            
                for(Funcionario f : listaF){

                    System.out.println("\nCPF: "+f.getCpf()+" Cargo: "+f.getCargo());
                                        
                    jdbc.remover(f);
                    
                     for(Curso c: f.getCurso()){
                         
                    System.out.println("\nCurso Descricao: "+c.getDescricao());
                     }
                }
               

            }else{
                System.out.println("Não encontrou funcionarios");
                
            }
            
            jdbc.fecharConexao();
         
            
        }else{
              System.out.println("Não conectou no BD via JDBC ...");

            
        }
    }
}

    
   
    
  
//test
/*
  public void testPersistenciaFuncionarioFind() throws Exception {

         PersistenciaJDBC jdbc = new PersistenciaJDBC();

        if (jdbc.conexaoAberta()) {
            System.out.println("conectou no BD via JDBC ...");

            //chama o método find da classe PersistencaiJDBC
            //modelo o retorno de Object para Peca
            Funcionario f = (Funcionario) jdbc.find(Funcionario.class, "123125125");
            if (f == null) {
                System.out.println("Não encontrou o funcionario com cpf informado");
            } else {
                System.out.println("Encontrou o funcionario: " + f.getCpf());
            }

            jdbc.fecharConexao();
        } else {
            System.out.println("nao conectou no BD via JDBC ...");

        }

    }
    */