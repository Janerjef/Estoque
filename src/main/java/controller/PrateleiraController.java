package controller;

import dao.PrateleiraDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.PrateleiraModel;

import java.io.IOException;
@WebServlet("/Prateleira")
public class PrateleiraController {

        public void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            PrateleiraModel prateleira = new PrateleiraModel();

            prateleira.setNome(request.getParameter("nomePrateleira"));
            prateleira.setDescricao(request.getParameter("descricaoPrateleira"));

            PrateleiraDAO dao = new PrateleiraDAO();

            if(dao.cadastrar(prateleira)) {
                response.sendRedirect(request.getContextPath() +"/index.html");
            } else {
                response.sendRedirect(request.getContextPath() + "/pages/.html");
            }
        }
    }

}
