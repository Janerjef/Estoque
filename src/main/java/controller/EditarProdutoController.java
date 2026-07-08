package controller;

import dao.CadastroProdutosDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.CadastroProdutoModel;

import java.io.IOException;

@WebServlet("/api/produto/editar")
public class EditarProdutoController extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        CadastroProdutoModel produto = new CadastroProdutoModel();

        produto.setCodigoBarras(request.getParameter("codigoBarras"));
        produto.setNomeProduto(request.getParameter("nomeProduto"));
        produto.setFabricante(request.getParameter("fabricante"));
        produto.setMarca(request.getParameter("Marca"));
        produto.setDataFabricacao(request.getParameter("dataFabricacao"));
        produto.setDataVencimento(request.getParameter("dataVencimento"));
        produto.setQuantidade(Long.parseLong(request.getParameter("quantidade")));
        produto.setValor(request.getParameter("valor"));
        produto.setTotal(request.getParameter("total"));
        produto.setStatus(request.getParameter("status"));
        produto.setId(Integer.parseInt(request.getParameter("id")));
        produto.setPrateleira(request.getParameter("prateleira_id"));
        produto.setEstoqueMínimo(Integer.parseInt(request.getParameter("EstoqueMínimo")));


        CadastroProdutosDAO dao = new CadastroProdutosDAO();
        if (dao.atualizar(produto)) {
            response.sendRedirect(request.getContextPath() + "/pages/projeto.html");
        } else {
            response.sendRedirect(request.getContextPath() + "/pages/cadastroProdutos.html");
        }
    }

}
