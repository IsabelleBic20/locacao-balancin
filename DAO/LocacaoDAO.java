/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import com.sun.jdi.connect.spi.Connection;
import java.util.ArrayList;
import java.util.List;
import locacaobalancim.model.Locacao;

/**
 *
 * @author belle
 */
public class LocacaoDAO extends AbstractDAO<Locacao>{
    private BalancimDAO balancimDAO = new BalancimDAO();

    public static List<Locacao> locacaoLista = new ArrayList<>();

  public boolean salvarLocacao(Locacao locacao) {
    if (!balancimDAO.consultaDisponibilidadeBalancim(locacao.getBalancim())) {
        System.out.println("Balancim não disponível para locação.");
        return false;
    }

    String sql = "INSERT INTO Locacao (idCliente, idBalancim, dataInicio, dataFim, valorLocacao) VALUES ("
            + locacao.getCliente().getIdCliente() + ", "
            + locacao.getBalancim().getIdentificacao() + ", '"
            + new java.sql.Date(locacao.getDataInicio().getTime()) + "', '"
            + new java.sql.Date(locacao.getDataFim().getTime()) + "', "
            + locacao.getValorLocacao() + ")";

    boolean sucesso = this.executaComando(sql);

    if (sucesso) {
        // Atualiza status do balancim para indisponível (false)
        balancimDAO.atualizaStatus(locacao.getBalancim().getIdentificacao(), false);
    }

    return sucesso;
}


    public static List<Locacao> ListarContratoLocacao(){
        return locacaoLista;
    }

   
}
