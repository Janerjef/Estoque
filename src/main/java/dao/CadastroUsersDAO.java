package dao;

import connection.ConnectionFactory;
import model.CadastroUsuarioModel;
import util.SenhaUtil;

import java.sql.PreparedStatement;

public class CadastroUsersDAO {
    
    public boolean cadastrar(CadastroUsuarioModel user) {
        String sql = "INSERT INTO users" +
                "(username, psw, nameFirst, sobrenome, dtaNascimento, matricula, cpf, sexo, email, telefone, funcao, cep, endereco, bairro, cidade, estado, numero, complemento)" +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (var con = ConnectionFactory.getConnection()){
            
            PreparedStatement stmt = con.prepareStatement(sql);

            String senhaHash = SenhaUtil.gerarHash(user.getSenha());

            stmt.setString(1, user.getNomeUsuario()); // username
            stmt.setString(2, senhaHash); // psw
            stmt.setString(3, user.getNome()); // nameFirst
            stmt.setString(4, user.getSobrenome());
            stmt.setString(5, user.getDtaNascimento());
            stmt.setString(6, user.getMatricula());
            stmt.setString(7, user.getCpf());
            stmt.setString(8, user.getSexo());
            stmt.setString(9, user.getEmail());
            stmt.setString(10, user.getTelefone());
            stmt.setString(11, user.getFuncao());
            stmt.setString(12, user.getCep());
            stmt.setString(13, user.getEndereco());
            stmt.setString(14, user.getBairro());
            stmt.setString(15, user.getCidade());
            stmt.setString(16, user.getEstado());
            stmt.setString(17, user.getNumero());
            stmt.setString(18, user.getComplemento());
            
            stmt.executeUpdate();
            
            return true;
   
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERRO AO CADASTRAR: " + e.getMessage());
            return false;
        }
    }
}
