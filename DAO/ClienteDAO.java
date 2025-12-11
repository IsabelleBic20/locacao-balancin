/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import com.sun.jdi.connect.spi.Connection;
import java.util.ArrayList;
import java.util.List;
import locacaobalancim.model.Balancim;
import locacaobalancim.model.Cliente;
import java.sql.*;
import java.util.List;


/**
 *
 * @author belle
 */
public class ClienteDAO extends AbstractDAO<Cliente> {

    public boolean existeCliente(String cpf) {
        return buscaPorCPF(cpf) != null;
    }

    public Cliente buscaPorCPF(String cpf) {
        Cliente cli = null;
        try {
            String sql = "SELECT idCliente, nome, cpf, telefone " +
                         "FROM Cliente WHERE cpf = '" + cpf + "'";
            ResultSet resultSetCliente = this.executaConsulta(sql);
            //System.out.println(sql);
            cli = converteResultParaObjeto(resultSetCliente);

            desconecta(resultSetCliente);

        } catch (SQLException ex) {
            System.err.println("Erro CPF Não Cadastrado: " + ex.getMessage());
        } finally {
            this.desconecta();
        }

        return cli;
    }

 public boolean cadastrarCliente(Cliente cli) {
    List<String> cpfs = buscaTodosCPFs();

    if (cpfs.contains(cli.getCpf())) {
        System.out.println("Cliente com esse CPF já existe.");
        return false;
    }

    System.out.println("Cadastrando Cliente");
    String sql = "INSERT INTO Cliente (nome, cpf, telefone) VALUES ('"
            + cli.getNome() + "', '"
            + cli.getCpf() + "', '"
            + cli.getTelefone() + "')";

    System.out.println(sql);
    return this.executaComando(sql);
}


    private Cliente converteResultParaObjeto(ResultSet rs) throws SQLException {
        if (rs != null && rs.next()) {  
            int idCliente = rs.getInt("idCliente");
            String nome = rs.getString("nome");
            String cpf = rs.getString("cpf");
            String telefone = rs.getString("telefone");

            Cliente cli = new Cliente(nome, cpf, telefone);
            cli.setIdCliente(idCliente);
            return cli;
        }
        return null;
    }

    
    public List<String> buscaTodosCPFs() {
    List<String> cpfs = new ArrayList<>();

    try {
        String sql = "SELECT cpf FROM Cliente";
        ResultSet rs = this.executaConsulta(sql);

        while (rs != null && rs.next()) {
            String cpf = rs.getString("cpf");
            cpfs.add(cpf);
        }

        desconecta(rs);
    } catch (SQLException ex) {
        System.out.println("Erro ao buscar CPFs: " + ex.getMessage());
    } finally {
        this.desconecta();
    }

    return cpfs;
}

}
