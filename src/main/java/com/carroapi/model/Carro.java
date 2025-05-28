package com.carroapi.model;

public class Carro {
    private Long id;
    private String modelo;
    private String marca;
    private int preco;

    public Carro() {
    }

    public Carro(Long id, String modelo, String marca, int preco) {
        this.id = id;
        this.modelo = modelo;
        this.marca = marca;
        this.preco = preco;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public int getPreco() { return preco; }
    public void setPreco(int preco) { this.preco = preco; }
}
