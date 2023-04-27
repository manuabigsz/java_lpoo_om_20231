package br.edu.ifsul.cc.om.test;

import br.edu.ifsul.cc.lpoo.om.model.Cargo;
import br.edu.ifsul.cc.lpoo.om.model.Curso;
import br.edu.ifsul.cc.lpoo.om.model.Funcionario;
import br.edu.ifsul.cc.lpoo.om.model.Peca;
import br.edu.ifsul.cc.lpoo.om.model.dao.PersistenciaJDBC;
import br.edu.ifsul.cc.lpoo.om.model.dao.PersistenciaJPA;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

    /*Realiza consulta de Curso*/
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

    /*Realiza consulta de Cargo*/
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

            if (!lista.isEmpty()) {

                for (Peca p : lista) {

                    System.out.println("Peca: " + p.getId() + " Nome: " + p.getNome());

                    jdbc.remover(p);
                }

            } else {

                System.out.println("Não encontrou o patente");

                Peca p = new Peca();

                p.setFornecedor("fornecedor de peca");
                p.setNome("correia");
                p.setValor(250.0f);

                jdbc.persist(p); //insert na tabela.                
                System.out.println("Cadastrou a Peca " + p.getId());

                System.out.println("Cadastrou a Peca " + p.getId());

            }

            jdbc.fecharConexao();

        } else {
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

            if (!listaF.isEmpty()) {

                for (Funcionario f : listaF) {
                    //ok
                    System.out.println("\nCPF: " + f.getCpf() + " Cargo: " + f.getCargo().getId());

                    System.out.println("\nCargo alterado:\nCPF: " + f.getCpf() + " Cargo: " + f.getCargo().getId());
                
                    for (Curso c : f.getCurso()) {

                        System.out.println("\nCurso Descricao: " + c.getDescricao());
                    }
                    Cargo car = new Cargo();
                    car.setId(20);
                    f.setCargo(car);
                    jdbc.persist(f);

                    System.out.println("\nCargo alterado:\nCPF: " + f.getCpf() + " Cargo: " + f.getCargo().getId());
                    jdbc.remover(f);
                }

            } else {
                Funcionario f = new Funcionario();
                List<Curso> listaC = jdbc.listCurso();
                Curso c = new Curso();
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
                f.setCpf("123125125");
                f.setNome("Joao");
                f.setSenha("senha123");
                f.setData_nascimento(dataNas);
                f.setCep("99876542");
                f.setComplemento("Shopping");
                f.setNumero("1234214");

                listaC.add(getCurso(jdbc));
                f.setCurso(listaC);

                //cria um novo cargo ou retorna o primeiro.
                f.setCargo(getCargo(jdbc));

                
                jdbc.persist(f); //insert na tabela.
                  System.out.println("Inseriu o Funcionario " + f.getCpf());
            }
    
            jdbc.fecharConexao();

        } else {
            System.out.println("Não conectou no BD via JDBC ...");

        }
    }

    private Cargo getCargo(PersistenciaJDBC jdbc) throws Exception {

        List<Cargo> list = jdbc.listCargo();
        if (list.isEmpty()) {
            Cargo c = new Cargo();
            c.setDescricao("Mecanico Master");
            jdbc.persist(c);

            return c;
        } else {

            return list.get(0);
        }
    }

    private Curso getCurso(PersistenciaJDBC jdbc) throws Exception {

        List<Curso> list = jdbc.listCurso();
        if (list.isEmpty()) {
            Curso c = new Curso();
            c.setDescricao("curso de mecanico");
            c.setCargahoraria(100);
            jdbc.persist(c);

            return c;
        } else {

            return list.get(0);
        }

    }

   // @Test
    public void testPersistenciaFuncionarioFind() throws Exception {

        PersistenciaJDBC jdbc = new PersistenciaJDBC();

        if (jdbc.conexaoAberta()) {
            System.out.println("conectou no BD via JDBC ...\n");

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

}

//test
/*
 
    */
