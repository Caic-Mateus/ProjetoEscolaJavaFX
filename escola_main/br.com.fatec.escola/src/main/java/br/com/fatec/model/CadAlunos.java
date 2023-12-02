/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec.model;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author caicm
 */
public class CadAlunos {
    private String cpf;
    private String nome;
    private String responsavel;
    private String sexo;
    private String email;
    private String rg;
    private String identificacao;
    private String periodo;
    private String ra;

    public CadAlunos() {
    }

    public CadAlunos(String cpf, String nome, String responsavel, String sexo, String email, String rg, String identificacao, String periodo, String ra) {
        this.cpf = cpf;
        this.nome = nome;
        this.responsavel = responsavel;
        this.sexo = sexo;
        this.email = email;
        this.rg = rg;
        this.identificacao = identificacao;
        this.periodo = periodo;
        this.ra = ra;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    
}
