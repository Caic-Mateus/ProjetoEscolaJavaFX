/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec.model;

/**
 *
 * @author Leonardo
 */
public class Distribuidora {
    
    private String nome, cnpj, responsavel, email, celular, whats;

    //CONSTRUCTORES
    public Distribuidora(){
    }
    
    public Distribuidora(String nome, String cnpj, String responsavel, String email, String celular, String whats) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.responsavel = responsavel;
        this.email = email;
        this.celular = celular;
        this.whats = whats;
    }

    //GETTERS
    public String getNome() {
        return nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public String getEmail() {
        return email;
    }

    public String getCelular() {
        return celular;
    }

    public String getWhats() {
        return whats;
    }
    
    //SETTERS
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public void setWhats(String whats) {
        this.whats = whats;
    }    
}
