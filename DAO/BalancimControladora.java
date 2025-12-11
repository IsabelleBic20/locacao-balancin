package DAO;

import java.util.List;
import locacaobalancim.model.Balancim;

public class BalancimControladora {
    private final BalancimDAO dao;

    public BalancimControladora() {
        this.dao = new BalancimDAO();
    }

    public boolean cadastrarBalancim(Balancim balancim) {
        return dao.cadastrarBalancim(balancim); 
    }

    public List<Balancim> listarBalancinsDisponiveis() {
        return dao.ListaBalancimDisponiveis();
    }

    // Atualiza status do balancim pelo número de série (identificacao)
  public boolean atualizarStatus(int identificacao, boolean novoStatus) {
    return dao.atualizaStatus(identificacao, novoStatus);
}


    public boolean balancimExiste(int identificacao) {
        return dao.existeBalancim(identificacao);
    }
}
