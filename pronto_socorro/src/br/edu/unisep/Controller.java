package br.edu.unisep;

import br.edu.unisep.model.dao.AtendimentoDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }


    public void novoAtendimento(ActionEvent event) throws IOException {

        var loader = new FXMLLoader();
        var root = (Parent) loader.load(getClass().getResource("novoAtendimento.fxml"));

        var janela = new Stage();
        janela.setScene(new Scene(root));
        janela.initStyle(StageStyle.UTILITY);
        janela.initModality(Modality.APPLICATION_MODAL);
        janela.setResizable(false);

        janela.show();
    }
}
