package controller;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class Perfilcontroller {
    public void doGet(ServletRequest request, ServletResponse response)
            throws IOException {
        HttpSession session = request.getSession(false);



        String perfil = (String) session.getAttribute("perfil");

        response.setContentType("aplication/json");
        response.getWriter().write("{\"perfil\":\"" + perfil + "\"}");

    }
}
