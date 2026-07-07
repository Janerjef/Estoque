package dao;


import connection.ConnectionFactory;
import model.PrateleiraModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PrateleiraDAO {
    public boolean cadastrar(PrateleiraModel prateleira){
        String sql = "INSERT INTO prateleiras" +
                "(nome, descricao) " +
                "VALUES (?, ? ) ";

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
        String sql = "UPDATE prateleiras SET " +
                "nome = ?, " +
                "descricao = ? " +
                "WHERE id = ?";


        try (var con = ConnectionFactory.getConnection()) {
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1,prateleira.getNome());
            stmt.setString(2,prateleira.getDescricao());
            stmt.setInt(3,prateleira.getPrateleiraId());
            stmt.executeUpdate();

            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<PrateleiraModel> listar(){
        List<PrateleiraModel> lista = new ArrayList<>();
        String sql = "SELECT * FROM prateleiras";

        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery() ){

            while (rs.next()){
                PrateleiraModel p = new PrateleiraModel();
                p.setPrateleiraId(rs.getInt("prateleiraId"));
                p.setNome(rs.getString("nome"));
                p.setDescricao(rs.getString("descricao"));
                lista.add(p);
            }

        }catch (Exception e) {
            e.printStackTrace();

        } return lista;

    }

}
