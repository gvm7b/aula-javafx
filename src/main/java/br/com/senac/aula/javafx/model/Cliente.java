package br.com.senac.aula.javafx.model;

public class Cliente {
    private int id;
    private String nome;
    private String documento;
    private String rg;

    public Cliente() {
    }

    public Cliente(int id, String nome, String documento, String rg) {
        this.id = id;
        this.nome = nome;
        this.documento = documento;
        this.rg = rg;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
