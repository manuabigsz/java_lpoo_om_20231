/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsul.cc.lpoo.om.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 20212pf.cc0010
 */
public class Cliente extends Pessoa{
    private String observacoes;
    private List<Veiculo> veiculo = new ArrayList<>();
    
    public Cliente(){
    
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
