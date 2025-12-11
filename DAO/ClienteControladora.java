/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.util.List;
import locacaobalancim.model.Cliente;

/**
 *
 * @author belle
 */
public class ClienteControladora {
     private final ClienteDAO dao;

    public ClienteControladora() {
        this.dao = new ClienteDAO();
    }

    public boolean cadastrarCliente(Cliente cliente) {
        return dao.cadastrarCliente(cliente);
    }

 
    public boolean clienteExiste(String cpf) {
        return dao.existeCliente(cpf);
    }
}
