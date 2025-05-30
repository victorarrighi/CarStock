package com.carroapi.model;

public class Carro {
    private Long id;
    private String marca;
    private String modelo;
    private int preco;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public int getPreco() { return preco; }
    public void setPreco(int preco) { this.preco = preco; }
}