package dao.impl;

import dao.custom.RoomDAO;
import entity.Room;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import utill.FactoryConfiguration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RoomDAOImpl implements RoomDAO {


    @Override
    public String generateRoomID() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String sql = "SELECT * FROM Room ORDER BY room_id DESC LIMIT 1";
        NativeQuery sqlQuery = session.createSQLQuery(sql);
        sqlQuery.addEntity(Room.class);
        List<Room> list = sqlQuery.list();
        String id = null;

        for (Room course : list) {
            id = course.getRoom_id();
        }

        transaction.commit();
        session.close();

        if (id != null){
            //if data has in database ,split orderId
            int tempId = Integer.parseInt(id.split("-")[1]);
            tempId = tempId+1;

            if (tempId <= 9){
                return "RM-000"+tempId;
            }else if (tempId <= 99) {
                return "RM-00" + tempId;
            }else if (tempId <= 999){
                return "RM-0" + tempId;
            }else {
                return "RM-"+tempId;
            }
        }else {
            //if no data in database
            return "RM-0001";
        }
    }

    @Override
    public ArrayList<Room> getRoomDetails(String name) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "FROM Room WHERE type=:cName";
        Query hqlQuery = session.createQuery(hql);
        Query query = hqlQuery.setParameter("cName", name);

        List<Room> list = query.list();
        ArrayList<Room> courses = new ArrayList<>();

        for (Room course : list) {
            courses.add(course);
        }

        transaction.commit();
        session.close();

        return courses;
    }

    @Override
    public boolean add(Room room) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Serializable save = session.save(room);

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

        Room course = session.load(Room.class, s);
        session.delete(course);

        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public boolean update(Room room) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(room);

        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public Room search(String s) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Room course = session.load(Room.class, s);

        transaction.commit();
        session.close();

        return course;
    }

    @Override
    public ArrayList<Room> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "FROM Room ";
        Query query = session.createQuery(hql);

        List<Room> list = query.list();
        ArrayList<Room> courses = new ArrayList<>();

        for (Room course : list) {
            courses.add(course);
        }

        transaction.commit();
        session.close();

        return courses;
    }
}
