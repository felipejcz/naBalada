
package br.com.nabalada.persistence;

import java.sql.*;

public class DAO {
    Connection con;
    PreparedStatement pstmt;
    ResultSet rs;
    
    public void OpenDB()throws Exception{
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/LoginJSP";
        String user = "postgres";
        String password = "123456";
        con = DriverManager.getConnection(url,user,password);
    }
    
    public void CloseDB()throws Exception{
        if(con != null){
            con.close();
        }
    }
    
}
