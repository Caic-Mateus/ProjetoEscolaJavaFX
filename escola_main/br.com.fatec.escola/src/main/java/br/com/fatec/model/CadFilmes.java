package br.com.fatec.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class CadFilmes {
    private final SimpleStringProperty nome;
    private final SimpleStringProperty genero;
    private final SimpleStringProperty classificacao;
    private final SimpleStringProperty sinopse;
    private final SimpleStringProperty distribuidora;
    private final SimpleStringProperty image;
    private final SimpleIntegerProperty idFilme;
    private boolean updateImage;

    public CadFilmes() {
        this.nome = new SimpleStringProperty("");
        this.genero = new SimpleStringProperty("");
        this.classificacao = new SimpleStringProperty("");
        this.sinopse = new SimpleStringProperty("");
        this.distribuidora = new SimpleStringProperty("");
        this.image = new SimpleStringProperty("");
        this.idFilme = new SimpleIntegerProperty(0);
    }

    public CadFilmes(SimpleStringProperty nome, SimpleStringProperty genero, SimpleStringProperty classificacao, SimpleStringProperty sinopse, SimpleStringProperty distribuidora, SimpleStringProperty image, boolean updateImage, SimpleIntegerProperty idFilme) {
        this.nome = nome;
        this.genero = genero;
        this.classificacao = classificacao;
        this.sinopse = sinopse;
        this.distribuidora = distribuidora;
        this.image = image;
        this.updateImage = updateImage;
        this.idFilme = idFilme;
        
    }
    
    public void setIdFilme(int idFilme) {
        this.idFilme.set(idFilme);
    }

    public boolean isUpdateImage() {
        return updateImage;
    }

    public SimpleIntegerProperty getIdFilme() {
        return idFilme;
    }

    public void setUpdateImage(boolean updateImage) {
        this.updateImage = updateImage;
    }

    public CadFilmes(String nome, String genero, String classificacao, String sinopse, String distribuidora, String image, int idFilme) {
        this.nome = new SimpleStringProperty(nome);
        this.genero = new SimpleStringProperty(genero);
        this.classificacao = new SimpleStringProperty(classificacao);
        this.sinopse = new SimpleStringProperty(sinopse);
        this.distribuidora = new SimpleStringProperty(distribuidora);
        this.image = new SimpleStringProperty(image);
        this.idFilme = new SimpleIntegerProperty(idFilme);
    }

    public SimpleStringProperty nomeProperty() {
        return nome;
    }

    public String getNome() {
        return nome.get();
    }

    public void setNome(String nome) {
        this.nome.set(nome);
    }

    public SimpleStringProperty generoProperty() {
        return genero;
    }

    public String getGenero() {
        return genero.get();
    }

    public void setGenero(String genero) {
        this.genero.set(genero);
    }

    public SimpleStringProperty classificacaoProperty() {
        return classificacao;
    }

    public String getClassificacao() {
        return classificacao.get();
    }

    public void setClassificacao(String classificacao) {
        this.classificacao.set(classificacao);
    }

    public SimpleStringProperty sinopseProperty() {
        return sinopse;
    }

    public String getSinopse() {
        return sinopse.get();
    }

    public void setSinopse(String sinopse) {
        this.sinopse.set(sinopse);
    }

    public SimpleStringProperty distribuidoraProperty() {
        return distribuidora;
    }

    public String getDistribuidora() {
        return distribuidora.get();
    }

    public void setDistribuidora(String distribuidora) {
        this.distribuidora.set(distribuidora);
    }

    public SimpleStringProperty imageProperty() {
        return image;
    }

    public String getImage() {
        return image.get();
    }

    public void setImage(String image) {
        this.image.set(image);
    }
}
