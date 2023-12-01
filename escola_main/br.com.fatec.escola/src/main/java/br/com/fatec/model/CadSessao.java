/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec.model;

/**
 *
 * @author Larica
 */
public class CadSessao {
    private String numeroSala, codigoFilme, horarioSessao, duracaoFilme, genero, classificacao;
    
    //CONSTRUCTORES
    public CadSessao(){
    }
    
    public CadSessao(String numeroSala, String codigoFilme, String horarioSessao, String duracaoFilme, String genero, String classificacao) {
        this.numeroSala = numeroSala;
        this.codigoFilme = codigoFilme;
        this.horarioSessao = horarioSessao;
        this.duracaoFilme = duracaoFilme;
        this.genero = genero;
        this.classificacao = classificacao;
    }
    
    //GETTERS
    public String getNumeroSala() {
        return numeroSala;
    }

    public String getCodigoFilme() {
        return codigoFilme;
    }

    public String getHorarioSessao() {
        return horarioSessao;
    }

    public String getDuracaoFilme() {
        return duracaoFilme;
    }

    public String getGenero() {
        return genero;
    }

    public String getClassificacao() {
        return classificacao;
    }
    
    //SETTERS
    public void setNumeroSala(String numeroSala) {
        this.numeroSala = numeroSala;
    }

    public void setCodigoFilme(String codigoFilme) {
        this.codigoFilme = codigoFilme;
    }

    public void setHorarioSessao(String horarioSessao) {
        this.horarioSessao = horarioSessao;
    }

    public void setDuracaoFilme(String duracaoFilme) {
        this.duracaoFilme = duracaoFilme;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }
    
    @Override
    public String toString() {
        return "Sala: " + numeroSala + " - Filme: " + codigoFilme;
    }
}
