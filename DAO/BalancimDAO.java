package DAO;

import locacaobalancim.model.Balancim;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BalancimDAO extends AbstractDAO<Balancim> {

    private static final String URL = "jdbc:sqlite:C:/Users/belle/LocacaoBalancim.db";

    // Verifica se existe Balancim pela identificação
    public boolean existeBalancim(int identificacao) {
        String sql = "SELECT 1 FROM Balancim WHERE identificacao = ? LIMIT 1";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, identificacao);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next(); // true se encontrou
            }

        } catch (SQLException ex) {
            System.out.println("Erro existeBalancim: " + ex.getMessage());
            return false;
        }
    }

    // Cadastra novo Balancim
   public boolean cadastrarBalancim(Balancim bal) {
    if (buscarPorIdentificacao(bal.getIdentificacao()) == null) {
        String sql = "INSERT INTO Balancim (identificacao, preco, tamanho, peso, status) VALUES ("
                + bal.getIdentificacao() + ", "
                + bal.getPreco() + ", "
                + bal.getTamanho() + ", "
                + bal.getPeso() + ", "
                + (bal.isStatus() ? 1 : 0) + ")";

        return executaComando(sql);
    } else {
        System.out.println("Balancim já cadastrado.");
        return false;
    }
}

  public Balancim buscarPorIdentificacao(int identificacao) {
    String sql = "SELECT * FROM Balancim WHERE identificacao = ?";

    try (Connection conn = DriverManager.getConnection(URL);
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, identificacao);

        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return new Balancim(
                    rs.getInt("identificacao"),
                    rs.getDouble("preco"),
                    rs.getDouble("tamanho"),
                    rs.getDouble("peso"),
                    rs.getInt("status") == 1
                );
            }
        }
    } catch (SQLException e) {
        System.out.println("Erro buscarPorIdentificacao: " + e.getMessage());
    }

    return null;
}


    // Buscar todos 
    public List<Balancim> ListaBalancimDisponiveis() {
        List<Balancim> lista = new ArrayList<>();
        String sql = "SELECT idBalancim, identificacao, preco, tamanho, peso, status FROM Balancim where status =1" ;

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Balancim bal = new Balancim(
                    rs.getInt("identificacao"),
                    rs.getDouble("preco"),
                    rs.getDouble("tamanho"),
                    rs.getDouble("peso"),
                    rs.getInt("status") == 1
                );
                lista.add(bal);
            }

        } catch (SQLException ex) {
            System.out.println("Erro buscar balancins disponiveis: " + ex.getMessage());
        }

        return lista;
    }
  public boolean consultaDisponibilidadeBalancim(Balancim bal){
        if(!bal.isStatus()){
            System.out.println("Balancim Indisponível. A seguir, lista de Balancins Disponiveis: ");
            ListaBalancimDisponiveis();
            return false;
        }else{
            return true;
        }
    }
    
    public boolean atualizaStatus(int identificacao, boolean status) {
    String sql = "UPDATE Balancim SET status = ? WHERE identificacao = ?";

    try (Connection conn = DriverManager.getConnection(URL);
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, status ? 1 : 0);
        ps.setInt(2, identificacao);
        int linhasAfetadas = ps.executeUpdate();

        return linhasAfetadas > 0;  // true se atualizou algum registro

    } catch (SQLException e) {
        System.out.println("Erro ao atualizar status do balancim: " + e.getMessage());
        return false;
    }
}


   

}
