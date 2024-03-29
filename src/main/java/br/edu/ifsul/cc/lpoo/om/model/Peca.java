/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsul.cc.lpoo.om.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author 20212pf.cc0010
 */
@Entity

@Table(name = "tb_peca")
@NamedQueries({@NamedQuery(name="Peca.getbyid", query="select p from Peca p order by p.id asc")})
public class Peca implements Serializable{

    @Id
    @SequenceGenerator(name = "seq_peca", sequenceName = "seq_peca_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_peca", strategy = GenerationType.SEQUENCE) 
    private Integer id;
    
    @Column(nullable = false, length = 100)
    private String nome;
    
    @Column(nullable = false, precision = 2)
    private Float valor;
    
    @Column(nullable = true, length = 200)
    private String fornecedor;

    public Peca() {
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
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
     * @return the valor
     */
    public Float getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(Float valor) {
        this.valor = valor;
    }

    /**
     * @return the fornecedor
     */
    public String getFornecedor() {
        return fornecedor;
    }

    /**
     * @param fornecedor the fornecedor to set
     */
    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }
    
    @Override
    public String toString() {
        return 
                "\tId='" + id + '\'' +
                "\t Nome='" + nome + '\'' +
                "\t Fornecedor=" + fornecedor +
                "\t Valor='" + valor + '\'' ;
    }

}
