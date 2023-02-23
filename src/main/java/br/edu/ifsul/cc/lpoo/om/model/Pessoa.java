/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsul.cc.lpoo.om.model;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author 20212pf.cc0010
 */
@Entity
@Table(name="tb_pessoa")

//definição da herança e estratégia
@Inheritance(strategy = InheritanceType.JOINED)

//definição da coluna discriminatória
@DiscriminatorColumn(name="Tipo")

public abstract class Pessoa implements Serializable{
    @Id
    
    @Column(nullable = false, length = 100)
    private String cpf;
    
    @Column(nullable = false)
    private String nome;
    
    @Column(nullable = false)
    private String senha;
    
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar data_nascimento;
    
    @Column(nullable = false)
    private String cep;
    
    @Column(nullable = true)
    private String complemento;
    
    public Pessoa(){
    
    }
    
    public Pessoa(String cpf,  String nome, String senha, Calendar data_nascimento, String cep, String complemento){
        this.cpf=cpf;
        this.nome=nome;
        this.senha=senha;
        this.data_nascimento=data_nascimento;
        this.cep=cep;
        this.complemento=complemento;
        
    }

    /**
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * @return the data_nascimento
     */
    public Calendar getData_nascimento() {
        return data_nascimento;
    }

    /**
     * @param data_nascimento the data_nascimento to set
     */
    public void setData_nascimento(Calendar data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    /**
     * @return the cep
     */
    public String getCep() {
        return cep;
    }

    /**
     * @param cep the cep to set
     */
    public void setCep(String cep) {
        this.cep = cep;
    }

    /**
     * @return the complemento
     */
    public String getComplemento() {
        return complemento;
    }

    /**
     * @param complemento the complemento to set
     */
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
    
    
    
}
