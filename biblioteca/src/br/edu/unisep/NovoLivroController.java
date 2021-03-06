package br.edu.unisep;

import br.edu.unisep.model.DAO.AutorDAO;
import br.edu.unisep.model.DAO.LivroDAO;
import br.edu.unisep.model.vo.AutorVO;
import br.edu.unisep.model.vo.LivroVO;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class NovoLivroController implements Initializable {

    @FXML private TextField txtTitulo;
    @FXML private TextField txtEditora;
    @FXML private TextField txtPaginas;

    @FXML private TextArea txtSinopse;

    @FXML private ChoiceBox<AutorVO> cmbAutor;

    @FXML private Label lblTitulo;

    private Stage janela;
    private Controller ctrlLista;

    private LivroVO livro = new LivroVO();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){

        listarAutores();
    }

    public void exibirDadosAlteracao(LivroVO livroSel){
        livro.setId(livroSel.getId());
        livro.setStatus(livroSel.getStatus());

        txtTitulo.setText(livroSel.getTitulo());
        txtEditora.setText(livroSel.getEditora());
        txtPaginas.setText(livroSel.getPaginas().toString());
        txtSinopse.setText(livroSel.getSinopse());
        cmbAutor.setValue(livroSel.getAutor());

        lblTitulo.setText("Alterar Livro");
    }


    private void listarAutores() {
        var dao = new AutorDAO();
        var lista = dao.listar();

        cmbAutor.setItems(FXCollections.observableList(lista));

    }

    public void salvar(ActionEvent event){

        if (validar()) {

            livro.setTitulo(txtTitulo.getText().trim());
            livro.setPaginas(Integer.valueOf(txtPaginas.getText().trim()));
            livro.setEditora(txtEditora.getText().trim());
            livro.setSinopse(txtSinopse.getText().trim());
            livro.setStatus(1);

            //Obtem o objeto AutorVO selecionado na combo de autores
            //e valoriza o atributo autor do livro;
            livro.setAutor(cmbAutor.getValue());

            var dao = new LivroDAO();

            if (livro.getId() != null) {
                dao.alterar(livro);
            } else {
                dao.salvar(livro);
            }

            janela.close();
            ctrlLista.listar();
        }
    }

    private boolean validar(){

        if (txtTitulo.getText().trim().length() == 0){
            return exibirMsgErro("Titulo é obrigatório!");
        }

        if (cmbAutor.getValue() == null){
            return exibirMsgErro("Autor é obrigatório!");
        }

        if (txtEditora.getText().trim().length() == 0){
            return exibirMsgErro("Editora é obrigatório!");
        }


        try {
            Integer.parseInt(txtPaginas.getText());
        } catch (NumberFormatException e){
            exibirMsgErro("Campo página inválido!");
        }

        if (txtSinopse.getText().trim().length() == 0){
            return exibirMsgErro("Sinópse é obrigatório!");
        }



        return true;
    }

    private boolean exibirMsgErro(String s) {
        var msg = new Alert(Alert.AlertType.ERROR, s);

        msg.setHeaderText(null);
        msg.show();

        return false;
    }

    public void cancelar(ActionEvent event){
        janela.close();
    }

    public Stage getJanela() {
        return janela;
    }

    public void setJanela(Stage janela) {
        this.janela = janela;
    }

    public Controller getCtrlLista() {
        return ctrlLista;
    }

    public void setCtrlLista(Controller ctrlLista) {
        this.ctrlLista = ctrlLista;
    }
}
