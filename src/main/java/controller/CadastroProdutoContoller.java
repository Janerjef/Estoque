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
        produto.setMarca(request.getParameter("Marca"));
        produto.setDataFabricacao(request.getParameter("dataFabricacao"));
        produto.setDataVencimento(request.getParameter("dataVencimento"));
        produto.setQuantidade(Long.parseLong(request.getParameter("quantidade")));
        produto.setValor(request.getParameter("valor"));
        produto.setTotal(request.getParameter("total"));
        produto.setStatus(request.getParameter("status"));
        produto.setPrateleira(request.getParameter("Prateleira"));
        produto.setEstoqueMínimo(Integer.parseInt(request.getParameter("EstoqueMínimo")));

        CadastroProdutosDAO dao = new CadastroProdutosDAO();
        if("saida".equals(produto.getStatus())){
            CadastroProdutoModel existente = dao.buscarPorCodigoBarras(produto.getCodigoBarras());
            if(existente != null){
                long novaQuantidade = existente.getQuantidade() - produto.getQuantidade();
                double valor = Double.parseDouble(existente.getValor());
                existente.setQuantidade(novaQuantidade);
                existente.setValor(String.valueOf(valor));
                existente.setTotal(String.valueOf(novaQuantidade * Double.parseDouble(existente.getValor())));

                produto.setNomeProduto(existente.getNomeProduto());
                produto.setFabricante(existente.getFabricante());
                produto.setMarca(existente.getMarca());
                produto.setDataFabricacao(existente.getDataFabricacao());
                produto.setDataVencimento(existente.getDataVencimento());
                produto.setPrateleira(existente.getPrateleira());
                produto.setEstoqueMínimo(existente.getEstoqueMínimo());

                
                dao.atualizar(existente);
                response.sendRedirect(request.getContextPath() + "/pages/projeto.html");
                return;
            }


        } else if("entrada".equals(produto.getStatus())){
            CadastroProdutoModel existente = dao.buscarPorCodigoBarras(produto.getCodigoBarras());
            if(existente != null){
                long novaQuantidade = existente.getQuantidade() + produto.getQuantidade();
                double valor = Double.parseDouble(existente.getValor());
                existente.setQuantidade(novaQuantidade);
                existente.setValor(String.valueOf(valor));
                existente.setTotal(String.valueOf(novaQuantidade * Double.parseDouble(existente.getValor())));


                produto.setNomeProduto(existente.getNomeProduto());
                produto.setFabricante(existente.getFabricante());
                produto.setMarca(existente.getMarca());
                produto.setDataFabricacao(existente.getDataFabricacao());
                produto.setDataVencimento(existente.getDataVencimento());
                produto.setPrateleira(existente.getPrateleira());
                produto.setEstoqueMínimo(existente.getEstoqueMínimo());



                dao.atualizar(existente);
                response.sendRedirect(request.getContextPath() + "/pages/projeto.html");
                return;
            }




        }
        if(dao.cadastrar(produto)) {
            response.sendRedirect(request.getContextPath() + "/pages/projeto.html");
        }else{
            response.sendRedirect(request.getContextPath() + "/pages/cadastroProdutos.html");
        }
    }
}
