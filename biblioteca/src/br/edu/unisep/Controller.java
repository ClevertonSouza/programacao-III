package br.edu.unisep;

import br.edu.unisep.model.DAO.LivroDAO;
import br.edu.unisep.model.vo.LivroVO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML private TableView<LivroVO> tabLivros;

    @FXML private TableColumn<LivroVO, String> colTitulo;
    @FXML private TableColumn<LivroVO, String> colAutor;
    @FXML private TableColumn<LivroVO, String> colEditora;
    @FXML private TableColumn<LivroVO, Integer> colPaginas;
    @FXML private TableColumn<LivroVO, String> colStatus;

    @FXML private Button btnLendo;
    @FXML private Button btnTerminado;

    private ObservableList<LivroVO> livros;
    private LivroDAO dao;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){

        livros = FXCollections.observableArrayList();
        tabLivros.setItems(livros);

        colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
        colEditora.setCellValueFactory(new PropertyValueFactory<>("editora"));
        colPaginas.setCellValueFactory(new PropertyValueFactory<>("paginas"));
        colStatus.setCellValueFactory( cell ->{
            var l = cell.getValue();

            if(l.getStatus() == 1){
                return new SimpleStringProperty("Não lido");
            }else if(l.getStatus() == 2){
                return new SimpleStringProperty("Lendo");
            }else{
                return new SimpleStringProperty("Lido");
            }
        });

        colTitulo.setPrefWidth(Integer.MAX_VALUE * 35d);
        colAutor.setPrefWidth(Integer.MAX_VALUE * 25d);
        colEditora.setPrefWidth(Integer.MAX_VALUE * 20d);
        colStatus.setPrefWidth(Integer.MAX_VALUE * 10d);
        colPaginas.setPrefWidth(Integer.MAX_VALUE * 10d);
        dao = new LivroDAO();
        listar();

        configurarSelecaoGrid();

    }

    private void configurarSelecaoGrid(){
        tabLivros.getSelectionModel().selectedItemProperty().addListener(
                ((observableValue, anterior, novo) -> {
                    btnLendo.setDisable(true);
                    btnTerminado.setDisable(true);

                    if (novo != null) {
                        if (novo.getStatus() == 1) {
                            btnLendo.setDisable(false);
                        } else if (novo.getStatus() == 2) {
                            btnTerminado.setDisable(false);
                        }
                    }
                })
        );
    }



    public void abrirNovo(ActionEvent event) throws IOException {

        abrirModal();

    }

    private void abrirModal(LivroVO... livroSel) throws IOException {
        var loader = new FXMLLoader((getClass().getResource("novo.fxml")));
        var root = (Parent) loader.load();

        var ctrl = (NovoLivroController) loader.getController();

        var janela = new Stage();
        janela.setScene(new Scene(root));
        janela.initStyle(StageStyle.UTILITY);
        janela.initModality(Modality.APPLICATION_MODAL);

        janela.setResizable(false);

        ctrl.setJanela(janela);
        ctrl.setCtrlLista(this);

        if (livroSel.length != 0){
            ctrl.exibirDadosAlteracao(livroSel[0]);
        }

        janela.show();
    }

    public void selecionarLivro(MouseEvent event) throws IOException{
        if (event.getButton() == MouseButton.PRIMARY &&
                event.getClickCount() ==2){
            var livroSel = tabLivros.getSelectionModel().getSelectedItem();
            abrirModal(livroSel);
        }
    }

    public void listar(){
        var lst = dao.listar();
        livros.setAll(lst);

        tabLivros.getSelectionModel().clearSelection();
    }


    public void alterarStatus(ActionEvent event){
        var livro = tabLivros.getSelectionModel().getSelectedItem();
        livro.setStatus(livro.getStatus() + 1);

        dao.alterar(livro);

        listar();
    }
}