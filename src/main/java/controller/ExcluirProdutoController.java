    package controller;

    import dao.CadastroProdutosDAO;
    import jakarta.servlet.ServletException;
    import jakarta.servlet.annotation.WebServlet;
    import jakarta.servlet.http.HttpServlet;
    import jakarta.servlet.http.HttpServletRequest;
    import jakarta.servlet.http.HttpServletResponse;
    import model.CadastroProdutoModel;

    import java.io.IOException;

    @WebServlet("/api/produto/deletar")
    public class ExcluirProdutoController extends HttpServlet {
        public void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {

            CadastroProdutoModel produto = new CadastroProdutoModel();

            produto.setId(Integer.parseInt(request.getParameter("id")));


            CadastroProdutosDAO dao = new CadastroProdutosDAO();
            if (dao.deletar(produto.getID())) {
                response.sendRedirect(request.getContextPath() + "/pages/projeto.html");
            } else {
                response.sendRedirect(request.getContextPath() + "/pages/cadastroProdutos.html");
            }
        }
    }
