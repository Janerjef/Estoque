/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.CadastroUsersDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.CadastroUsuarioModel;

import java.io.IOException;


@WebServlet("/cadastro")
public class CadastroController extends HttpServlet {
      //VOU TER QUE FAZER UM DOGET
      /*protected void doGet(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException{
            request.getRequestDispatcher("/pages/cadastro.html").forward(request,response);
      }*/


    // A "ponte" entre o banco e o usuario
     protected void doPost(HttpServletRequest request, HttpServletResponse response)
            // recebe uma requisição do usuario
            throws ServletException, IOException {

        CadastroUsuarioModel user = new CadastroUsuarioModel();
        
        user.setNome(request.getParameter("nameFirst"));
        user.setSobrenome(request.getParameter("sobrenome"));
        user.setMatricula(request.getParameter("matricula"));
        user.setCpf(request.getParameter("cpf"));
        user.setSexo(request.getParameter("sexo"));
        user.setDtaNascimento(request.getParameter("dtaNascimento"));
        user.setEmail(request.getParameter("email"));
        user.setTelefone(request.getParameter("telefone"));
        user.setNomeUsuario(request.getParameter("usuario"));
        user.setSenha(request.getParameter("senha"));
        user.setFuncao(request.getParameter("funcao"));
        user.setCep(request.getParameter("cep"));;
        user.setEndereco(request.getParameter("endereco"));
        user.setCidade(request.getParameter("cidade"));
        user.setBairro(request.getParameter("bairro"));
        user.setEstado(request.getParameter("estado"));
        user.setNumero(request.getParameter("numero"));
        user.setComplemento(request.getParameter("complemento"));

        
        CadastroUsersDAO dao = new CadastroUsersDAO();
        //comunica com o model para os dados processarem

        //decide para onde vai
        if(dao.cadastrar(user)) {
            response.sendRedirect(request.getContextPath() +"/index.html");
        } else {
            response.sendRedirect(request.getContextPath() + "/pages/cadastro.html");
        }
    }
}
