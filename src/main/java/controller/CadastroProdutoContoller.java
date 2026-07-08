package controller;

import dao.CadastroProdutosDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.CadastroProdutoModel;

import java.io.IOException;

@WebServlet("/cadastroProdutos")
public class CadastroProdutoContoller extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        CadastroProdutoModel produto = new CadastroProdutoModel();

        produto.setCodigoBarras(request.getParameter("codigoBarras"));
        produto.setNomeProduto(request.getParameter("nomeProduto"));
        produto.setFabricante(request.getParameter("fabricante"));
        produto.setMarca(request.getParameter("marca"));
        produto.setDataFabricacao(request.getParameter("dataFabricacao"));
        produto.setDataVencimento(request.getParameter("dataVencimento"));
        produto.setQuantidade(Long.parseLong(request.getParameter("quantidade")));
        produto.setValor(request.getParameter("valor"));
        produto.setTotal(request.getParameter("total"));
        produto.setStatus(request.getParameter("status"));
        produto.setPrateleira(request.getParameter("prateleira_id"));
        produto.setEstoqueMínimo(Integer.parseInt(request.getParameter("EstoqueMínimo")));

        CadastroProdutosDAO dao = new CadastroProdutosDAO();

        CadastroProdutoModel existente = dao.buscarPorCodigoBarras(produto.getCodigoBarras());

        if(existente != null){
            double valor = Double.parseDouble(existente.getValor());
            long novaQuantidade;

            if("saida".equals(produto.getStatus())){
                novaQuantidade = existente.getQuantidade() - produto.getQuantidade();
            }else {
                novaQuantidade = existente.getQuantidade() + produto.getQuantidade();
            }

            existente.setQuantidade(novaQuantidade);
            existente.setTotal(String.valueOf(novaQuantidade * valor));
            dao.atualizar(existente);


            produto.setNomeProduto(existente.getNomeProduto());
            produto.setFabricante(existente.getFabricante());
            produto.setMarca(existente.getMarca());
            produto.setPrateleira(existente.getPrateleira());
            produto.setValor(existente.getValor());
            produto.setTotal(String.valueOf(produto.getQuantidade() * valor));
            produto.setTipo("movimentacao"); // diferencia do saldo
            dao.cadastrar(produto);

            response.sendRedirect(request.getContextPath() + "/pages/projeto.html");
            return;
        }
        produto.setTipo("saldo");

        if(dao.cadastrar(produto)) {
            produto.setTipo("movimentacao");
            dao.cadastrar(produto);

            response.sendRedirect(request.getContextPath() + "/pages/projeto.html");
        }else{
            response.sendRedirect(request.getContextPath() + "/pages/cadastroProdutos.html");
        }
    }
}
