package br.edu.unisep;

import br.edu.unisep.fx.annotation.FXColumn;
import br.edu.unisep.fx.annotation.Required;
import br.edu.unisep.fx.controller.AppController;
import br.edu.unisep.model.dao.CursoDAO;
import br.edu.unisep.model.dao.ProfessorDAO;
import br.edu.unisep.model.vo.CursoVO;
import br.edu.unisep.model.vo.ProfessorVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ProfessoresController extends AppController {

    @Required(campo = "curso")
    @FXML private ChoiceBox<CursoVO> cmbCurso;

    @FXML private TableView<ProfessorVO> tabProfessor;

    @FXColumn(property = "nome", percentWidth = 60)
    @FXML private TableColumn<ProfessorVO, String> colNome;

    @FXColumn(property = "email", percentWidth = 40)
    @FXML private TableColumn<ProfessorVO, String> colEmail;

    private ObservableList<ProfessorVO> professores;
    private ProfessorDAO dao;


    @Override
    protected void onInit() {
        professores = FXCollections.observableArrayList();
        tabProfessor.setItems(professores);

        dao = new ProfessorDAO();
        listar();

        listarCursos();
    }

    public void abrirNovo(ActionEvent event){
        openModal("novoProfessor.fxml", this::listar);
    }


    public void listar(){
        var lista = dao.listar();
        professores.setAll(lista);
    }

    public void listarBusca(ActionEvent event){
        var lista = dao.listar(cmbCurso.getValue());
        professores.setAll(lista);
    }

    public void listarCursos(){
        var dao = new CursoDAO();
        var lista = dao.listar();

        cmbCurso.setItems(FXCollections.observableList(lista));
    }

}
