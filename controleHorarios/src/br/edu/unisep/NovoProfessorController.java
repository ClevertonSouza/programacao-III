package br.edu.unisep;

import br.edu.unisep.fx.annotation.Required;
import br.edu.unisep.fx.controller.ModalController;
import br.edu.unisep.model.dao.ProfessorDAO;
import br.edu.unisep.model.vo.ProfessorVO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class NovoProfessorController extends ModalController {


    @Required(campo = "nome")
    @FXML private TextField txtNome;

    @Required(campo = "email")
    @FXML private TextField txtEmail;

    private ProfessorVO professor = new ProfessorVO();

    @Override
    protected void onModalInit() {

    }


    public void salvar(ActionEvent event){

        if (validate()){
            professor.setNome(txtNome.getText().trim());
            professor.setEmail(txtEmail.getText().trim());

            var dao = new ProfessorDAO();

            dao.salvar(professor);

            closeModal();
        }
    }

    public void cancelar(ActionEvent event){
        closeModal();
    }

}
