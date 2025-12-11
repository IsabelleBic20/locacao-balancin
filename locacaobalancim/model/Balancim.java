package locacaobalancim.model;

import java.io.Serializable;

public class Balancim implements Serializable {
    private int identificacao; // chave primária agora
    private double preco;
    private double tamanho;
    private double peso;
    private boolean status;

    public Balancim(int identificacao, double preco, double tamanho, double peso, boolean status){
        this.identificacao = identificacao;
        this.preco = preco;
        this.tamanho = tamanho;
        this.peso = peso;
        this.status = status;
    }

    public int getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(int identificacao) {
        this.identificacao = identificacao;
    }

    public double getPreco() {
        return preco;
    }

    public double getTamanho() {
        return tamanho;
    }

    public double getPeso() {
        return peso;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}