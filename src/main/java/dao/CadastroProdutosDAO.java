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
                "(codigo_barras,nome_produtos,fabricante,marca,data_fabricacao,data_vencimento,quantidade,valor,total,status,prateleira_id,estoque_minimo, tipo)"
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?, ? )";
        // executa um comando em sql
        try (var con = ConnectionFactory.getConnection()) {
            //chama a a função do connection factory para abrir uma conexão

            PreparedStatement stmt = con.prepareStatement(sql);
            //
            stmt.setString(1, Produto.getCodigoBarras());
            // troca os valores desconhecidos do comando pelos dados obtidos pela classe Model

            stmt.setString(2, Produto.getNomeProduto());
            stmt.setString(3, Produto.getFabricante());
            stmt.setString(4, Produto.getMarca());
            stmt.setString(5, Produto.getDataFabricacao());
            stmt.setString(6, Produto.getDataVencimento());
            stmt.setLong(7, Produto.getQuantidade());
            stmt.setString(8, Produto.getValor());
            stmt.setString(9, Produto.getTotal());
            stmt.setString(10, Produto.getStatus());
            stmt.setString(11, Produto.getPrateleira());
            stmt.setInt(12, Produto.getEstoqueMínimo());
            stmt.setString(13, Produto.getTipo());
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
                "marca = ?, " +
                "data_fabricacao = ?, " +
                "data_vencimento = ?, " +
                "quantidade = ?, " +
                "valor = ?, " +
                "total = ?, " +
                "status = ?, " +
                "prateleira_id = ?," +
                "estoque_minimo = ? " +
                "WHERE id = ?";

        try (var con = ConnectionFactory.getConnection()) {
            //chama a a função do connection factory para abrir uma conexão

            PreparedStatement stmt = con.prepareStatement(sql);
            //
            stmt.setString(1, Produto.getCodigoBarras());
            // troca os valores desconhecidos do comando pelos dados obtidos pela classe Model

            stmt.setString(2, Produto.getNomeProduto());
            stmt.setString(3, Produto.getFabricante());
            stmt.setString(4,Produto.getMarca());
            stmt.setString(5, Produto.getDataFabricacao());
            stmt.setString(6, Produto.getDataVencimento());
            stmt.setLong(7, Produto.getQuantidade());
            stmt.setString(8, Produto.getValor());
            stmt.setString(9, Produto.getTotal());
            stmt.setString(10, Produto.getStatus());
            stmt.setString(11, Produto.getPrateleira());
            stmt.setInt(12, Produto.getEstoqueMínimo() );
            stmt.setInt(13,Produto.getID());

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

    public CadastroProdutoModel buscarPorCodigoBarras(String codigoBarras){
        String sql = "SELECT * FROM produtos WHERE codigo_barras = ? LIMIT 1";
        try (var con = ConnectionFactory.getConnection()){
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, codigoBarras);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                CadastroProdutoModel p = new CadastroProdutoModel();
                p.setId(rs.getInt("id"));
                p.setCodigoBarras(rs.getString("codigo_barras"));
                p.setNomeProduto(rs.getString("nome_produtos"));
                p.setFabricante(rs.getString("fabricante"));
                p.setMarca(rs.getString("marca"));
                p.setDataFabricacao(rs.getString("data_fabricacao"));
                p.setDataVencimento(rs.getString("data_vencimento"));
                p.setQuantidade(rs.getLong("quantidade"));
                p.setValor(rs.getString("valor"));
                p.setTotal(rs.getString("total"));
                p.setStatus(rs.getString("status"));
                p.setPrateleira(rs.getString("prateleira_id"));
                p.setEstoqueMínimo(rs.getInt("estoque_minimo"));

                return p;
            }
        }catch (Exception e ){
            e.printStackTrace();
        }
        return null;
    }

        public List<CadastroProdutoModel> listarComFiltro(String nome, String tipo, String data, String id, String registro){
            List<CadastroProdutoModel> lista = new ArrayList<>();

            if(registro == null || registro.isEmpty()){
                registro = "saldo";
            } // para essa validação acontecer antes de montar a consulta e o tipo/registro ser definido

            StringBuilder sql = new StringBuilder("SELECT * FROM produtos WHERE 1=1 AND tipo = ? ");


            if(nome != null && !nome.isEmpty()){
                sql.append(" AND LOWER( nome_produtos ) LIKE ?");
            }
            if(tipo != null && !tipo.isEmpty()){
                sql.append(" AND status = ?");
            }
            if(data != null && !data.isEmpty()){
                sql.append(" AND data_fabricacao = ?");
            }
            if (id != null && !id.isEmpty()) {
                sql.append(" AND id = ?");
            }



            try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql.toString())){

                int index = 1;


                stmt.setString(index++, registro);
                if(nome != null && !nome.isEmpty()){
                    stmt.setString(index++, "%" + nome.toLowerCase()+ "$");
                }
                if(tipo != null && !tipo.isEmpty()){
                    stmt.setString(index++, "%" );
                }
                if(data != null && !data.isEmpty()){
                    stmt.setString(index++, data);
                }
                if (id != null && !id.isEmpty()) {
                    stmt.setString(index++, id);
                }


                ResultSet rs = stmt.executeQuery();

                while(rs.next()){
                    CadastroProdutoModel p = new CadastroProdutoModel();
                    p.setId(rs.getInt("id"));
                    p.setCodigoBarras(rs.getString("codigo_barras"));
                    p.setNomeProduto(rs.getString("nome_produtos"));
                    p.setFabricante(rs.getString("fabricante"));
                    p.setMarca(rs.getString("marca"));
                    p.setDataFabricacao(rs.getString("data_fabricacao"));
                    p.setDataVencimento(rs.getString("data_vencimento"));
                    p.setQuantidade(rs.getLong("quantidade"));
                    p.setValor(rs.getString("valor"));
                    p.setTotal(rs.getString("total"));
                    p.setStatus(rs.getString("status"));
                    p.setPrateleira(rs.getString("prateleira_id"));
                    p.setEstoqueMínimo(rs.getInt("estoque_minimo"));

                    lista.add(p);

                }

            }catch( Exception e){
                e.printStackTrace();
            }

        return lista;

    }
}
