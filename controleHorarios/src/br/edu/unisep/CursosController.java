package br.edu.unisep;

import br.edu.unisep.fx.annotation.ColValueMap;
import br.edu.unisep.fx.annotation.FXColumn;
import br.edu.unisep.fx.annotation.IntToString;
import br.edu.unisep.fx.controller.AppController;
import br.edu.unisep.model.dao.CursoDAO;
import br.edu.unisep.model.vo.CursoVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class CursosController extends AppController {

    @FXML private TableView tabCursos;

    @FXColumn(property = "nome", percentWidth = 40)
    @FXML private TableColumn<CursoVO, String> colCurso;

    @FXColumn(property = "duracao", percentWidth = 30)
    @FXML private TableColumn<CursoVO, Integer> colDuracao;

    @FXColumn(property = "tipo", percentWidth = 30)
    @ColValueMap({
        @IntToString(from = 1, to = "Bacharelado"),
        @IntToString(from = 2, to = "Licenciatura"),
        @IntToString(from = 3, to = "Tecnologia")
    })
    @FXML private TableColumn<CursoVO, String> colTipo;


    private ObservableList<CursoVO> cursos;
    private CursoDAO dao;

    @Override
    public void onInit(){
        cursos = FXCollections.observableArrayList();
        tabCursos.setItems(cursos);

        dao = new CursoDAO();
        listar();
    }

    public void listar(){
        var lista = dao.listar();
        cursos.setAll(lista);
    }


    public void abrirNovo(){
        openModal("novoCurso.fxml", this::listar);
    }


}
