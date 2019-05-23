package br.edu.unisep.model.vo;

import javax.persistence.*;

@Entity
@Table(name = "produtos")
public class ProdutoVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produto")
    private Integer id;

    @Column(name = "nome")
    private String descricao;

    @Column(name = "valor_mercadoa")
    private Double mercadoA;

    @Column(name = "valor_mercadob")
    private Double mercadoB;

    @Column(name = "valor_mercadoc")
    private Double mercadoC;


    public Double getMaior() {
        if (mercadoA > mercadoB && mercadoA > mercadoC) {
            return mercadoA;
        } else if (mercadoB > mercadoA && mercadoB > mercadoC) {
            return mercadoB;
        } else {
            return mercadoC;
        }
    }

    public Double getMenor() {
        if (mercadoA < mercadoB && mercadoA < mercadoC) {
            return mercadoA;
        } else if (mercadoB < mercadoA && mercadoB < mercadoC) {
            return mercadoB;
        } else {
            return mercadoC;
        }
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getMercadoA() {
        return mercadoA;
    }

    public void setMercadoA(Double mercadoA) {
        this.mercadoA = mercadoA;
    }

    public Double getMercadoB() {
        return mercadoB;
    }

    public void setMercadoB(Double mercadoB) {
        this.mercadoB = mercadoB;
    }

    public Double getMercadoC() {
        return mercadoC;
    }

    public void setMercadoC(Double mercadoC) {
        this.mercadoC = mercadoC;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
