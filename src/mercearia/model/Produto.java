package mercearia.model;

import java.sql.Timestamp;

public class Produto {
    private String nome;
    private int quantidade;
    private String tipo;
    private double preco;
    private String fornecedor;
    private Timestamp criado;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public double getPreco() {
        return preco;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setCriado(Timestamp criado) {
        this.criado = criado;
    }

    public Timestamp getCriado() {
        return criado;
    }
}
