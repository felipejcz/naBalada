
package br.com.nabalada.persistence;

import br.com.nabalada.model.Evento;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class EventoDAO extends DAO {
    
    public void cadastrar(Evento ev)throws Exception{
        OpenDB();
        String sql = "SELECT id FROM Usuario WHERE usuario =?;";
        pstmt = con.prepareStatement(sql);
        pstmt.setString(1, ev.getCriador());
        rs = pstmt.executeQuery();
        int id=8;
        if(rs.next()){
            id = rs.getInt("id");
        }
        sql = "INSERT INTO Evento(titulo,descricao,local,data,hora,criador,autorizado,moderador)"
                + "VALUES(?,?,?,?,?,?,?,?);";
        pstmt.clearParameters();
        pstmt = con.prepareStatement(sql);
        pstmt.setString(1, ev.getTitulo());
        pstmt.setString(2, ev.getDescricao());
        pstmt.setString(3, ev.getLocal());
        DateFormat date = new SimpleDateFormat("dd/MM/yyyy");  
        java.sql.Date data = new java.sql.Date(date.parse(ev.getData()).getTime());
        pstmt.setDate(4, data);
        DateFormat formato = new SimpleDateFormat("HH:mm");  
        java.sql.Time hora = new java.sql.Time(formato.parse(ev.getHora()).getTime()); 
        pstmt.setTime(5, hora);
        pstmt.setInt(6, id);
        pstmt.setBoolean(7, ev.getAutorizado());
        pstmt.setInt(8, 0);
        pstmt.execute();
        CloseDB();
    }
    
    public void deletar(int id)throws Exception{
        OpenDB();
        String sql = "DELETE FROM Evento where id=?;";
        pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, id);
        pstmt.execute();
        CloseDB();
    }
    
    public void atualizar(Evento ev)throws Exception{
        OpenDB();
        int criador=0,moderador=0;
        pstmt = con.prepareStatement("SELECT id FROM Usuario WHERE usuario=?;");
        pstmt.setString(1, ev.getCriador());
        rs = pstmt.executeQuery();
        if(rs.next()){
            criador = rs.getInt("id");
        }
        pstmt.clearParameters();
        pstmt = con.prepareStatement("SELECT id FROM Usuario WHERE usuario=?;");
        pstmt.setString(1, ev.getModerador());
        rs = pstmt.executeQuery();
        if(rs.next()){
            moderador = rs.getInt("id");
        }
        pstmt.clearParameters();
        String sql = "UPDATE Evento set titulo=?,descricao=?,local=?,data=?,hora=?,foto=?,localizacao=?,criador=?,autorizado=?,moderador=?,comentario=? WHERE id=?;";
        pstmt = con.prepareStatement(sql);
        pstmt.setString(1, ev.getTitulo());
        pstmt.setString(2, ev.getDescricao());
        pstmt.setString(3, ev.getLocal());
        DateFormat date = new SimpleDateFormat("dd/MM/yyyy");  
        java.sql.Date data = new java.sql.Date(date.parse(ev.getData()).getTime());
        pstmt.setDate(4, data);
        DateFormat formato = new SimpleDateFormat("HH:mm");  
        java.sql.Time hora = new java.sql.Time(formato.parse(ev.getHora()).getTime()); 
        pstmt.setTime(5, hora);
        pstmt.setString(6, ev.getFoto());
        pstmt.setString(7, ev.getLocalizacao());
        pstmt.setInt(8, criador);
        pstmt.setBoolean(9, ev.getAutorizado());
        pstmt.setInt(10, moderador);
        pstmt.setString(11, ev.getComentario());
        pstmt.setInt(12, ev.getId());
        pstmt.executeUpdate();
        CloseDB();
    }
    
    public List<Evento> listar()throws Exception{
        OpenDB();
        String sql = "SELECT e.id,titulo,descricao,local,data,hora,foto,localizacao,p.usuario AS criador,autorizado,m.usuario AS moderador,comentario FROM Usuario p, Usuario m, Evento e where e.criador = p.id AND e.moderador = m.id order by e.id;";
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
            ev.setFoto(rs.getString("foto"));
            ev.setLocalizacao(rs.getString("localizacao"));
            ev.setCriador(rs.getString("criador"));
            ev.setAutorizado(rs.getBoolean("autorizado"));
            ev.setModerador(rs.getString("moderador"));
            ev.setComentario(rs.getString("comentario"));
            lista.add(ev);
        }
        CloseDB();
        return lista;
    }
    
    
  
    
}
