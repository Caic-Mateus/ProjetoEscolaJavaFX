/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec.model;

import java.util.List;

/**
 *
 * @author Larica
 */
public class Poltronas {
    private List<String> poltronasSelecionadas;
    
    //CONTRUTORES
    public Poltronas(){
    }
    
    public Poltronas(List<String> poltronasSelecionadas) {
        this.poltronasSelecionadas = poltronasSelecionadas;
    }
    
    
    //GETTERS AND SETTERS
    public List<String> getPoltronasSelecionadas() {
        return poltronasSelecionadas;
    }

    public void setPoltronasSelecionadas(List<String> poltronasSelecionadas) {
        this.poltronasSelecionadas = poltronasSelecionadas;
    }
}
