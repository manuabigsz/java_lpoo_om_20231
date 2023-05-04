package br.edu.ifsul.cc.lpoo.om.model.dao;

import br.edu.ifsul.cc.lpoo.om.model.Cargo;
import br.edu.ifsul.cc.lpoo.om.model.Curso;
import br.edu.ifsul.cc.lpoo.om.model.Equipe;
import br.edu.ifsul.cc.lpoo.om.model.Funcionario;
import br.edu.ifsul.cc.lpoo.om.model.Peca;
import br.edu.ifsul.cc.lpoo.om.model.Pessoa;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author 20212pf.cc0010
 * @data 300/2023
 *
 * Classe que implementará InterfacePersistencia e utilização de JDBC para a
 * persistencia dos dados.
 *
 */
public class PersistenciaJDBC implements InterfacePersistencia {

    private final String DRIVER = "org.postgresql.Driver";
    private final String USER = "postgres";
    private final String SENHA = "postgres"; //senha do postgress
    public static final String URL = "jdbc:postgresql://localhost:5432/db_om_2023_1";
    private Connection con = null; // é semelhante ao entity, gerenciador de entidades - será o atributo que fará uma conexão com o banco

    public PersistenciaJDBC() throws Exception {
        Class.forName(DRIVER); //carregamento do driver postgresql em tempo de execução
        System.out.println("Tentando estabelecer conexao JDBC com : " + URL + " ...");

        this.con = (Connection) DriverManager.getConnection(URL, USER, SENHA);
    }

    @Override
    public Boolean conexaoAberta() {
        try {
            if (con != null) {
                return !con.isClosed();//verifica se a conexao está aberta
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public void fecharConexao() {
        try {
            this.con.close();//fecha a conexao.
            System.out.println("Fechou conexao JDBC");
        } catch (SQLException e) {
            e.printStackTrace();//gera uma pilha de erro na saida.
        }
    }

    @Override
    public Object find(Class c, Object id) throws Exception {
        if (c == Peca.class) {
            //select na tb_peca
            PreparedStatement ps = this.con.prepareStatement("select " + " id, " // a string que ele recebe será um comando SQL
                    + "fornecedor, " + "nome, " + "valor " + "from tb_peca "
                    + "where id = ?");//parametros sã o definidos pelo ponto de interrogação

            //o 1 represetna o primeiro parametro(?)
            ps.setInt(1, Integer.valueOf(id.toString()));

            //executa o comando SQL(select)
            ResultSet rs = ps.executeQuery();

            //next pega a proxima linha do resultado
            //se existir uma linha retorna verdadeiro
            //se não, retorna falso
            if (rs.next()) {
                Peca p = new Peca();

                p.setId(rs.getInt("id"));//recupera pelo nome da coluna
                p.setNome(rs.getString("nome"));
                p.setFornecedor(rs.getString("fornecedor"));
                p.setValor(rs.getFloat("valor"));

                rs.close(); //fecha o curso do bd para essa consulta

                return p; // retorna o objetio do tipo peca
            }

        } else if (c == Funcionario.class) {
            //select na tb_pessoa e tb_funcionario
            PreparedStatement ps
                    = this.con.prepareStatement("select "
                            + "f.data_admissao, "
                            + "f.data_demissao,"
                            + "f.numero_ctps, "
                            + "f.cpf, "
                            + "f.cargo,"
                            + "p.tipo,p.cpf,"
                            + "p.cep,"
                            + "p.complemento,"
                            + "p.data_nascimento, "
                            + "p.nome,"
                            + "p.numero,"
                            + "p.senha\n"
                            + "from tb_funcionario f, tb_pessoa p "
                            + "where f.cpf=p.cpf;");

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                Funcionario f = new Funcionario();
                f.setNumero_ctps(rs.getString("numero_ctps"));
                f.setNome(rs.getString("nome"));

                if (rs.getDate("data_admissao") != null) {

                    Calendar cData = Calendar.getInstance();
                    cData.setTimeInMillis(rs.getDate("data_admissao").getTime());
                    f.setData_admissao(cData);
                }

                if (rs.getDate("data_demissao") != null) {
                    Calendar cDataFinal = Calendar.getInstance();
                    cDataFinal.setTimeInMillis(rs.getDate("data_demissao").getTime());
                    f.setData_demissao(cDataFinal);
                }

                f.setNumero_ctps(rs.getString("numero_ctps"));

                f.setCpf(rs.getString("cpf"));

                Cargo car = new Cargo();
                car.setDescricao(rs.getString("descricao"));
                f.setCargo(car);

                f.setCep(rs.getString("cep"));
                f.setComplemento(rs.getString("complemento"));

                if (rs.getDate("data_nascimento") != null) {
                    Calendar cDataNasc = Calendar.getInstance();
                    cDataNasc.setTimeInMillis(rs.getDate("data_nascimento").getTime());
                    f.setData_nascimento(cDataNasc);
                }

                f.setNome(rs.getString("nome"));

                f.setNumero(rs.getString("numero"));

                f.setSenha(rs.getString("senha"));
                //..demais campos.

                PreparedStatement psCursos
                        = this.con.prepareStatement("select c.descricao, c.id "
                                + "from tb_funcionario f, tb_curso c, tb_funcionario_curso fc"
                                + "where f.cpf=fc.pessoa_cpf and c.id=fc.curso_id");

                ResultSet rsCursos = psCursos.executeQuery();
                List<Curso> listCursos = new ArrayList<>();

                listCurso();
                while (rsCursos.next()) {
                    Curso curso = new Curso();
                    curso.setDescricao(rsCursos.getString("descricao"));
                    curso.setId(rsCursos.getInt("id"));

                    listCursos.add(curso);
                }

                f.setCurso(listCursos);

                rsCursos.close();
                return f;
            }

            rs.close();

        } else if (c == Curso.class) {
            //select na tb_curso

            PreparedStatement ps = this.con.prepareStatement("select id, cargahoraria, descricao, dt_conclusao from tb_curso where id = ?");

            ps.setInt(1, Integer.valueOf(id.toString()));

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Curso cur = new Curso();

                cur.setId(rs.getInt("id"));//recupera pelo nome da coluna
                cur.setCargahoraria(rs.getInt("cargahoraria"));
                cur.setDescricao(rs.getString("descricao"));

                //Conversão de Calendar para data:
                if (rs.getDate("dt_conclusao") != null) {
                    Calendar cData = Calendar.getInstance();
                    cData.setTimeInMillis(rs.getDate("dt_conclusao").getTime());
                    cur.setDt_conclusao(cData);
                }
                //c.setDt_conclusao(rs.getDate("dt_conclusao"));
                rs.close(); //fecha o curso do bd para essa consulta

                return cur; // retorna o objetio do tipo peca
            }

        } else if (c == Cargo.class) {
            //select na tb_cargo

            PreparedStatement ps = this.con.prepareStatement("select id, cargahoraria, descricao, dt_conclusao from tb_curso where id = ?");

            ps.setInt(1, Integer.valueOf(id.toString()));

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Cargo car = new Cargo();

                car.setId(rs.getInt("id"));//recupera pelo nome da coluna
                car.setDescricao(rs.getString("descricao"));

                rs.close(); //fecha o curso do bd para essa consulta

                return car; // retorna o objetio do tipo peca
            }
        }
        return null;
    }

    @Override
    public void persist(Object o) throws Exception {//recebe object
        if (o instanceof Peca) { //descobrir instancia do objeto, se o for uma peça
            //faz conversão(casting "modela") de object para peça
            Peca p = (Peca) o;
            //testa para descobrir se existe informação na chave primaria
            if (p.getId() == null) {
                //insert into tb_peca...

                PreparedStatement ps = this.con.prepareStatement("insert into tb_peca (id,"
                        + "fornecedor,"
                        + "nome, "
                        + "valor) "
                        + "values (nextval('seq_peca_id'), "
                        + "?, "
                        + "?, "
                        + "?);");
                //seta parametros.
                ps.setString(1, p.getFornecedor()); //seta fornecedor, do tipo vchar, com indice 1, e pega o forncedor
                ps.setString(2, p.getNome());
                ps.setFloat(3, p.getValor());

                //executa o insert
                ps.execute();

                //fecha o cursor
                ps.close();

            } else {
                //update tb_peca set..

                PreparedStatement ps = this.con.prepareStatement("update tb_peca "
                        + "set fornecedor = ?, "
                        + "nome= ?, "
                        + "valor = ? "
                        + "where id=?");

                //seta parametros.
                ps.setString(1, p.getFornecedor()); //seta fornecedor, do tipo vchar, com indice 1, e pega o forncedor
                ps.setString(2, p.getNome());
                ps.setFloat(3, p.getValor());
                ps.setInt(4, p.getId());

                //executa o insert
                ps.execute();

                //fecha o cursor
                ps.close();
            }
        } else if (o instanceof Cargo) {
            Cargo c = (Cargo) o;
            if (c.getId() == null) {
                //insert into tb_peca...

                PreparedStatement ps = this.con.prepareStatement("insert into tb_cargo ("
                        + "descricao) "
                        + "values (nextval('seq_cargo'), "
                        + "?);");
                //seta parametros.
                ps.setString(1, c.getDescricao()); //seta fornecedor, do tipo vchar, com indice 1, e pega o forncedor

                //executa o insert
                ps.execute();

                //fecha o cursor
                ps.close();
            } else {
                //update tb_peca set..

                PreparedStatement ps = this.con.prepareStatement("update tb_cargo "
                        + "set descricao = ?, "
                        + "where id=?");
                ps.setString(1, c.getDescricao()); //seta fornecedor, do tipo vchar, com indice 1, e pega o forncedor

                //seta parametros.
                ps.setString(1, c.getDescricao()); //seta fornecedor, do tipo vchar, com indice 1, e pega o forncedor
                //executa o insert
                ps.execute();

                //fecha o cursor
                ps.close();

            }
        } else if (o instanceof Funcionario) {
            Funcionario f = (Funcionario) o;
            //utilizando a data_admissao, pois será gerada pelo próprio BD.
            //nesse caso, quando não houver data_admissao realizará insert.
            if(f.getData_admissao() == null){
                
                PreparedStatement ps = this.con.prepareStatement("insert into tb_pessoa (cpf, nome, numero, senha, complemento, tipo, cep, data_nascimento) values "
                                                                 + "("
                                                                 + "?, "
                                                                 + "?, "
                                                                 + "?, "
                                                                 + "?, "
                                                                 + "?, "
                                                                 + "?, "
                                                                 + "?, "
                                                                 + "? "
                                                                 + ")");
                ps.setString(1, f.getCpf());
                ps.setString(2, f.getNome());
                ps.setString(3, f.getNumero());
                ps.setString(4, f.getSenha());
                ps.setString(5, f.getComplemento());
                ps.setString(6, "F");
                ps.setString(7, f.getCep());
                ps.setDate(8, new java.sql.Date(f.getData_nascimento().getTimeInMillis()));
                ps.execute();
                ps.close();
                
                ps = this.con.prepareStatement("insert into tb_funcionario (data_admissao, numero_ctps, cpf, cargo_id) values "
                                                                 + "("
                                                                 + "now(), "
                                                                 + "?, "
                                                                 + "?, "
                                                                 + "? "                                                              
                                                                 + ")");
                ps.setString(1, f.getNumero_ctps());
                ps.setString(2, f.getCpf());
                ps.setInt(3, f.getCargo().getId());
                ps.execute();
                
                ps.close();}
            else{
                 PreparedStatement ps = this.con.prepareStatement("update tb_pessoa set "
                         + "cpf=?,"
                         + "nome=?, "
                         + "numero=?, "
                         + "senha=?, "
                         + "complemento=?, "
                         + "tipo=?,"
                         + " cep=?, "
                         + "data_nascimento=? where cpf=?");
                 
                ps.setString(1, f.getCpf());
                ps.setString(2, f.getNome());
                ps.setString(3, f.getNumero());
                ps.setString(4, f.getSenha());
                ps.setString(5, f.getComplemento());
                ps.setString(6, "F");
                ps.setString(7, f.getCep());
                ps.setDate(8, new java.sql.Date(f.getData_nascimento().getTimeInMillis()));
                ps.execute();
                ps.close();
                
                ps = this.con.prepareStatement("update tb_funcionario set"
                        + "data_admissao=?,"
                        + "numero_ctps=?,"
                        + " cpf=?,"
                        + " cargo_id=? where cpf=? ");
                
                ps.setString(1, f.getNumero_ctps());
                ps.setString(2, f.getCpf());
                ps.setInt(3, f.getCargo().getId());
                ps.execute();
            }

        } else if (o instanceof Equipe) {
            Equipe e = (Equipe) o;
            if (e.getId() == null) {
                //insert into tb_peca...

                PreparedStatement ps = this.con.prepareStatement("insert into tb_equipe (id,"
                        + "especialidades,"
                        + "nome) "
                        + "values (nextval('seq_equipe_id'), "
                        + "?,?);");
                //seta parametros.
                ps.setString(1, e.getEspecialidades()); //seta fornecedor, do tipo vchar, com indice 1, e pega o forncedor
                ps.setString(2, e.getNome());
                //executa o insert
                ps.execute();
              
               /* //fecha o cursor
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    e.setId(rs.getInt("id"));
                }

                if (e.getFuncionario() != null && !e.getFuncionario().isEmpty()) {

                    for (Funcionario f : e.getFuncionario()) {

                        PreparedStatement ps2 = this.con.prepareStatement("insert into tb_equipe_funcionario (equipe_id, funcionario_cpf) "
                                + "values (?, ?) ");

                        ps2.setInt(1, e.getId());
                        ps2.setString(2, f.getCpf());
                        ps2.execute();

                        ps2.close();

                    }
                    
                    rs.close();
                }*/
                
                ps.close();
            } else {
                //update tb_peca set..

                PreparedStatement ps = this.con.prepareStatement("update tb_equipe "
                        + "set especialidades = ?, "
                        + "set nome = ?"
                        + "where equipe_id=?");
                ps.setString(1, e.getEspecialidades()); //seta fornecedor, do tipo vchar, com indice 1, e pega o forncedor

                //seta parametros.
                ps.setString(1, e.getNome()); //seta fornecedor, do tipo vchar, com indice 1, e pega o forncedor
                //executa o insert
                ps.execute();
               
                
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    e.setId(rs.getInt("id"));
                }

                if (e.getFuncionario() != null && !e.getFuncionario().isEmpty()) {

                    for (Funcionario f : e.getFuncionario()) {

                        PreparedStatement ps2 = this.con.prepareStatement("update tb_equipe_funcionario set "
                                + "equipe_id=?, funcionario_cpf=?) "
                                + "where equipe_id= ?; ");

                        ps2.setInt(1, e.getId());
                        ps2.setString(2, f.getCpf());
                        ps2.execute();

                        ps2.close();

                    }

                    rs.close();

                    //fecha o cursor
                    ps.close();

                }
            }
        }
    }

    @Override
    public void remover(Object o) throws Exception {
        if (o instanceof Peca) {
            Peca p = (Peca) o;

            PreparedStatement ps = this.con.prepareStatement("delete from tb_peca "
                    + "where id = ?;");

            ps.setInt(1, p.getId());
            //executa
            ps.execute();
            //fecha o cursor
            ps.close();

        } else if (o instanceof Cargo) {
            Cargo c = (Cargo) o;
            PreparedStatement ps = this.con.prepareStatement("delete from tb_cargo "
                    + "where id = ?;");
            ps.setInt(1, c.getId());
            //executa
            ps.execute();
            //fecha o cursor
            ps.close();

        } else if (o instanceof Funcionario) {
            Funcionario f = (Funcionario) o;
            PreparedStatement psC = this.con.prepareStatement("delete from tb_funcionario_curso "
                    + "where pessoa_cpf = ?;");
            psC.setString(1, f.getCpf());
            psC.execute();

            PreparedStatement ps = this.con.prepareStatement("delete from tb_funcionario "
                    + "where cpf = ?;");
            ps.setString(1, f.getCpf());
            ps.execute();

            PreparedStatement psP = this.con.prepareStatement("delete from tb_pessoa "
                    + "where cpf = ?;");
            psP.setString(1, f.getCpf());
            psP.execute();

            //executa
            //fecha o cursor
            ps.close();

            System.out.println("Removeu o funcionario");
        } else if (o instanceof Equipe) {
            Equipe e = (Equipe) o;
            PreparedStatement ps = this.con.prepareStatement("delete from tb_equipe_funcionario where equipe_id = ?; ");
          
            ps.setInt(1, e.getId());
            
            ps.execute();
            
            ps.close();
            
            ps = this.con.prepareStatement("delete from tb_equipe where id = ?; ");
          
            ps.setInt(1, e.getId());
            
            ps.execute();
            
            ps.close();

        }
    }

    @Override
    public List<Peca> listPecas() throws Exception {

        List<Peca> listagemRetorno;

        //select na tb_peca
        PreparedStatement ps
                = this.con.prepareStatement("select "
                        + "id, "
                        + "fornecedor, "
                        + "nome, "
                        + "valor "
                        + "from tb_peca "
                        + "order by id asc ");

        //executa o comando SQL (select)
        ResultSet rs = ps.executeQuery();

        listagemRetorno = new ArrayList();

        //o método next recupera a proxima linha do resultado,
        //se exitir uma linha retorna verdadeiro
        //se nao existir uma linha retorna false.
        while (rs.next()) {

            Peca p = new Peca();
            p.setId(rs.getInt("id"));//recupera pelo nome da coluna
            p.setNome(rs.getString("nome"));
            p.setFornecedor(rs.getString("fornecedor"));
            p.setValor(rs.getFloat("valor"));

            listagemRetorno.add(p);

        }

        rs.close();//fecha o cursor do BD para essa consulta

        return listagemRetorno;//retorna a lista.

    }

    @Override
    public List<Funcionario> listFuncionario() throws Exception {
        List<Funcionario> listagemRetorno;

        //select na tb_peca
        PreparedStatement ps
                = this.con.prepareStatement("select "
                        + "f.data_admissao, "
                        + "f.data_demissao, "
                        + "f.numero_ctps, "
                        + "f.cpf,"
                        + "c.descricao as cargo,"
                        + "p.tipo,"
                        + "p.cpf,"
                        + "p.cep,"
                        + "p.complemento,"
                        + "p.data_nascimento,"
                        + "p.nome,"
                        + "p.numero,"
                        + "p.senha "
                        + "from tb_funcionario f, tb_pessoa p, tb_cargo c where f.cpf=p.cpf and f.cargo = c.id;");

        //executa o comando SQL (select)
        ResultSet rs = ps.executeQuery();

        listagemRetorno = new ArrayList();

        //o método next recupera a proxima linha do resultado,
        //se exitir uma linha retorna verdadeiro
        //se nao existir uma linha retorna false.
        while (rs.next()) {

            Funcionario f = new Funcionario();

            if (rs.getDate("data_admissao") != null) {

                Calendar cData = Calendar.getInstance();
                cData.setTimeInMillis(rs.getDate("data_admissao").getTime());
                f.setData_admissao(cData);
            }

            if (rs.getDate("data_demissao") != null) {
                Calendar cDataFinal = Calendar.getInstance();
                cDataFinal.setTimeInMillis(rs.getDate("data_demissao").getTime());
                f.setData_demissao(cDataFinal);
            }

            f.setNumero_ctps(rs.getString("numero_ctps"));

            f.setCpf(rs.getString("cpf"));

            Cargo car = new Cargo();
            car.setDescricao(rs.getString("cargo"));

            f.setCargo(car);

            f.setCep(rs.getString("cep"));
            f.setComplemento(rs.getString("complemento"));

            if (rs.getDate("data_nascimento") != null) {
                Calendar cDataNasc = Calendar.getInstance();
                cDataNasc.setTimeInMillis(rs.getDate("data_nascimento").getTime());
                f.setData_nascimento(cDataNasc);
            }

            f.setNome(rs.getString("nome"));

            f.setNumero(rs.getString("numero"));

            f.setSenha(rs.getString("senha"));

            PreparedStatement psCursos
                    = this.con.prepareStatement("select c.descricao, c.id "
                            + "from tb_funcionario f, tb_curso c, tb_funcionario_curso fc "
                            + "where f.cpf=fc.pessoa_cpf and c.id=fc.curso_id");

            ResultSet rsCursos = psCursos.executeQuery();
            List<Curso> listCursos = new ArrayList();
            while (rsCursos.next()) {
                //criar um objeto do tipo curso
                //setar a descricao e o id do curso
                //adicionar o curso na lista
                Curso crs = new Curso();
                crs.setDescricao(rsCursos.getString("descricao"));
                crs.setId(rsCursos.getInt("id"));//recupera pelo nome da coluna

                listCursos.add(crs);

            }
            rsCursos.close();

            f.setCurso(listCursos);
            listagemRetorno.add(f);
        }

        rs.close();//fecha o cursor do BD para essa consulta

        return listagemRetorno;//retorna a lista.

    }

    @Override
    public List<Curso> listCurso() throws Exception {
        List<Curso> listagemRetorno;

        //select na tb_peca
        PreparedStatement ps
                = this.con.prepareStatement("select "
                        + "id, "
                        + "cargahoraria, "
                        + "descricao, "
                        + "dt_conclusao "
                        + "from tb_curso;");

        //executa o comando SQL (select)
        ResultSet rs = ps.executeQuery();

        listagemRetorno = new ArrayList();

        //o método next recupera a proxima linha do resultado,
        //se exitir uma linha retorna verdadeiro
        //se nao existir uma linha retorna false.
        while (rs.next()) {

            Curso c = new Curso();

            c.setId(rs.getInt("id"));//recupera pelo nome da coluna
            c.setDescricao(rs.getString("descricao"));

            if (rs.getDate("dt_conclusao") != null) {

                Calendar cData = Calendar.getInstance();
                cData.setTimeInMillis(rs.getDate("dt_conclusao").getTime());
                c.setDt_conclusao(cData);
            }

            listagemRetorno.add(c);
        }

        rs.close();//fecha o cursor do BD para essa consulta

        return listagemRetorno;//retorna a lista.
    }

    @Override
    public List<Cargo> listCargo() throws Exception {
        
        List<Cargo> listagemRetorno;

        //select na tb_peca
        PreparedStatement ps
                = this.con.prepareStatement("select "
                        + "descricao "
                        + "from tb_cargo");

        //executa o comando SQL (select)
        ResultSet rs = ps.executeQuery();

        listagemRetorno = new ArrayList();

        //o método next recupera a proxima linha do resultado,
        //se exitir uma linha retorna verdadeiro
        //se nao existir uma linha retorna false.
        while (rs.next()) {

            Cargo c = new Cargo();

            c.setDescricao(rs.getString("descricao"));

            listagemRetorno.add(c);
        }

        rs.close();//fecha o cursor do BD para essa consulta

        return listagemRetorno;//retorna a lista.

    }

    @Override
    public Funcionario doLogin(String cpf, String senha) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   /* @Override
    public List<Equipe> listEquipe() throws Exception {
        List<Equipe> listagemRetornoEquipe;

        //select na tb_equipe
        PreparedStatement psEquipe
                = this.con.prepareStatement("select "
                        + "e.id,e.especialidades,e.nome,"
                        + "f.data_admissao,f.data_demissao, f.numero_ctps,"
                        + "f.cpf, c.descricao as cargo, "
                        + "p.tipo, p.cpf, p.cep,"
                        + "p.complemento,p.data_nascimento, p.nome, p.numero,"
                        + "p.senha, "
                        + "ef.equipe_id, ef.funcionario_cpf "
                        + "from tb_equipe e, tb_funcionario f, tb_pessoa p, "
                        + "tb_cargo c, tb_equipe_funcionario ef "
                        + "where e.id=ef.equipe_id and f.cpf = ef.funcionario_cpf;");

        //executa o comando SQL (select)
        ResultSet rsEquipe = psEquipe.executeQuery();

        listagemRetornoEquipe = new ArrayList();

        while (rsEquipe.next()) {

            Equipe e = new Equipe();
            
            e.setId(rsEquipe.getInt("id"));
            e.setEspecialidades(rsEquipe.getString("especialidades"));

            PreparedStatement psFunc
                    = this.con.prepareStatement("select f.cpf, p.nome, c.descricao as cargo from tb_equipe e, tb_funcionario f, tb_pessoa p, tb_cargo c, "
                            + "tb_equipe_funcionario ef where e.id=ef.equipe_id and f.cpf = ef.funcionario_cpf;");

            ResultSet rsFunc = psFunc.executeQuery();
            List<Funcionario> listFunc = new ArrayList();
            while (rsFunc.next()) {
                //criar um objeto do tipo curso
                //setar a descricao e o id do curso
                //adicionar o curso na lista
                Funcionario f = new Funcionario();
                f.setNome(rsFunc.getString("nome"));
                f.setCpf(rsFunc.getString("cpf"));
                Cargo car = new Cargo();
                car.setDescricao(rsFunc.getString("cargo"));
                f.setCargo(car);
                listFunc.add(f);

            }
            rsFunc.close();

            e.setFuncionario(listFunc);
            listagemRetornoEquipe.add(e);
            
        }
        rsEquipe.close();//fecha o cursor do BD para essa consulta
        
       
        return listagemRetornoEquipe;//retorna a lista.
    }*/
    
    @Override
    public List<Equipe> listEquipe() throws Exception {
        
        List<Equipe> listagemRetorno;
        
        //select na tb_equipe
        PreparedStatement ps = 
                this.con.prepareStatement("select id, especialidades, nome "
                                        + "from tb_equipe order by id asc;");
               
        //executa o comando SQL (select)
        ResultSet rs = ps.executeQuery();
        listagemRetorno = new ArrayList();
        while(rs.next()){
            
            Equipe e = new Equipe();
            e.setId(rs.getInt("id"));
            e.setNome(rs.getString("nome"));
            e.setEspecialidades(rs.getString("especialidades"));
            
              PreparedStatement ps2 = 
                this.con.prepareStatement("select equipe_id, funcionario_cpf from tb_equipe_funcionario where equipe_id = ? ");
              ps2.setInt(1, e.getId());
              
              ResultSet rs2 = ps2.executeQuery();
              List<Funcionario> listfunc = new ArrayList();
              while(rs2.next()){
                  Funcionario f= new Funcionario();
                  Cargo c = new Cargo();
                  //c.setId();
                  f.setCargo(c);
                  
                  f.setCpf(rs2.getString("funcionario_cpf"));                
                  listfunc.add(f);
              }
              e.setFuncionario(listfunc);
              
              rs2.close();
            
            //select equipe_id, funcionario_cpf from tb_equipe_funcionario where equipe_id = 1;
            //recuperar os funcionarios da equipe.
            
            listagemRetorno.add(e);
        }
        rs.close();
        
        return listagemRetorno;
        
        
    }

}
