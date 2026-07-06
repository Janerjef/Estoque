package controller;

import com.google.gson.Gson;
import dao.PrateleiraDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.PrateleiraModel;

import java.io.IOException;
import java.util.List;

@WebServlet("/Prateleira")
public class PrateleiraController extends HttpServlet {

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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrateleiraDAO dao = new PrateleiraDAO();
        List<PrateleiraModel> lista = dao.listar();

        String json = new Gson().toJson(lista);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);

    }
    }



