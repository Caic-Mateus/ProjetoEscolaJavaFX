/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec.model;

/**
 *
 * @author Larica
 */
public class Home {
    private String onze, uma, tres, cinco, sete, dez, nome_filme;
    
    //CONTRUTORES
    public Home(){
    }
    

    public Home(String onze, String uma, String tres, String cinco, String sete, String dez, String nome_filme) {
        this.onze = onze;
        this.uma = uma;
        this.tres = tres;
        this.cinco = cinco;
        this.sete = sete;
        this.dez = dez;
        this.nome_filme = nome_filme;
    }
       
    
    //GETTERS AND SETTERS
    public String getOnze() {
        return onze;
    }

    public void setOnze(String onze) {
        this.onze = onze;
    }

    public String getUma() {
        return uma;
    }

    public void setUma(String uma) {
        this.uma = uma;
    }

    public String getTres() {
        return tres;
    }

    public void setTres(String tres) {
        this.tres = tres;
    }

    public String getCinco() {
        return cinco;
    }

    public void setCinco(String cinco) {
        this.cinco = cinco;
    }

    public String getSete() {
        return sete;
    }

    public void setSete(String sete) {
        this.sete = sete;
    }

    public String getDez() {
        return dez;
    }

    public void setDez(String dez) {
        this.dez = dez;
    }

    public String getNome_filme() {
        return nome_filme;
    }

    public void setNome_filme(String nome_filme) {
        this.nome_filme = nome_filme;
    }
    
}
