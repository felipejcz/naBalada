
package br.com.nabalada.persistence;

import br.com.nabalada.model.Usuario;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO extends DAO{
    
    public void cadastrar(Usuario usu)throws Exception{
        OpenDB();
        String sql = "INSERT INTO Usuario(usuario,senha) values(?,?);";
        pstmt = con.prepareStatement(sql);
        pstmt.setString(1, usu.getUsuario());
        pstmt.setString(2, usu.getSenha());
        pstmt.execute();
        CloseDB();
    }
    
    public void deletar(Usuario usu)throws Exception{
        OpenDB();
        String sql = "DELETE FROM Usuario where usuario=?;";
        pstmt = con.prepareStatement(sql);
        pstmt.setString(1, usu.getUsuario());
        pstmt.execute();
        CloseDB();
    }
    
    public void atualizar(Usuario usu)throws Exception{
        OpenDB();
        String sql = "UPDATE Usuario set usuario=?, senha=? where id=?;";
        pstmt = con.prepareStatement(sql);
        pstmt.setString(1, usu.getUsuario());
        pstmt.setString(2, usu.getSenha());
        pstmt.setInt(3, usu.getId());
        pstmt.executeUpdate();
    }
    
    public List<Usuario> listar()throws Exception{
        OpenDB();
        String sql = "SELECT * FROM Usuario;";
        pstmt = con.prepareStatement(sql);
        rs = pstmt.executeQuery();
        Usuario usu = null;
        List<Usuario> lista = new ArrayList<Usuario>();
        while(rs.next()){
            usu = new Usuario();
            usu.setId(rs.getInt("id"));
            usu.setUsuario(rs.getString("usuario"));
            usu.setSenha(rs.getString("senha"));
            lista.add(usu);
        }
        CloseDB();
        return lista;
    }
    
    public boolean validar(Usuario usu)throws Exception{
        List<Usuario> lista = listar();
        OpenDB();
        
        for(Usuario u : lista){
            if(u.getUsuario().equals(usu.getUsuario())){
                if(u.getSenha().equals(usu.getSenha())){
                    return true;
                }
            }
        }
        CloseDB();
        return false;
    }
    
    
    
    
}
