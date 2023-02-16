/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsul.cc.lpoo.om.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author 20212pf.cc0010
 */
public class Servico {
    private Integer id;
    private Float valor;
    private Calendar data_inicio;
    private Calendar data_fim;
    
    private StatusServico status;
    private Orcamento orcamento;
    private Equipe equipe;
    private List<Pagamento> listaPagamento = new ArrayList<>();
    
}
