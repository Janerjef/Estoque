package util;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/*")
public class AuthFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);
        String uri = req.getRequestURI();

        if(uri.contains("index.html") || uri.contains("login") || uri.contains("css") || uri.contains("js") ||
                uri.contains("cadastro.html") || uri.endsWith("/cadastro") || uri.endsWith("/api/estoque")){
            chain.doFilter(request, response);

            return;
        }
        if(session == null || session.getAttribute("usuario") == null) {
            res.sendRedirect(req.getContextPath()+"index.html");
            return;
        }

        String perfil = (String) session.getAttribute("perfil");

        if (!"Administrador".equals(perfil)
                && !uri.contains("/pages/projeto.html")
                && !uri.contains("/Prateleira")
                && !uri.endsWith("/api/estoque")
                && !uri.endsWith("/api/resumo")
                && !uri.toLowerCase().contains("logout")) {

            res.sendRedirect(req.getContextPath() + "/pages/projeto.html");
            return;
        }

        if((uri.contains("cadastroProduto") || uri.contains("cadastroPrateleira") ||
                uri.contains("api/produto/editar")) && !"Administrador".equals(perfil)){
            res.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        chain.doFilter(request, response);
    }
}