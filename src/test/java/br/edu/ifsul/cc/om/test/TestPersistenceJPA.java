/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsul.cc.om.test;

import br.edu.ifsul.cc.lpoo.om.model.Cargo;
import br.edu.ifsul.cc.lpoo.om.model.Curso;
import br.edu.ifsul.cc.lpoo.om.model.Funcionario;
import br.edu.ifsul.cc.lpoo.om.model.Peca;
import br.edu.ifsul.cc.lpoo.om.model.dao.PersistenciaJPA;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;

/**
 *
 * @author 20212pf.cc0010
 */
public class TestPersistenceJPA {

    //@Test
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

    //@Test
    public void testPersistenciaPeca() throws Exception {

        PersistenciaJPA persistencia = new PersistenciaJPA();
        if (persistencia.conexaoAberta()) {
            System.out.println("testPersistenciaPeca");

            Peca p = new Peca();
            
            p.setFornecedor("Texas");
            p.setNome("BC348");
            p.setValor(3.5f);

            persistencia.persist(p);

            System.out.println("Inseriu a peca id=" + p.getId());
            persistencia.fecharConexao();

            System.out.println("fechou a conexao com o BD via JPA");

        } else {
            System.out.println("Nao abriu a conexao com o BD via JPA");
        }

    }

    /*
        Exercício 2:
         Passo 1: encontrar a peça id=1
         Passo 2: caso encontre remove-la
         Passo 3: caso não encontre criar nova peça.
     */
    //@Test
    public void testPersistenciaEncontraId() throws Exception {

        PersistenciaJPA persistencia = new PersistenciaJPA();
        if (persistencia.conexaoAberta()) {
            System.out.println("testPersistenciaEncontraId:");
            Peca p = (Peca) persistencia.find(Peca.class, 1);
            if (p == null) {
                System.out.println("nao encontrou o id =1");
                p = new Peca();
                p.setFornecedor("fornecedor de teste");
                p.setNome("biela");
                p.setValor(100f);
                persistencia.persist(p);

            } else {
                System.out.println("encontrou o id=1 para endereco " + p.getId());
                persistencia.remover(p);
                System.out.println("Removeu a peca id=" + p.getId());
            }
            persistencia.fecharConexao();
        } else {
            System.out.println("Nao abriu a conexao com o BD via JPA");
        }
    }

    /*
        Exercício 3:
        Passo 1: recuperar a lista de peças.
        Passo 2: se a lista.size() > 0 listar e remover.
        Passo 3: se a lista.size() == 0 inserir duas Peças.
     */
    //@Test
    public void testPersistenciaListPeca() throws Exception {

        PersistenciaJPA persistencia = new PersistenciaJPA();
        if (persistencia.conexaoAberta()) {
            System.out.println("testPersistenciaListPeca:");

            List<Peca> list = persistencia.listPecas();
            if (!list.isEmpty()) {

                for (Peca p : list) {

                    System.out.println("Peca \n" + p);
                }
            }
            else if (list.size() > 0) {
                for (Peca p : list) {

                    System.out.println("Peca \n" + p);
                    persistencia.remover(p);
                }

            } else {
                Peca p = new Peca();

                p.setFornecedor("Texas");
                p.setNome("BC348");
                p.setValor(3.5f);
                //encaminhar o e
                persistencia.persist(p);

                Peca p2 = new Peca();

                p2.setFornecedor("Texas");
                p2.setNome("BC548");
                p2.setValor(4.5f);
                //encaminhar o e
                persistencia.persist(p2);

            }
        } else {
            System.out.println("Nao abriu a conexao com o BD via JPA");
        }
    }

    /*
        Exercício 4:
            Passo 1: listar dos os funcionários com os seus respectivos curso.
            Passo 2: se a lista.size() > 0 listar e remover o funcionário.
            Passo 3: se a lista.size() == 0 inserir um funcionário e associar cursos..
     */
   // @Test
    public void testPersistenciaListFuncionario() throws Exception {

        PersistenciaJPA persistencia = new PersistenciaJPA();
        if (persistencia.conexaoAberta()) {
            System.out.println("testPersistenciaListFuncionario:");

            List<Funcionario> list = persistencia.listFuncionario();
            if (!list.isEmpty()) {

                for (Funcionario f : list) {

                    System.out.println("Funcionario \nNumero CTPS: " + f.getNumero_ctps()
                            + "\t Data Admissao: " + f.getData_admissao()
                            + "\t Data demissão: " + f.getData_demissao()
                            + "\t CPF: " + f.getCpf() + "\tCEP: " + f.getCep()
                            + "\tNome: " + f.getNome() + "\t Senha: " + f.getSenha()
                            + "\t Data nascimento: " + f.getData_nascimento()
                            + "\t Complemento: " + f.getComplemento() + "\t Cargo: " + f.getCargo().getDescricao() 
                            + "Cursos: " + f.getCurso());

                }
            }
            if (list.size() > 0) {
                for (Funcionario f : list) {
                    System.out.println("Funcionario \nNumero CTPS: " + f.getNumero_ctps()
                            + "\t Data Admissao: " + f.getData_admissao()
                            + "\t Data demissão: " + f.getData_demissao()
                            + "\t CPF: " + f.getCpf() + "\tCEP: " + f.getCep()
                            + "\tNome: " + f.getNome() + "\t Senha: " + f.getSenha()
                            + "\t Data nascimento: " + f.getData_nascimento()
                            + "\t Complemento: " + f.getComplemento() 
                            + "\t Cargo: " + f.getCargo().getDescricao() +  "Cursos: " + f.getCurso());
                    persistencia.remover(f);

                }

            } else {
                Funcionario f = new Funcionario();
                List<Curso> listaC = persistencia.listCurso();
                 List<Cargo> listaCar = persistencia.listCargo();
                Curso c = new Curso();
                Cargo car = new Cargo();
                Calendar dataAdm = null;
                Calendar dataNas = null;
                Calendar dataC = null;

                try {
                    String dataAdmi = "01/05/2020";
                    String dataNasci = "02/05/1999";
                    String dataCurso = "25/05/2021";
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyy");

                    dataAdm = Calendar.getInstance();
                    dataAdm.setTimeInMillis(simpleDateFormat.parse(dataAdmi).getTime());

                    dataNas = Calendar.getInstance();
                    dataNas.setTimeInMillis(simpleDateFormat.parse(dataNasci).getTime());

                    dataC = Calendar.getInstance();
                    dataC.setTimeInMillis(simpleDateFormat.parse(dataCurso).getTime());

                } catch (Exception e) {
                    e.printStackTrace();
                }

                f.setNumero_ctps("1234");
                f.setData_admissao(dataAdm);
                f.getData_demissao();
                f.setCpf("12345678912");
                f.setNome("Joao");
                f.setSenha("senha123");
                f.setData_nascimento(dataNas);
                f.setCep("99876542");
                f.setComplemento("Shopping");
                f.setNumero("1234214");
               
                
                 listaC.add(getCurso(persistencia));
                f.setCurso(listaC);
                
              
                //cria um novo cargo ou retorna o primeiro.
                 f.setCargo(getCargo(persistencia));
                
                persistencia.persist(f);

            }
        } else {
            System.out.println("Nao abriu a conexao com o BD via JPA");
        }
    }
    
    private Cargo getCargo(PersistenciaJPA jpa) throws Exception{
    
         List<Cargo> list = jpa.listCargo();
        if(list.isEmpty()){
            Cargo c = new Cargo();
            c.setDescricao("Mecanico Master");
            jpa.persist(c);
            
            return c;
        }else{
            
            return list.get(0);
        }
    }

     private Curso getCurso(PersistenciaJPA jpa) throws Exception {
        
        List<Curso> list = jpa.listCurso();
        if(list.isEmpty()){
            Curso c = new Curso();
            c.setDescricao("curso de mecanico");
            c.setCargahoraria(100);
            jpa.persist(c);
            
            return c;
        }else{
            
            return list.get(0);
        }
        
    }
    
    
}

