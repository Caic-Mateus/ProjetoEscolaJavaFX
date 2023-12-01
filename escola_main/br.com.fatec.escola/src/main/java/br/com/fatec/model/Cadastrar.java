/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec.model;

/**
 *
 * @author Larica
 */
public class Cadastrar {
   
    private String nome, apelido, cpf, celular, regiao, email, senha, rsenha;

     
    //CONSTRUTORES
    public Cadastrar(){
    }
    
    public Cadastrar(String nome, String apelido, String cpf, String celular, String regiao, String amail, String senha, String rsenha) {
        this.nome = nome;
        this.apelido = apelido;
        this.cpf = cpf;
        this.celular = celular;
        this.regiao = regiao;
        this.email = amail;
        this.senha = senha;
        this.rsenha = rsenha;
    }
       
    //GETTERS AND SETTERS
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getRegiao() {
        return regiao;
    }

    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getRsenha() {
        return rsenha;
    }

    public void setRsenha(String rsenha) {
        this.rsenha = rsenha;
    }
    
}
