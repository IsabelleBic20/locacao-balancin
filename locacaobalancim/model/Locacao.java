package locacaobalancim.model;

import java.io.Serializable;
import java.util.Date;

public class Locacao implements Serializable {
    private int idLocacao;
    private Cliente cliente;
    private Balancim balancim;
    private Date dataInicio;
    private Date dataFim;
    private double valorLocacao;

    public Locacao(int idLocacao, Cliente cliente, Balancim balancim, Date dataInicio, Date dataFim, double valorLocacao) {
        this.idLocacao = idLocacao;
        this.cliente = cliente;
        this.balancim = balancim;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.valorLocacao = valorLocacao;
        calculaValorLocacao();

    }

    // Getters
    public int getIdLocacao() {
        return idLocacao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Balancim getBalancim() {
        return balancim;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public double getValorLocacao() {
        return valorLocacao;
    }

    // Setters
    public void setIdLocacao(int idLocacao) {
        this.idLocacao = idLocacao;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setBalancim(Balancim balancim) {
        this.balancim = balancim;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public void setValorLocacao(double valorLocacao) {
        this.valorLocacao = valorLocacao;
    }
    
    public void calculaValorLocacao() {
    long diffMillis = dataFim.getTime() - dataInicio.getTime();
    long dias = diffMillis / (1000 * 60 * 60 * 24);
    if (dias == 0) {
        dias = 1; // considerar no mínimo 1 dia
    }
    this.valorLocacao = dias * balancim.getPreco();
}

}
