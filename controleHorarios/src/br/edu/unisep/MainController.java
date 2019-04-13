package br.edu.unisep;

import br.edu.unisep.fx.controller.AppController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import javax.swing.*;

//extends appControler para importar a biblioteca do Sr.Roberto Padilha
public class MainController extends AppController {

    @FXML private AnchorPane conteudo;

    @Override
    protected void onInit() {

    }


    public void abrirCursos(ActionEvent event){
        openScene(conteudo, "cursos.fxml");
    }

    public void abrirProfessores(ActionEvent event){
        openScene(conteudo, "professor.fxml");
    }
    public void abrirDisciplinas(ActionEvent event){
        openScene(conteudo, "disciplina.fxml");
    }
}
