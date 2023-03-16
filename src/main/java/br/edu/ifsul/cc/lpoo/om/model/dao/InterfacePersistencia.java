/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsul.cc.lpoo.om.model.dao;

import br.edu.ifsul.cc.lpoo.om.model.Curso;
import br.edu.ifsul.cc.lpoo.om.model.Funcionario;
import br.edu.ifsul.cc.lpoo.om.model.Peca;
import java.util.List;

/**
 *
 * @author 20212PF.CC0010
 */
public interface InterfacePersistencia {
     public Boolean conexaoAberta();
    
    public void fecharConexao();
    
    public Object find(Class c, Object id) throws Exception;//select. recupera informação
    
    public void persist(Object o) throws Exception;//insert ou update.
    
    public void remover(Object o) throws Exception;//delete.
    
    public List <Peca> listPecas() throws Exception;
    
     public List <Funcionario> listFuncionario() throws Exception;
     
       public List <Curso> listCurso() throws Exception;
    
    public Funcionario doLogin(String cpf, String senha) throws Exception; //select 
}
