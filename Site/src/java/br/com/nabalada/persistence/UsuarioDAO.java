
package br.com.nabalada.persistence;

import br.com.nabalada.model.Usuario;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO extends DAO{
    
    
    public Usuario getUser(String user,String pass)throws Exception{
        OpenDB();
        try{
            pstmt = con.prepareStatement("select * from usuario where usuario = ? and senha = ?");
            pstmt.setString(1, user);
            pstmt.setString(2, pass);
 
            rs = pstmt.executeQuery();
 
            if ( rs.next() ){
                Usuario usu = new Usuario();
                usu.setId(rs.getInt("id"));
                usu.setUsuario(rs.getString("usuario"));
                usu.setSenha(rs.getString("senha"));
                usu.setNome(rs.getString("nome"));
                usu.setEmail(rs.getString("email"));            
                usu.setNivelAcesso(rs.getInt("nivelAcesso_id"));
                return usu;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally{
            CloseDB();
        }
        return null;
    }
        
    
    
    
    public void cadastrar(Usuario usu)throws Exception{
        OpenDB();
        String sql = "INSERT INTO Usuario(usuario,senha,nome,email,nivelAcesso_id) values(?,?,?,?,?);";
        pstmt = con.prepareStatement(sql);
        pstmt.setString(1, usu.getUsuario());
        pstmt.setString(2, usu.getSenha());
        pstmt.setString(3, usu.getNome());
        pstmt.setString(4, usu.getEmail());
        pstmt.setInt(5, 1);
        pstmt.execute();
        CloseDB();
    }
    
    public void deletar(int id) throws Exception{
        OpenDB();
        String sql = "UPDATE Evento set criador=0 where criador=?;";
        pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, id);
        pstmt.execute();
        pstmt.clearParameters();
        sql = "DELETE FROM Usuario where id=?";
        pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, id);
        pstmt.execute();
        CloseDB();
    }
    
    public void atualizar(Usuario usu)throws Exception{
        OpenDB();
        String sql = "UPDATE Usuario set usuario=?, senha=?, nome=?, email=?, nivelAcesso_id=? where id=?;";
        pstmt = con.prepareStatement(sql);
        pstmt.setString(1, usu.getUsuario());
        pstmt.setString(2, usu.getSenha());
        pstmt.setString(3, usu.getNome());
        pstmt.setString(4, usu.getEmail());
        pstmt.setInt(5, usu.getNivelAcesso());
        pstmt.setInt(6, usu.getId());
        pstmt.executeUpdate();
        CloseDB();
    }
    
    public List<Usuario> listar()throws Exception{
        OpenDB();
        String sql = "SELECT * FROM Usuario order by nome;";
        pstmt = con.prepareStatement(sql);
        rs = pstmt.executeQuery();
        Usuario usu = null;
        List<Usuario> lista = new ArrayList<Usuario>();
        while(rs.next()){
            usu = new Usuario();
            usu.setId(rs.getInt("id"));
            usu.setUsuario(rs.getString("usuario"));
            usu.setSenha(rs.getString("senha"));
            usu.setNome(rs.getString("nome"));
            usu.setEmail(rs.getString("email"));            
            usu.setNivelAcesso(rs.getInt("nivelAcesso_id"));
            lista.add(usu);
        }
        CloseDB();
        return lista;
    }
 
}
