package br.edu.unisep;

import br.edu.unisep.model.dao.AtendimentoDAO;
import br.edu.unisep.model.dao.EspecialidadeDAO;
import br.edu.unisep.model.dao.PlanoSaudeDAO;
import br.edu.unisep.model.vo.AtendimentoVO;
import br.edu.unisep.model.vo.EspecialidadeVO;
import br.edu.unisep.model.vo.PlanoSaudeVO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class NovoAtendimentoController implements Initializable {


    @FXML private TextField paciente;
    @FXML private ChoiceBox<PlanoSaudeVO> planoSaude;
    @FXML private ChoiceBox<EspecialidadeVO> especialidade;
    @FXML private DatePicker nascimento;

    private Stage janela;
    private Controller ctrlLista;

    private AtendimentoVO atendimento = new AtendimentoVO();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        listarPlanoSaude();
        listarEspecialidade();
    }

    public void exibirDadosAlteracao(AtendimentoVO atendimentoSel){
        atendimento.setId(atendimentoSel.getId());
        atendimento.setTp_status(atendimentoSel.getTp_status());

        paciente.setText(atendimentoSel.getDs_paciente());
        planoSaude.setValue(atendimentoSel.getId_plano());
        especialidade.setValue(atendimentoSel.getId_especialidade());
        nascimento.setValue(atendimentoSel.getDt_nascimento());
    }

    private void listarPlanoSaude(){
        var dao = new PlanoSaudeDAO();
        var listar = dao.listar();
    }

    private void listarEspecialidade(){
        var dao = new EspecialidadeDAO();
        var listar =  dao.listar();
    }
}
