package DAO;

import DAO.BalancimDAO;
import DAO.LocacaoDAO;
import locacaobalancim.model.Locacao;
import locacaobalancim.model.Balancim;
import locacaobalancim.model.Cliente;
import java.util.Date;

public class LocacaoControladora {
    private LocacaoDAO locacaoDAO = new LocacaoDAO();
    private BalancimDAO balancimDAO = new BalancimDAO();

      // Calcula e retorna o valor da locação
    public double calculaValorLocacao(Locacao locacao) {
        locacao.calculaValorLocacao();
        return locacao.getValorLocacao();
    }

    // Verifica se o balancim está disponível para locação
    public boolean verificarDisponibilidade(Balancim balancim) {
        return balancimDAO.consultaDisponibilidadeBalancim(balancim);
    }

    // Salva a locação se o balancim estiver disponível
    public boolean salvarLocacao(Locacao locacao) {
        if (!verificarDisponibilidade(locacao.getBalancim())) {
            System.out.println("Balancim indisponível para locação.");
            return false;
        }

        locacao.calculaValorLocacao(); 
        boolean sucesso = locacaoDAO.salvarLocacao(locacao);

        if (sucesso) {
            System.out.println("Locação salva com sucesso.");
        } else {
            System.out.println("Erro ao salvar locação.");
        }

        return sucesso;
    }
}
