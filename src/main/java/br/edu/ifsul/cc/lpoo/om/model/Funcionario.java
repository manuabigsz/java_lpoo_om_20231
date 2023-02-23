/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsul.cc.lpoo.om.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author 20212pf.cc0010
 */
@Entity
@DiscriminatorValue("F")

public class Funcionario extends Pessoa {

    @Id
    @Column(nullable = false, length = 100)
    private String numero_ctps;
    
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar data_admissao;
    
    @Column(nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar data_demissao;

    @ManyToMany
     @JoinTable(name = "tb_funcionario_curso", joinColumns = {@JoinColumn(name = "pessoa_cpf")}, //agregacao, vai gerar uma tabela associativa.
                                       inverseJoinColumns = {@JoinColumn(name = "curso_id")})
    private List<Curso> curso = new ArrayList<>();
    
    @ManyToOne
    @JoinColumn(name = "cargo", nullable = false)
    private Cargo cargo;

    public Funcionario() {

    }

    /**
     * @return the numero_ctps
     */
    public String getNumero_ctps() {
        return numero_ctps;
    }

    /**
     * @param numero_ctps the numero_ctps to set
     */
    public void setNumero_ctps(String numero_ctps) {
        this.numero_ctps = numero_ctps;
    }

    /**
     * @return the data_admissao
     */
    public Calendar getData_admissao() {
        return data_admissao;
    }

    /**
     * @param data_admissao the data_admissao to set
     */
    public void setData_admissao(Calendar data_admissao) {
        this.data_admissao = data_admissao;
    }

    /**
     * @return the data_demissao
     */
    public Calendar getData_demissao() {
        return data_demissao;
    }

    /**
     * @param data_demissao the data_demissao to set
     */
    public void setData_demissao(Calendar data_demissao) {
        this.data_demissao = data_demissao;
    }

    /**
     * @return the curso
     */
    public List<Curso> getCurso() {
        return curso;
    }

    /**
     * @param curso the curso to set
     */
    public void setCurso(List<Curso> curso) {
        this.curso = curso;
    }

    /**
     * @return the cargo
     */
    public Cargo getCargo() {
        return cargo;
    }

    /**
     * @param cargo the cargo to set
     */
    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

}
