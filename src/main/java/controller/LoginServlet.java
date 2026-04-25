/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.UserModel;

import java.io.IOException;

@WebServlet("/api/login")
public class LoginServlet extends HttpServlet {
    
    protected void doPost (
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
        String usuario = request.getParameter("users");
        String senha = request.getParameter("passw");
        
        UserModel userModel = new UserModel ();
        userModel.setUsername(usuario);
        userModel.setPassword(senha);
        
        UserDAO dao = new UserDAO ();
        UserModel user = dao.validarLogin(userModel);
        if (user != null) {
            HttpSession session =
                    request.getSession();
            
            session.setAttribute("usuario", user.getUsername());
            session.setAttribute("perfil", user.getFuncao());

            
            response.sendRedirect(request.getContextPath() +"/pages/projeto.html");
        } else {
            response.sendRedirect(request.getContextPath() +"/index.html");
        }
    }
}