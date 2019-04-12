package br.edu.unisep.model.vo;

import java.sql.Date;
import java.time.LocalDate;

public class AtendimentoVO {

    private Integer id;
    private String ds_paciente;
    private PlanoSaudeVO id_plano;
    private EspecialidadeVO id_especialidade;
    private LocalDate dt_nascimento;
    private String ds_sintomas;
    private Integer tp_status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDs_paciente() {
        return ds_paciente;
    }

    public void setDs_paciente(String ds_paciente) {
        this.ds_paciente = ds_paciente;
    }

    public PlanoSaudeVO getId_plano() {
        return id_plano;
    }

    public void setId_plano(PlanoSaudeVO id_plano) {
        this.id_plano = id_plano;
    }

    public EspecialidadeVO getId_especialidade() {
        return id_especialidade;
    }

    public void setId_especialidade(EspecialidadeVO id_especialidade) {
        this.id_especialidade = id_especialidade;
    }

    public LocalDate getDt_nascimento() {
        return dt_nascimento;
    }

    public void setDt_nascimento(LocalDate dt_nascimento) {
        this.dt_nascimento = dt_nascimento;
    }

    public String getDs_sintomas() {
        return ds_sintomas;
    }

    public void setDs_sintomas(String ds_sintomas) {
        this.ds_sintomas = ds_sintomas;
    }

    public Integer getTp_status() {
        return tp_status;
    }

    public void setTp_status(Integer tp_status) {
        this.tp_status = tp_status;
    }
}
