package br.edu.unisep;

import br.edu.unisep.fx.annotation.Required;
import br.edu.unisep.fx.controller.ModalController;
import br.edu.unisep.model.dao.CursoDAO;
import br.edu.unisep.model.dao.DisciplinaDAO;
import br.edu.unisep.model.dao.ProfessorDAO;
import br.edu.unisep.model.vo.CursoVO;
import br.edu.unisep.model.vo.DisciplinaVO;
import br.edu.unisep.model.vo.ProfessorVO;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class NovaDisciplinaController extends ModalController {

    @Required(campo = "nome")
    @FXML private TextField txtNome;
    @Required(campo = "curso")
    @FXML private ChoiceBox<CursoVO> chCurso;
    @Required(campo = "Professor")
    @FXML private ChoiceBox<ProfessorVO> chProfessor;
    @Required(campo = "cargaHoraria")
    @FXML private TextField txtCarga;
    @Required(campo = "periodo")
    @FXML private TextField txtPeriodo;

    private DisciplinaVO disciplina = new DisciplinaVO();

    @Override
    public void onModalInit() {

        listarCursos();
        listarProfessores();

    }

    public void salvar() {

        if (validate()) {
            disciplina.setNome(txtNome.getText().trim());
            disciplina.setCargaHoraria(Integer.valueOf(txtCarga.getText().trim()));
            disciplina.setPeriodo(Integer.valueOf(txtPeriodo.getText().trim()));
            disciplina.setCurso(chCurso.getValue());
            disciplina.setProfessor(chProfessor.getValue());

        }

        var dao = new DisciplinaDAO();

        dao.salvar(disciplina);
        closeModal();

    }
    private void listarCursos () {
        var dao = new CursoDAO();
        var lista = dao.listar();

        chCurso.setItems(FXCollections.observableList(lista));
    }
    private void listarProfessores () {
        var dao = new ProfessorDAO();
        var lista = dao.listar();

        chProfessor.setItems(FXCollections.observableList(lista));
    }



    public void cancelar(ActionEvent event){closeModal();}
}


