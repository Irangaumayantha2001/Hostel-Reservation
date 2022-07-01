package dao.impl;

import dao.custom.ReservationDAO;
import entity.Reserve;
import entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import utill.FactoryConfiguration;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAOImpl implements ReservationDAO {


    @Override
    public String generateNewReservId()  {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String sql = "SELECT * FROM Reserve ORDER BY res_id DESC LIMIT 1";
        NativeQuery sqlQuery = session.createSQLQuery(sql);
        sqlQuery.addEntity(Reserve.class);
        List<Reserve> list = sqlQuery.list();
        String id = null;

        for (Reserve register : list) {
            id = register.getRes_id();
        }

        transaction.commit();
        session.close();

        if (id != null){
            //if data has in database ,split orderId
            int tempId = Integer.parseInt(id.split("-")[1]);
            tempId = tempId+1;

            if (tempId <= 9){
                return "RE-000"+tempId;
            }else if (tempId <= 99) {
                return "RE-00" + tempId;
            }else if (tempId <= 999){
                return "RE-0" + tempId;
            }else {
                return "RE-"+tempId;
            }
        }else {
            //if no data in database
            return "RE-0001";
        }
    }

    @Override
    public Reserve searchRegisterObject(Student student) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "FROM Reserve WHERE student= :studentObject";
        Query hqlQuery = session.createQuery(hql);
        Query query = hqlQuery.setParameter("studentObject", student);

        List<Reserve> list = query.list();

        Reserve register = session.load(Reserve.class, list.get(0).getRes_id());

        transaction.commit();
        session.close();

        return register;
    }

    @Override
    public boolean add(Reserve reserve) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Serializable save = session.save(reserve);

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
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Reserve register = session.get(Reserve.class, s);
        session.delete(register);

        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public boolean update(Reserve reserve) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(reserve);

        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public Reserve search(String s) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Reserve register = session.get(Reserve.class, s);

        transaction.commit();
        session.close();

        return register;
    }

    @Override
    public ArrayList<Reserve> getAll() {
        throw new UnsupportedOperationException("No Supported Yet.");
    }
}
