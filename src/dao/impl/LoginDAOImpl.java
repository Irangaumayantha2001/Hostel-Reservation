package dao.impl;

import dao.custom.LoginDAO;
import entity.Login;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utill.FactoryConfiguration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LoginDAOImpl  implements LoginDAO {
    @Override
    public boolean add(Login login) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Serializable save = session.save(login);

        transaction.commit();
        session.close();

        if (save != null){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean delete(String s) {
        throw new UnsupportedOperationException("No Supported Yet.");
    }

    @Override
    public boolean update(Login login) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(login);

        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public Login search(String s) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "FROM Login WHERE userName= :name";
        Query hqlQuery = session.createQuery(hql);
        Query query = hqlQuery.setParameter("name", s);

        List<Login> list = query.list();

        Login login = session.load(Login.class, list.get(0).getUserId());

        transaction.commit();
        session.close();

        return login;
    }

    @Override
    public ArrayList<Login> getAll() {
        throw new UnsupportedOperationException("No Supported Yet.");
    }
}
