/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

/**
 *
 * @author 232.004390
 */
public class ConnectionFactory {
    // Pra criar a conexão, preciso de Dados da conexão
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL =("DB_URL");
    private static final String USER = ("DB_USER");
    private static final String PASSWORD = ("DB_PASS");
    // 2.Métodos
    public static Connection getConnection() {
        Connection con = null;
        
        try {
            if(URL == null || USER == null || PASSWORD == null){
                System.out.println("Variavel de ambiente com problema");
                return null;
            }
            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Banco de dados conectado.");
        } catch(ClassNotFoundException e){
            System.out.println("Erro no JDBC");
            e.printStackTrace();
        } catch(SQLException e){
            System.out.println("Banco de dados não conectado");
            e.printStackTrace();
        } 
        catch (Exception e) {
            System.out.println("Banco de dados não conectado.");
            e.printStackTrace();
        }
        return con;
    }
}
