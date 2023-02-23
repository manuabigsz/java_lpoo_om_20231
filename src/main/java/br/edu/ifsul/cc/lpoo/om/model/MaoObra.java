/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsul.cc.lpoo.om.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author 20212pf.cc0010
 */
@Entity

@Table(name = "tb_maoObra")
public class MaoObra implements Serializable{

    @Id

    @Column(nullable = false, length = 100)
    private Integer id;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date tempo_estimado_execucao;

    @Column(nullable = true)
    private Float valor;

    public MaoObra() {
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

    /**
     * @return the tempo_estimado_execucao
     */
    public Date getTempo_estimado_execucao() {
        return tempo_estimado_execucao;
    }

    /**
     * @param tempo_estimado_execucao the tempo_estimado_execucao to set
     */
    public void setTempo_estimado_execucao(Date tempo_estimado_execucao) {
        this.tempo_estimado_execucao = tempo_estimado_execucao;
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

}
