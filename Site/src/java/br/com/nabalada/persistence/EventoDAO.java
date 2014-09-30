
package br.com.nabalada.persistence;

import br.com.nabalada.model.Evento;
import java.util.ArrayList;
import java.util.List;

public class EventoDAO extends DAO {
    
    public void cadastrar(Evento ev)throws Exception{
        OpenDB();
        String sql = "INSERT INTO Evento(titulo,descricao,local,data,hora) values(?,?,?,?,?);";
        pstmt = con.prepareStatement(sql);
        pstmt.setString(1, ev.getTitulo());
        pstmt.setString(2, ev.getDescricao());
        pstmt.setString(3, ev.getLocal());
        pstmt.setString(4, ev.getData());
        pstmt.setString(5, ev.getHora());
        pstmt.execute();
        CloseDB();
    }
    
    public void deletar(Evento ev)throws Exception{
        OpenDB();
        String sql = "DELETE FROM Evento where id=?;";
        pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, ev.getId());
        pstmt.execute();
        CloseDB();
    }
    
    public void atualizar(Evento ev)throws Exception{
        OpenDB();
        String sql = "UPDATE Evento set titulo=?, descricao=?, local=?, data=?, hora=? where id=?;";
        pstmt = con.prepareStatement(sql);
        pstmt.setString(1, ev.getTitulo());
        pstmt.setString(2, ev.getDescricao());
        pstmt.setString(3, ev.getLocal());
        pstmt.setString(4, ev.getData());
        pstmt.setString(5, ev.getHora());
        pstmt.setInt(6, ev.getId());
        pstmt.executeUpdate();
        CloseDB();
    }
    
    public List<Evento> listar()throws Exception{
        OpenDB();
        String sql = "SELECT * FROM Evento;";
        pstmt = con.prepareStatement(sql);
        rs = pstmt.executeQuery();
        Evento ev = null;
        List<Evento> lista = new ArrayList<Evento>();
        while(rs.next()){
            ev = new Evento();
            ev.setId(rs.getInt("id"));
            ev.setTitulo(rs.getString("titulo"));
            ev.setDescricao(rs.getString("descricao"));
            ev.setLocal(rs.getString("local"));
            ev.setData(rs.getString("data"));
            ev.setHora(rs.getString("hora"));
            lista.add(ev);
        }
        CloseDB();
        return lista;
    }
    
    public boolean validar(Evento ev)throws Exception{
        List<Evento> lista = listar();
        OpenDB();
        
        for(Evento e : lista){
            if(e.getTitulo().equals(ev.getTitulo())){
                CloseDB();
                return true;
            }
        }
        CloseDB();
        return false;
    }
    
}
