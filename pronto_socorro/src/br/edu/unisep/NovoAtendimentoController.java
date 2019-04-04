package br.edu.unisep;

import br.edu.unisep.model.dao.EspecialidadeDAO;
import br.edu.unisep.model.dao.PlanoSaudeDAO;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class NovoAtendimentoController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        listarPlanoSaude();
        listarEspecialidade();
    }

    private void listarPlanoSaude(){
        var dao = new PlanoSaudeDAO();
        var listar = dao.listar();
    }

    private void listarEspecialidade(){
        var dao = new EspecialidadeDAO();
        var listar =  dao.listar();
    }
}
