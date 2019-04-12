package br.edu.unisep.model.dao;

import br.edu.unisep.model.vo.AtendimentoVO;
import br.edu.unisep.model.vo.EspecialidadeVO;
import br.edu.unisep.model.vo.PlanoSaudeVO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AtendimentoDAO {

    public List<AtendimentoVO> listar (){

        ArrayList<AtendimentoVO> atendimentos = new ArrayList<>();

        try {
            var con = Conexao.obterConexao();


            var stringSelect = new StringBuffer();
            stringSelect.append("select a.id_atandimento,");
            stringSelect.append("a.ds_paciente,");
            stringSelect.append("p.id_plano,");
            stringSelect.append("p.ds_plano,");
            stringSelect.append("e.id_especialidade,");
            stringSelect.append("e.ds_especialidade");
            stringSelect.append("a.dt_nascimento,");
            stringSelect.append("a.ds_sintomas,");
            stringSelect.append("a.tp_status");
            stringSelect.append("from atendimento a");
            stringSelect.append("join public.especialidade e on e.id_especialidade = a.id_especialidade");
            stringSelect.append("join public.plano_saude p on p.id_plano = a.id_plano");

            var ps = con.prepareStatement(stringSelect.toString());

            var rs = ps.executeQuery();

            while (rs.next()){
                var a = new AtendimentoVO();
                a.setId(rs.getInt("id_atendimento"));
                a.setDs_paciente(rs.getString("ds_paciente"));
                a.setDt_nascimento(rs.getDate("dt_nascimento").toLocalDate());
                a.setDs_sintomas(rs.getString("ds_sintomas"));
                a.setTp_status(rs.getInt("tp_status"));

                var p = new PlanoSaudeVO();
                p.setId(rs.getInt("id_plano"));
                p.setDs_plano(rs.getString("ds_plano"));

                var e = new EspecialidadeVO();
                e.setId(rs.getInt("id_especialidade"));
                e.setDs_especialidade(rs.getString("ds_especialidade"));

                a.setId_plano(p);
                a.setId_especialidade(e);

                atendimentos.add(a);
            }

            rs.close();
            ps.close();
            con.close();

        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        return atendimentos;
    }
}
