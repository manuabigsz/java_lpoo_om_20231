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
@Table(name = "tb_cargo")
@NamedQueries({@NamedQuery(name="Cargo.getbyid", query="select car from Cargo car order by car.id asc")})
public class Cargo implements Serializable{

    @Id
    @SequenceGenerator(name = "seq_cargo", sequenceName = "seq_cargo_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_cargo", strategy = GenerationType.SEQUENCE) 
    private Integer id;

    @Column(nullable = false, length = 200)
    private String descricao;

    public Cargo() {

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
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

     @Override
    public String toString() {
        return 
                "\tId='" + id + '\'' +
                "\t Descrição='" + descricao + '\'';
    }
}
