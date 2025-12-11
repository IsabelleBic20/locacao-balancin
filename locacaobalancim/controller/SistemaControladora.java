package locacaobalancim.controller;

import DAO.BalancimControladora;
import DAO.ClienteControladora;
import DAO.LocacaoControladora;
import locacaobalancim.model.Balancim;
import locacaobalancim.model.Cliente;
import locacaobalancim.model.Locacao;

public class SistemaControladora {
    private final ClienteControladora cliente;
    private final BalancimControladora balancim;
    private final LocacaoControladora locacao;

    public SistemaControladora() {
        this.cliente = new ClienteControladora();
        this.balancim = new BalancimControladora();
        this.locacao = new LocacaoControladora();
    }

  
    // --- Métodos Cliente ---
    public boolean cadastrarNovoCliente(Cliente cli) {
        return cliente.cadastrarCliente(cli);
    }

    //Métodos Balancim
    public boolean cadastrarNovoBalancim(Balancim bal) {
        return balancim.cadastrarBalancim(bal);
    }

    public boolean balancimDisponivel(Balancim bal) {
        return locacao.verificarDisponibilidade(bal);
    }

    //Métodos Locação
    public boolean realizarLocacao(Locacao loc) {
        return locacao.salvarLocacao(loc);
    }

    public double calcularValorLocacao(Locacao loc) {
        return locacao.calculaValorLocacao(loc);
    }
}
