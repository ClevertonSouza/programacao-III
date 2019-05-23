package br.edu.unisep;

import br.edu.unisep.fx.annotation.FXColumn;
import br.edu.unisep.fx.annotation.Required;
import br.edu.unisep.fx.controller.AppController;
import br.edu.unisep.model.dao.CursoDAO;
import br.edu.unisep.model.dao.DisciplinaDAO;
import br.edu.unisep.model.vo.CursoVO;
import br.edu.unisep.model.vo.DisciplinaVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class DisciplinasController extends AppController {

    @FXML private TableView<DisciplinaVO> tabDisciplinas;

    @Required(campo = "curso")
    @FXML private ChoiceBox<CursoVO> cmbCurso;
    @FXColumn(property = "nome",percentWidth = 35)
    @FXML private TableColumn<DisciplinaVO,String> colDisciplina;
    @FXColumn(property = "professor",percentWidth = 25)
    @FXML private TableColumn<DisciplinaVO,String> colProfessor;
    @FXColumn(property = "cargaHoraria",percentWidth = 20)
    @FXML private TableColumn<DisciplinaVO,String> colCarga;
    @FXColumn(property = "curso",percentWidth = 10)
    @FXML private TableColumn<DisciplinaVO,String> colCurso;
    @FXColumn(property = "periodo",percentWidth = 10)
    @FXML private TableColumn<DisciplinaVO,String> colPeriodo;

    private ObservableList<DisciplinaVO> disciplinas;
    private DisciplinaDAO dao;
    private CursoDAO daoC;

    @Override
    protected void onInit() {

        disciplinas = FXCollections.observableArrayList();
        tabDisciplinas.setItems(disciplinas);

        dao = new DisciplinaDAO();
        daoC = new CursoDAO();
        listar();
    }

    public void abrirNovo(ActionEvent event) throws IOException {
        openModal("novaDisciplina.fxml", this::listar);
    }

    public void listar(){
        var daoC = new CursoDAO();
        var lst = daoC.listar();
        cmbCurso.setItems(FXCollections.observableList(lst));

        var lista = dao.listar();
        disciplinas.setAll(lista);
    }

    public void buscar(ActionEvent event){
        var lst = dao.listar(cmbCurso.getValue());
        disciplinas.setAll(lst);
    }
}
