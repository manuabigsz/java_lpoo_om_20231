/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsul.cc.lpoo.om.model.dao;

import br.edu.ifsul.cc.lpoo.om.model.Cargo;
import br.edu.ifsul.cc.lpoo.om.model.Curso;
import br.edu.ifsul.cc.lpoo.om.model.Equipe;
import br.edu.ifsul.cc.lpoo.om.model.Funcionario;
import br.edu.ifsul.cc.lpoo.om.model.Peca;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author 20212PF.CC0010
 */
public class PersistenciaJPA implements InterfacePersistencia{
    
    public EntityManagerFactory factory;    //fabrica de gerenciadores de entidades
    public EntityManager entity;            //gerenciador de entidades JPA

    
    public PersistenciaJPA(){
        
        //parametro: é o nome da unidade de persistencia (Persistence Unit)
        factory = Persistence.createEntityManagerFactory("br.edu.ifsul.cc.lpoo.om_java_lpoo_om_20231_jar_1.0-SNAPSHOTPU");
        entity = factory.createEntityManager();
    }

    @Override
    public Boolean conexaoAberta() {
        
        return entity.isOpen(); 
    }

    @Override
    public void fecharConexao() {
        
        entity.close();  
    }

    @Override
    public Object find(Class c, Object id) throws Exception {
        
       return entity.find(c, id); // encontra determinado registro
    }

    @Override
    public void persist(Object o) throws Exception {
        entity.getTransaction().begin(); // abrir transação
        entity.persist(o); // realiaz insert ou update
        entity.getTransaction().commit(); // comita a transação (comando sql)
     
    }

    @Override
    public void remover(Object o) throws Exception {
      entity.getTransaction().begin(); // abrir transação
        entity.persist(o);
        entity.getTransaction().commit();
     
    }
    
   @Override
    public List <Peca> listPecas() throws Exception {
    
        return entity.createNamedQuery("Peca.getbyid").getResultList();    }

    @Override
    public Funcionario doLogin(String cpf, String senha,String tipo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Funcionario> listFuncionario() throws Exception {
      return entity.createNamedQuery("Funcionario.getbyid").getResultList();
        
    }

    @Override
    public List<Curso> listCurso() throws Exception {
         return entity.createNamedQuery("Curso.getbyid").getResultList();
    }

    @Override
    public List<Cargo> listCargo() throws Exception {
       return entity.createNamedQuery("Cargo.getbyid").getResultList();
    }

    @Override
    public List<Equipe> listEquipe() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    


   
    
}
