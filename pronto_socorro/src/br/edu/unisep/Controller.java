package br.edu.unisep;

import br.edu.unisep.model.dao.AtendimentoDAO;
import br.edu.unisep.model.vo.AtendimentoVO;
import javafx.beans.property.Property;
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

    @FXML private TableView<AtendimentoVO> tbAtendiemnto;
    @FXML private TableColumn<AtendimentoVO, String> paciente;
    @FXML private TableColumn<AtendimentoVO, String> planoSaude;
    @FXML private TableColumn<AtendimentoVO, String> especialidade;
    @FXML private TableColumn<AtendimentoVO, String> status;
    @FXML private TableColumn<AtendimentoVO, String> dataNascimento;

    @FXML private Button btAtendido;

    private ObservableList<AtendimentoVO> atendimentos;
    private AtendimentoDAO dao;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        atendimentos = FXCollections.observableArrayList();
        tbAtendiemnto.setItems(atendimentos);

        paciente.setCellValueFactory(new PropertyValueFactory<>("paciente"));
        planoSaude.setCellValueFactory(new PropertyValueFactory<>("plano de saude"));
        especialidade.setCellValueFactory(new PropertyValueFactory<>("especialidade"));
        status.setCellValueFactory( cell -> {
            var l = cell.getValue();

            if (l.getTp_status() == 1){
                return new SimpleStringProperty("Aguardando Atendimento");
            } else {
                return new SimpleStringProperty("Atendido");
            }
        });
        dataNascimento.setCellValueFactory(new PropertyValueFactory<>("data de nascimento"));

        dao = new AtendimentoDAO();
        listar();


    }


    public void novoAtendimento(ActionEvent event) throws IOException {

        var loader = new FXMLLoader();
        var root = (Parent) loader.load(getClass().getResource("novoAtendimento.fxml"));

        var janela = new Stage();
        janela.setScene(new Scene(root));
        janela.initStyle(StageStyle.UTILITY);
        janela.initModality(Modality.APPLICATION_MODAL);
        janela.setResizable(false);

        janela.show();
    }


    public void listar(){
        var lista = dao.listar();

        atendimentos.setAll(lista);

        tbAtendiemnto.getSelectionModel().clearSelection();
    }

    public void selecionarAtendimento(MouseEvent event) throws IOException {
        if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2){
            var atendimentoSel = tbAtendiemnto.getSelectionModel().getSelectedItem();


        }
    }

    public void abrirModal(ActionEvent event) throws IOException{
        abrirNovo();
    }

    private void abrirNovo(AtendimentoVO... atendimentoSel) throws IOException {
        var loader = new FXMLLoader((getClass().getResource("novoAtendimento.fxml")));
        var root = (Parent) loader.load();

        var ctrl = (NovoAtendimentoController) loader.getController();

        var janela = new Stage();
        janela.setScene(new Scene(root));
        janela.initStyle(StageStyle.UTILITY);
        janela.initModality(Modality.APPLICATION_MODAL);

        janela.setResizable(false);

        ctrl.setJanela(janela);
        ctrl.setCtrlLista(this);

        if (atendimentoSel.length != 0){
            ctrl.exibirDadosAlteracao(atendimentoSel[0]);
        }

        janela.show();
    }


}
