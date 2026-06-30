package dao;

import connection.ConnectionFactory;
import model.CadastroProdutoModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CadastroProdutosDAO {
// classe para se comunicar diretamente em SQL


    public boolean cadastrar(CadastroProdutoModel Produto) {
        String sql = "INSERT INTO produtos" +
                "(codigo_barras,nome_produtos,fabricante,data_fabricacao,data_vencimento,quantidade,valor,total,status,prateleira,estoque_mínimo)"
                + "VALUES (?,?,?,?,?,?,?,?,?,?,? )";
        // executa um comando em sql
        try (var con = ConnectionFactory.getConnection()) {
            //chama a a função do connection factory para abrir uma conexão

            PreparedStatement stmt = con.prepareStatement(sql);
            //
            stmt.setString(1, Produto.getCodigoBarras());
            // troca os valores desconhecidos do comando pelos dados obtidos pela classe Model

            stmt.setString(2, Produto.getNomeProduto());
            stmt.setString(3, Produto.getFabricante());
            stmt.setString(4, Produto.getDataFabricacao());
            stmt.setString(5, Produto.getDataVencimento());
            stmt.setLong(6, Produto.getQuantidade());
            stmt.setString(7, Produto.getValor());
            stmt.setString(8, Produto.getTotal());
            stmt.setString(9, Produto.getStatus());
            stmt.setString(10, Produto.getPrateleira());
            stmt.setInt(11, Produto.getEstoqueMínimo());
            stmt.executeUpdate();
            // atualiza no banco
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean atualizar (CadastroProdutoModel Produto){
        String sql = "UPDATE produtos SET " +
                "codigo_barras = ?, " +
                "nome_produtos = ?, " +
                "fabricante = ?, " +
                "data_fabricacao = ?, " +
                "data_vencimento = ?, " +
                "quantidade = ?, " +
                "valor = ?, " +
                "total = ?, " +
                "status = ?, " +
                "prateleira = ?," +
                "estoque_mínimo = ?" +
                "WHERE id = ?";

        try (var con = ConnectionFactory.getConnection()) {
            //chama a a função do connection factory para abrir uma conexão

            PreparedStatement stmt = con.prepareStatement(sql);
            //
            stmt.setString(1, Produto.getCodigoBarras());
            // troca os valores desconhecidos do comando pelos dados obtidos pela classe Model

            stmt.setString(2, Produto.getNomeProduto());
            stmt.setString(3, Produto.getFabricante());
            stmt.setString(4, Produto.getDataFabricacao());
            stmt.setString(5, Produto.getDataVencimento());
            stmt.setLong(6, Produto.getQuantidade());
            stmt.setString(7, Produto.getValor());
            stmt.setString(8, Produto.getTotal());
            stmt.setString(9, Produto.getStatus());
            stmt.setString(10, Produto.getPrateleira());
            stmt.setInt(11, Produto.getEstoqueMínimo() );
            stmt.setInt(12,Produto.getID());

            stmt.executeUpdate();
            // atualiza no banco
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
    public boolean deletar  ( int id){
       String sql = "DELETE FROM produtos " +
               "where id = ?";


        try (var con = ConnectionFactory.getConnection()) {
            //chama a a função do connection factory para abrir uma conexão
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

        public List<CadastroProdutoModel> listarComFiltro(String nome, String tipo, String data) {
            List<CadastroProdutoModel> lista = new ArrayList<>();

            StringBuilder sql = new StringBuilder("SELECT * FROM produtos WHERE 1=1");


            if(nome != null && !nome.isEmpty()){
                sql.append(" AND LOWER( nome_produto ) LIKE ?");
            }
            if(tipo != null && tipo.isEmpty()){
                sql.append(" AND status = ?");
            }
            if(data != null && data.isEmpty()){
                sql.append(" AND data_fabricacao = ?");
            }

            try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql.toString())){

                int index = 1;

                if(nome != null && !nome.isEmpty()){
                    stmt.setString(index++, "%" + nome.toLowerCase()+ "$");
                }
                if(tipo != null && !tipo.isEmpty()){
                    stmt.setString(index++, "%" );
                }
                if(data != null && !data.isEmpty()){
                    stmt.setString(index++, data);
                }


                ResultSet rs = stmt.executeQuery();

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
                    p.setPrateleira(rs.getString("prateleira"));
                    p.setEstoqueMínimo(rs.getInt("EstoqueMínimo"));

                    lista.add(p);
                }
            }catch( Exception e){
                e.printStackTrace();
            }

        return lista;
    }
}
