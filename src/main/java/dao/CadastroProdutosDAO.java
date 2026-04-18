package dao;

import connection.ConnectionFactory;
import model.CadastroProdutoModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CadastroProdutosDAO {


    public boolean cadastrar(CadastroProdutoModel Produto) {
        String sql = "INSERT INTO produtos" +
                "(codigo_barras,nome_produtos,fabricante,data_fabricacao,data_vencimento,quantidade,valor,total )"
                + "VALUE(?,?,?,?,?,?,?,? )";

        try (var con = ConnectionFactory.getConnection()) {

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, Produto.getCodigoBarras());
            stmt.setString(2, Produto.getNomeProduto());
            stmt.setString(3, Produto.getFabricante());
            stmt.setString(4, Produto.getDataFabricacao());
            stmt.setString(5, Produto.getDataVencimento());
            stmt.setLong(6, Produto.getQuantidade());
            stmt.setString(7, Produto.getValor());
            stmt.setString(8, Produto.getTotal());
            stmt.setString(9, Produto.getStatus());

            stmt.executeUpdate();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
        public List<CadastroProdutoModel> listar() {
            List<CadastroProdutoModel> lista = new ArrayList<>();

            String sql = "SELECT * FROM produtos";

            try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {

                while(rs.next()){
                    CadastroProdutoModel p = new CadastroProdutoModel();

                    p.setCodigoBarras(rs.getString("codigo_barras"));
                    p.setNomeProduto(rs.getString("nome_produtos"));
                    p.setFabricante(rs.getString("fabricante"));
                    p.setDataFabricacao(rs.getString("data_fabricacao"));
                    p.setQuantidade(rs.getLong("quantidade"));
                    p.setValor(rs.getString("valor"));
                    p.setTotal(rs.getString("total"));
                    p.setStatus(rs.getString("status"));

                    lista.add(p);
                }
            }catch( Exception e){
                e.printStackTrace();
            }

        return lista;
    }
}
