package br.edu.unisep;

import br.edu.unisep.model.dao.AtendimentoDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        listarAtendimentos();
    }

    private void listarAtendimentos(){
        var dao = new AtendimentoDAO();
        dao.listar();
    }
}
