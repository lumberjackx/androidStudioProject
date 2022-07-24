package com.example.trabalho_final2.Models;

public class Formando
{
    private int Id;
    private int numero;
    private String nome;
    private String telefone;
    private int idade;
    private String email;

    public Formando(int id, int numero, String nome, String telefone, int idade, String email)
    {
        this.Id = id;
        this.numero = numero;
        this.nome = nome;
        this.telefone = telefone;
        this.idade = idade;
        this.email = email;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
