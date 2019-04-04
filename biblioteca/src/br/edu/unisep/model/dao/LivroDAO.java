package br.edu.unisep.model.dao;

import br.edu.unisep.model.vo.AutorVO;
import br.edu.unisep.model.vo.LivroVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO {

    public List<LivroVO> listar() {

        var livros = new ArrayList<LivroVO>();

        try {

            var con = Conexao.obterConexao();

            var sql = new StringBuffer();
            sql.append("select l.id_livro, ");
            sql.append("l.titulo_lv, ");
            sql.append("l.sinopse_lv, ");
            sql.append("l.editora_lv, ");
            sql.append("l.paginas_lv, ");
            sql.append("l.status_lv, ");
            sql.append("a.id_autor, ");
            sql.append("a.nome ");
            sql.append("from livro l ");
            sql.append("inner join autor a ");
            sql.append("on l.id_autor = a.id_autor");

            var ps = con.prepareStatement(sql.toString());
            var rs = ps.executeQuery();

            while (rs.next()){
                var l = new LivroVO();

                l.setId(rs.getInt("id_livro"));
                l.setTitulo(rs.getString("titulo_lv"));
                l.setSinopse(rs.getString("sinopse_lv"));
                l.setEditora(rs.getString("editora_lv"));
                l.setPaginas(rs.getInt("paginas_lv"));
                l.setStatus(rs.getInt("status_lv"));

                var a = new AutorVO();
                a.setId(rs.getInt("id_autor"));
                a.setNome(rs.getString("nome"));

                l.setAutor(a);

                livros.add(l);
            }

            rs.close();
            con.close();


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return livros;
    }


    public void salvar (LivroVO l){

        try{

            var con = Conexao.obterConexao();

            var sql = new StringBuilder()
                    .append("INSERT INTO public.livro(")
                    .append("titulo_lv, editora_lv, paginas_lv, status_lv, sinopse_lv, id_autor)")
                    .append("VALUES (?, ?, ?, ?, ?, ?);")
                    .toString();


            var ps = con.prepareStatement(sql);

            ps.setString(1, l.getTitulo());
            ps.setString(2, l.getEditora());
            ps.setInt(3, l.getPaginas());
            ps.setInt(4, l.getStatus());
            ps.setString(5, l.getSinopse());
            ps.setInt(6, l.getAutor().getId());

            ps.execute();

            ps.close();
            con.close();


        } catch (ClassNotFoundException | SQLException exception){
        }
    }

}
