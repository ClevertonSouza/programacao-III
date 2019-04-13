package br.edu.unisep.model.dao;

import br.edu.unisep.model.vo.CursoVO;
import br.edu.unisep.model.vo.ProfessorVO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfessorDAO {

    public void salvar(ProfessorVO professor) {

        try {

            var con = Conexao.obterConexao();

            var sql = new StringBuilder()
                    .append("INSERT INTO public.professor(")
                    .append("ds_professor, ds_email)")
                    .append("VALUES ( ?, ?);").toString();

            var ps = con.prepareStatement(sql);
            ps.setString(1, professor.getNome());
            ps.setString(2, professor.getEmail());

            ps.execute();

            ps.close();
            con.close();

        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

    }

    public List<ProfessorVO> listar(CursoVO... curso) {

        var professores = new ArrayList<ProfessorVO>();

        try {

            var con = Conexao.obterConexao();

            var sql = new StringBuilder()
                    .append("select p. * ")
                    .append("from professor p ");

            if (curso.length != 0) {
                sql.append("where exists (");
                sql.append("   select 1 ");
                sql.append("   from disciplina d ");
                sql.append("   where d.id_professor = p.id_professor ");
                sql.append("   and d.id_curso = ?);");
            }

            var ps = con.prepareStatement(sql.toString());

            if (curso.length != 0) {
                ps.setInt(1, curso[0].getId());
            }
            var rs = ps.executeQuery();

            while (rs.next()){
                var professor = new ProfessorVO();

                professor.setId(rs.getInt("id_professor"));
                professor.setNome(rs.getString("ds_professor"));
                professor.setEmail(rs.getString("ds_email"));

                professores.add(professor);
            }

            rs.close();
            ps.close();
            con.close();

        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

        return professores;
    }

}
