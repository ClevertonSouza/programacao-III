package br.edu.unisep;

import br.edu.unisep.fx.annotation.FXColumn;
import br.edu.unisep.fx.annotation.OnlyNumber;
import br.edu.unisep.fx.annotation.Required;
import br.edu.unisep.fx.controller.ModalController;
import br.edu.unisep.model.dao.CursoDAO;
import br.edu.unisep.model.vo.CursoVO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class NovoCursoController extends ModalController {

    @Required(campo = "Nome")
    @FXML private TextField txtNome;

    @Required(campo = "Duração")
    @OnlyNumber
    @FXML private TextField txtDuracao;

    @FXML private RadioButton rbBacharelado;

    @FXML private RadioButton rbLicenciatura;


    private CursoVO curso = new CursoVO();


    @Override
    protected void onModalInit() {

    }


    public void salvar(ActionEvent event){

        if (validate()){
            curso.setNome(txtNome.getText().trim());
            curso.setDuracao(Integer.valueOf(txtDuracao.getText().trim()));

            if (rbBacharelado.isSelected()) {
                curso.setTipo(1);
            } else if (rbLicenciatura.isSelected()){
                curso.setTipo(2);
            } else {
                curso.setTipo(3);
            }

            var dao = new CursoDAO();
            dao.salvar(curso);

            closeModal();
        }
    }

    public void cancelar(){
        closeModal();
    }
}
