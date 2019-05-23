package br.edu.unisep.model.dao;

import br.edu.unisep.model.hibernate.HibernateSessionFactory;
import br.edu.unisep.model.vo.ProdutoVO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    public void salvar(ProdutoVO prod) {

        var session = HibernateSessionFactory.getSession();
        var trans = session.beginTransaction();

        try {
            session.save(prod);
            trans.commit();

        } catch(Exception e) {
            e.printStackTrace();
            trans.rollback();
        }
        session.close();
    }

    public List<ProdutoVO> listar() {

        var session = HibernateSessionFactory.getSession();
        var query = session.createQuery("from ProdutoVO", ProdutoVO.class);
        var produtos = query.list();

        session.close();

        return produtos;
    }

    public void excluir(ProdutoVO prod) {

        var session = HibernateSessionFactory.getSession();
        var trans = session.beginTransaction();

        try {
            session.delete(prod);
            trans.commit();

        } catch(Exception e) {
            e.printStackTrace();
            trans.rollback();
        }
        session.close();
    }

    public void alterar(ProdutoVO prod) {

        var session = HibernateSessionFactory.getSession();
        var trans = session.beginTransaction();

        try {
            session.update(prod);
            trans.commit();

        } catch(Exception e) {
            e.printStackTrace();
            trans.rollback();
        }
        session.close();
    }
}
