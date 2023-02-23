/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsul.cc.lpoo.om.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author 20212pf.cc0010
 */
@Entity
@DiscriminatorValue("C")

public class Cliente extends Pessoa {

    @Id
    @Column(nullable = true, length = 100)
    private String observacoes;

    @ManyToMany
    @JoinTable(name = "tb_cliente_veiculos", joinColumns = {
        @JoinColumn(name = "cliente_nome")}, //agregacao, vai gerar uma tabela associativa.
            inverseJoinColumns = {
                @JoinColumn(name = "veiculo_placa")})
    private List<Veiculo> veiculo = new ArrayList<>();

    public Cliente() {

    }

    /**
     * @return the observacoes
     */
    public String getObservacoes() {
        return observacoes;
    }

    /**
     * @param observacoes the observacoes to set
     */
    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    /**
     * @return the veiculo
     */
    public List<Veiculo> getVeiculo() {
        return veiculo;
    }

    /**
     * @param veiculo the veiculo to set
     */
    public void setVeiculo(List<Veiculo> veiculo) {
        this.veiculo = veiculo;
    }

}
