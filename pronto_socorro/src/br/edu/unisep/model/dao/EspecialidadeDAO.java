package br.edu.unisep.model.dao;

import br.edu.unisep.model.vo.EspecialidadeVO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EspecialidadeDAO {

    public List<EspecialidadeVO> listar (){

        ArrayList<EspecialidadeVO> especialidades = new ArrayList<>();

        try {

            var con = Conexao.obterConexao();

            var ps = con.prepareStatement("select * from especialidade order by ds_especialidade");

            var rs = ps.executeQuery();

            while (rs.next()){
                var e = new EspecialidadeVO();

                e.setId(rs.getInt("id_especialidade"));
                e.setDs_especialidade(rs.getString("ds_especialidade"));

                especialidades.add(e);
            }

            rs.close();
            ps.close();
            con.close();


        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

        return especialidades;

    }
}
