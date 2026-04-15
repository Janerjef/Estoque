package dao;

import connection.ConnectionFactory;
import java.sql.PreparedStatement;
import model.CadastroUsuarioModel;

/**
 *
 * @author 232.004390
 */
public class CadastroUsersDAO {
    
    public boolean cadastrar(CadastroUsuarioModel user) {
        String sql = "INSERT INTO users" +
                "(username, psw, nameFirst, sobrenome, dtaNascimento, matricula, cpf, sexo, email, telefone, função, cep, endereço, bairro, cidade, estado, numero, complemento)" +
                "VALUE(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (var con = ConnectionFactory.getConnection()){
            
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, user.getNome()); // namefirst
            stmt.setString(2, user.getSobrenome());
            stmt.setString(3, user.getMatricula());
            stmt.setString(4, user.getCpf());
            stmt.setString(5, user.getSexo());
            stmt.setString(6, user.getDtaNascimento());
            stmt.setString(7, user.getEmail());
            stmt.setString(8, user.getTelefone());
            stmt.setLong(9, user.getCep());
            stmt.setString(10, user.getEndereco());
            stmt.setString(11, user.getEstado());
            stmt.setString(12, user.getBairro());
            stmt.setString(13, user.getCidade());
            stmt.setLong(14, user.getNumero());
            stmt.setString(15, user.getComplemento());
            stmt.setString(16, user.getNomeUsuario()); //username
            stmt.setString(17, user.getSenha()); //psw
            stmt.setString(18, user.getFuncao());
            
            stmt.executeUpdate();
            
            return true;
   
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
