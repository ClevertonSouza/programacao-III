package br.edu.unisep.model.dao;

import br.edu.unisep.model.vo.PlanoSaudeVO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlanoSaudeDAO {

    public List<PlanoSaudeVO> listar (){

        ArrayList<PlanoSaudeVO> planos = new ArrayList<>();

        try{

            var con = Conexao.obterConexao();

            var ps = con.prepareStatement("select * from plano_saude" +
                    "order by ds_plano");

            var rs = ps.executeQuery();

            while (rs.next()){
                var a = new PlanoSaudeVO();

                a.setId(rs.getInt("id_plano"));
                a.setDs_plano(rs.getString("ds_plano"));

                planos.add(a);
            }

            rs.close();
            ps.close();
            con.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return planos;
    }
}
