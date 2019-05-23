package br.edu.unisep.model.dao;

import br.edu.unisep.model.vo.CursoVO;
import br.edu.unisep.model.vo.DisciplinaVO;
import br.edu.unisep.model.vo.ProfessorVO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DisciplinaDAO {

    public void salvar(DisciplinaVO disciplina) {

        try {

            var con = Conexao.obterConexao();

            var sql = new StringBuilder()
                    .append("INSERT INTO public.disciplina(")
                    .append("ds_disciplina, id_professor, id_curso, nr_cargahoraria, nr_periodo)")
                    .append("VALUES (?, ?, ?, ?, ?);").toString();

            var ps = con.prepareStatement(sql);
            ps.setString(1, disciplina.getNome());
            ps.setInt(2, disciplina.getProfessor().getId());
            ps.setInt(3, disciplina.getCurso().getId());
            ps.setInt(4, disciplina.getCargaHoraria());
            ps.setInt(5, disciplina.getPeriodo());

            ps.execute();

            ps.close();
            con.close();

        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }


    public List<DisciplinaVO> listar(CursoVO... curso) {
        var disciplinas = new ArrayList<DisciplinaVO>();

        try {

            var con = Conexao.obterConexao();
            var sql = new StringBuilder()
                    .append("select ")
                    .append(" * ")
                    .append("from ")
                    .append("disciplina d ")
                    .append("join ")
                    .append("professor p ")
                    .append("ON p.id_professor = d.id_professor ")
                    .append("join ")
                    .append("curso c ")
                    .append("on c.id_curso = d.id_curso ");

            if (curso.length != 0) {
                sql.append("where c.id_curso = ?;");
            }

            var ps = con.prepareStatement(sql.toString());
            if (curso.length != 0) {
                ps.setInt(1, curso[0].getId());
            }

            var rs = ps.executeQuery();

            while (rs.next()){

                var d =  new DisciplinaVO();
                d.setId(rs.getInt("id_disciplina"));
                d.setNome(rs.getString("ds_disciplina"));
                d.setCargaHoraria(rs.getInt("nr_cargahoraria"));
                d.setPeriodo(rs.getInt("nr_periodo"));

                var c = new CursoVO();
                c.setId(rs.getInt("id_curso"));
                c.setNome(rs.getString("ds_curso"));
                c.setTipo(rs.getInt("tp_curso"));
                c.setDuracao(rs.getInt("vl_duracao"));

                var p = new ProfessorVO();
                p.setId(rs.getInt("id_professor"));
                p.setNome(rs.getString("ds_professor"));
                p.setEmail(rs.getString("ds_email"));

                d.setCurso(c);
                d.setProfessor(p);

                disciplinas.add(d);
            }

            rs.close();
            ps.close();
            con.close();

        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

        return disciplinas;
    }
}
