
package br.com.nabalada.persistence;

import br.com.nabalada.model.Evento;
import java.util.ArrayList;
import java.util.List;

public class EventoDAO extends DAO {
    
    public void cadastrar(Evento ev)throws Exception{
        OpenDB();
        int criador = 0;
        pstmt = con.prepareStatement("SELECT Usuario.id FROM Evento inner join Usuario on ('"+ev.getCriador()+"'= Usuario.nome);");
        rs = pstmt.executeQuery();
        if(rs.next()){
            criador = rs.getInt("id");
        }
        String sql = "INSERT INTO Evento(titulo,descricao,local,data,hora,criador,autorizado,moderador)VALUES(?,?,?,?,?,?,false,0);";
        pstmt = con.prepareStatement(sql);
        pstmt.setString(1, ev.getTitulo());
        pstmt.setString(2, ev.getDescricao());
        pstmt.setString(3, ev.getLocal());
        pstmt.setString(4, ev.getData());
        pstmt.setString(5, ev.getHora());
        pstmt.setInt(6, criador);
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
        String sql = "UPDATE Evento set titulo=?,descricao=?,local=?,data=?,hora=?,foto=?,localizacao=?,criador=?,autorizado=?,moderador=?,comentario=? WHERE id=?;";
        pstmt = con.prepareStatement(sql);
        pstmt.setString(1, ev.getTitulo());
        pstmt.setString(2, ev.getDescricao());
        pstmt.setString(3, ev.getLocal());
        pstmt.setString(4, ev.getData());
        pstmt.setString(5, ev.getHora());
        pstmt.setString(6, ev.getFoto());
        pstmt.setString(7, ev.getLocalizacao());
        pstmt.setString(8, ev.getCriador());
        pstmt.setBoolean(9, ev.getAutorizado());
        pstmt.setString(10, ev.getModerador());
        pstmt.setString(11, ev.getComentario());
        pstmt.setInt(12, ev.getId());
        pstmt.executeUpdate();
        CloseDB();
    }
    
    public List<Evento> listar()throws Exception{
        OpenDB();
        String sql = "SELECT Evento.id,titulo,descricao,local,data,hora,foto,localizacao,pessoa.nome AS criador,autorizado,moderador.nome AS moderador,comentario FROM Usuario pessoa, Usuario moderador, Evento WHERE Evento.criador = pessoa.id and Evento.moderador = moderador.id;";
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
