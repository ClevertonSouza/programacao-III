package br.edu.unisep;

import br.edu.unisep.model.dao.AutorDAO;
import br.edu.unisep.model.dao.LivroDAO;
import br.edu.unisep.model.vo.AutorVO;
import br.edu.unisep.model.vo.LivroVO;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class NovoLivroController implements Initializable {

    @FXML private TextField txtTitulo;
    @FXML private TextField txtEditora;
    @FXML private TextField txtPaginas;

    @FXML private TextArea txtSinopse;

    @FXML private ChoiceBox<AutorVO> cmbAutor;

    private Stage janela;
    private Controller controller;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        listarAutores();

    }

    private void listarAutores () {

        var dao = new AutorDAO();
        var lista = dao.listar();

        cmbAutor.setItems(FXCollections.observableList(lista));
    }

    public void salvar(ActionEvent e){

        var livro = new LivroVO();
        livro.setTitulo(txtTitulo.getText());
        livro.setPaginas(Integer.valueOf(txtPaginas.getText()));
        livro.setEditora(txtEditora.getText());
        livro.setSinopse(txtSinopse.getText());
        livro.setStatus(1);

        livro.setAutor(cmbAutor.getValue());

        var dao = new LivroDAO();
        dao.salvar(livro);

        janela.close();
        controller.listar();
    }

    public void cancelar(ActionEvent e){
        janela.close();

    }

    public Stage getJanela() {
        return janela;
    }

    public void setJanela(Stage janela) {
        this.janela = janela;
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
}
