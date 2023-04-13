package br.edu.ifsul.cc.lpoo.om.model.dao;

import br.edu.ifsul.cc.lpoo.om.model.Cargo;
import br.edu.ifsul.cc.lpoo.om.model.Curso;
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

            PreparedStatement ps = this.con.prepareStatement("select id, cargahoraria, descricao, dt_conclusao from tb_curso where id = ?");

            ps.setInt(1, Integer.valueOf(id.toString()));

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

            }

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
                /*Calendar cData = Calendar.getInstance();
               cData.setTimeInMillis(rs.getDate("dt_conclusao").getTime());
               cur.setDt_conclusao(cData);*/
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

                PreparedStatement ps = this.con.prepareStatement("insert into tb_peca (id,fornecedor,"
                        + "nome, valor) "
                        + "values (nextval('seq_peca_id'), "
                        + "?, ?, "
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

            } else {

            }
        } else if (o instanceof Funcionario) {
            Funcionario f = (Funcionario) o;

            //utiliza data_admissao, pois será gerada pelo próprio BD.
            //nesse caso, quando não houver data_admissao sera realizada insert.
            if (f.getData_admissao() == null) {

            } else {

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

        } else if (o instanceof Funcionario) {
            Funcionario f = (Funcionario) o;
        }
    }

    @Override
    public List<Peca> listPecas() throws Exception {

         List<Peca> listagemRetorno;
        
        //select na tb_peca
        PreparedStatement ps = 
                this.con.prepareStatement("select "
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
        while(rs.next()){

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
        PreparedStatement ps = 
                this.con.prepareStatement("select "
                        + "data_admissao,"
                        + "data_demissao,"
                        + "numero_ctps,"
                        + "cpf,"
                        + "cargo "
                        + "from tb_funcionario "
                        + "order by data_admissao asc; ");
               
        //executa o comando SQL (select)
        ResultSet rs = ps.executeQuery();
        
        listagemRetorno = new ArrayList();
        
        //o método next recupera a proxima linha do resultado,
        //se exitir uma linha retorna verdadeiro
        //se nao existir uma linha retorna false.
        while(rs.next()){

            Funcionario f = new Funcionario();
            Cargo c = new Cargo();
            
            Calendar dtAdms = Calendar.getInstance();
            dtAdms.setTimeInMillis(rs.getDate("data_cadastro").getTime());                        
            f.setData_admissao(dtAdms);
            
           
            f.setData_demissao(null);
            f.setNumero_ctps("1234567");
            f.setCpf("43456787698");
            
            c.setId(3);
            c.setDescricao("Mecanico junior");
            f.setCargo(c);
            
          Curso curso = new Curso();
          curso.setDt_conclusao(null);
          curso.setDescricao("Curso de Engrenagens");
          List<Curso> listaCurso = new ArrayList();
          listaCurso.add(curso);
          
          f.setCurso(listaCurso);
            
            
            listagemRetorno.add(f);     
            
        }
        
        rs.close();//fecha o cursor do BD para essa consulta
        
        return listagemRetorno;//retorna a lista.
    }

    @Override
    public List<Curso> listCurso() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Cargo> listCargo() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Funcionario doLogin(String cpf, String senha) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
