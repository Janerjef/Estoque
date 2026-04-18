package controller;

import dao.CadastroProdutosDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.CadastroProdutoModel;

import java.io.IOException;

public class CadastroProdutoContoller {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        CadastroProdutoModel produto = new CadastroProdutoModel();

        produto.setCodigoBarras(request.getParameter("codigoBarras"));
        produto.setNomeProduto(request.getParameter("nomeProduto"));
        produto.setFabricante(request.getParameter("Fabricante"));
        produto.setMarca(request.getParameter("Marca"));
        produto.setDataFabricacao(request.getParameter("dataFabricacao"));
        produto.setDataVencimento(request.getParameter("datavencimento"));
        produto.setQuantidade(Long.parseLong(request.getParameter("quantidade")));
        produto.setValor(request.getParameter("valor"));
        produto.setTotal(request.getParameter("total"));
        produto.setStatus(request.getParameter("status"));

        CadastroProdutosDAO dao = new CadastroProdutosDAO();

        if(dao.cadastrar(produto)) {
            response.sendRedirect(request.getContextPath() + "pages/dashboard.html");
        }else{
            response.sendRedirect(request.getContextPath() + "pages/cadastroProdutos.html");
        }
    }
}
