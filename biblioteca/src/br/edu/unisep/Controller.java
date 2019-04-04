package br.edu.unisep;

import br.edu.unisep.model.dao.LivroDAO;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.imageio.IIOException;
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

    private ObservableList<LivroVO> livros;
    private LivroDAO dao;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        livros = FXCollections.observableArrayList();
        tabLivros.setItems(livros);

        colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
        colEditora.setCellValueFactory(new PropertyValueFactory<>( "editora"));
        colPaginas.setCellValueFactory(new PropertyValueFactory<>("paginas"));

        colStatus.setCellValueFactory( cell -> {
            var l = cell.getValue();

            if (l.getStatus() == 1){
                return new SimpleStringProperty("Não Lido");
            } else if (l.getStatus() == 2){
                return new SimpleStringProperty("Lendo");
            } else {
                return new SimpleStringProperty("Lido");
            }
        });

        dao = new LivroDAO();
        listar();
    }

    public void listar (){
        var list = dao.listar();
        livros.setAll(list);
    }

    public void abrirNovo (ActionEvent e) throws IOException {

            var loader = new FXMLLoader(getClass().getResource("novo.fxml"));
            var root = (Parent) loader.load();
            var janela = new Stage();

            var controller = (NovoLivroController) loader.getController();

            janela.setScene(new Scene(root));
            janela.initStyle(StageStyle.UTILITY);
            janela.initModality(Modality.APPLICATION_MODAL);
            janela.setResizable(false);

            controller.setJanela(janela);


            janela.show();
    }
}
