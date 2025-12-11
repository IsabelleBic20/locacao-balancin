/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author belle
 */

    public abstract class AbstractDAO<E> {

    Connection connection;
    Statement statement;

    protected void conecta() {

        try {
            // Cria a conexão com o banco de dados
            connection = DriverManager.getConnection("jdbc:sqlite:C:/Users/belle/LocacaoBalancim.db");
            statement = connection.createStatement();
            statement.setQueryTimeout(30);  // Espera só por 30 segundos para conectar

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    protected void desconecta() {
        try {
            if (connection != null) {
                statement.close();
                statement.getConnection().close();
                connection.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

  protected void desconecta(ResultSet rs) {
    try {
        if (rs != null) {
            if (rs.getStatement() != null) {
                if (rs.getStatement().getConnection() != null) {
                    rs.getStatement().getConnection().close();
                }
                rs.getStatement().close();
            }
            rs.close();
        }
    } catch (SQLException ex) {
        System.out.println("Erro desconecta(rs): " + ex.getMessage());
    }
}


  protected ResultSet executaConsulta(String sql) {
    try {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:C:/Users/belle/LocacaoBalancim.db");
        Statement stmt = conn.createStatement();
        return stmt.executeQuery(sql);
    } catch (SQLException ex) {
        System.out.println("Erro executaConsulta: " + ex.getMessage());
        return null;
    }
}


   protected boolean executaComando(String sql) {
    try (Connection conn = DriverManager.getConnection("jdbc:sqlite:C:/Users/belle/LocacaoBalancim.db");
         Statement stmt = conn.createStatement()) {

        stmt.execute("PRAGMA busy_timeout = 3000");
        stmt.executeUpdate(sql);
        return true;
    } catch (SQLException ex) {
        System.out.println("Erro executaComando: " + ex.getMessage());
        return false;
    }
}

   
}

