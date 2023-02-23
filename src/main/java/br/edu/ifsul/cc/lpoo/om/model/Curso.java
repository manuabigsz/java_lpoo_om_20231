/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsul.cc.lpoo.om.model;

import java.io.Serializable;
import java.util.Calendar;
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
@Table(name = "tb_curso")
public class Curso implements Serializable{

    @Id
    @Column(nullable = false, length = 100)
    private String placa;

    @Column(nullable = false)
    private String modelo;

    @Column(nullable = false)
    private Integer ano;

    @Column(nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar data_ultimo_servico;

    public Curso() {
    }

    /**
     * @return the placa
     */
    public String getPlaca() {
        return placa;
    }

    /**
     * @param placa the placa to set
     */
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    /**
     * @return the modelo
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * @return the ano
     */
    public Integer getAno() {
        return ano;
    }

    /**
     * @param ano the ano to set
     */
    public void setAno(Integer ano) {
        this.ano = ano;
    }

    /**
     * @return the data_ultimo_servico
     */
    public Calendar getData_ultimo_servico() {
        return data_ultimo_servico;
    }

    /**
     * @param data_ultimo_servico the data_ultimo_servico to set
     */
    public void setData_ultimo_servico(Calendar data_ultimo_servico) {
        this.data_ultimo_servico = data_ultimo_servico;
    }

}
