package br.edu.unisep.model.DAO;

import br.edu.unisep.model.vo.AutorVO;
import br.edu.unisep.model.vo.LivroVO;

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

            while (rs.next()) {
                var l = new LivroVO();
                l.setId(rs.getInt("id_livro"));
                l.setTitulo(rs.getString("titulo_lv"));
                l.setSinopse(rs.getString("sinopse_lv"));
                l.setEditora(rs.getString("editora_lv"));
                l.setPaginas(rs.getInt("paginas_lv"));
                l.setStatus((rs.getInt("status_lv")));

                var a = new AutorVO();
                a.setId(rs.getInt("id_autor"));
                a.setNome(rs.getString("nome"));

                l.setAutor(a);

                livros.add(l);
            }

            rs.close();
            ps.close();
            con.close();


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return livros;
    }


    public void salvar(LivroVO livro) {

        try {

            var con = Conexao.obterConexao();

            var sql=  new StringBuffer()
                    .append("INSERT INTO public.livro(")
                    .append("titulo_lv, editora_lv, ")
                    .append("paginas_lv, status_lv, ")
                    .append("sinopse_lv, id_autor) ")
                    .append("VALUES (?, ?, ?, ?, ?, ?)").toString();

            var ps = con.prepareStatement(sql);
            ps.setString(1,livro.getTitulo());
            ps.setString(2,livro.getEditora());
            ps.setInt(3,livro.getPaginas());
            ps.setInt(4,livro.getStatus());
            ps.setString(5,livro.getSinopse());
            ps.setInt(6,livro.getAutor().getId());

            ps.execute();
            ps.close();
            con.close();


        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }

    public void alterar(LivroVO livro){

        try {

            var con = Conexao.obterConexao();

            var sql = new StringBuilder()
                    .append("UPDATE")
                    .append(" public.livro SET titulo_lv=?,")
                    .append(" editora_lv=?,")
                    .append(" paginas_lv=?,")
                    .append(" status_lv=?,")
                    .append(" sinopse_lv=?,")
                    .append(" id_autor=? ")
                    .append("WHERE id_livro=?;").toString();

            var ps = con.prepareStatement(sql);
            ps.setString(1,livro.getTitulo());
            ps.setString(2,livro.getEditora());
            ps.setInt(3,livro.getPaginas());
            ps.setInt(4,livro.getStatus());
            ps.setString(5,livro.getSinopse());
            ps.setInt(6,livro.getAutor().getId());
            ps.setInt(7,livro.getId());

            ps.execute();
            ps.close();
            con.close();


        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }

}
