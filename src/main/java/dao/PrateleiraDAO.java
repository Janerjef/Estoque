package dao;


import connection.ConnectionFactory;
import model.PrateleiraModel;

import java.sql.PreparedStatement;

public class PrateleiraDAO {
    public boolean cadastrar(PrateleiraModel prateleira){
        String sql = "INSERT INTO prateleiras" +
                "(nome, descricao)" +
                "VALUES (?, ? )";

        try (var con = ConnectionFactory.getConnection()) {
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1,prateleira.getNome());
            stmt.setString(2,prateleira.getDescricao());
            stmt.executeUpdate();

            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean atualizar(PrateleiraModel prateleira){
        String sql = "UPDATE prateleiras SET" +
                "nome = ?, " +
                "descricao = ? " +
                "WHERE id = ?";


        try (var con = ConnectionFactory.getConnection()) {
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1,prateleira.getNome());
            stmt.setString(2,prateleira.getDescricao());
            stmt.setInt(3,prateleira.getId());
            stmt.executeUpdate();

            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
