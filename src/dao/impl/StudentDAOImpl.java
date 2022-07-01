package dao.impl;

import dao.custom.StudentDAO;
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

public class StudentDAOImpl implements StudentDAO {


    @Override
    public String generateStudentID() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String sql = "SELECT * FROM Student ORDER BY  student_id DESC LIMIT 1";
        NativeQuery sqlQuery = session.createSQLQuery(sql);
        sqlQuery.addEntity(Student.class);
        List<Student> list = sqlQuery.list();
        String id = null;

        for (Student course : list) {
            id = course.getStudent_id();
        }

        transaction.commit();
        session.close();

        if (id != null){
            //if data has in database ,split orderId
            int tempId = Integer.parseInt(id.split("-")[1]);
            tempId = tempId+1;

            if (tempId <= 9){
                return "S-000"+tempId;
            }else if (tempId <= 99) {
                return "S-00" + tempId;
            }else if (tempId <= 999){
                return "S-0" + tempId;
            }else {
                return "S-"+tempId;
            }
        }else {
            //if no data in database
            return "S-0001";
        }
    }

    @Override
    public boolean saveRegisterDetails(Reserve register, String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        boolean b;

        String hql = "UPDATE Student SET reserveList =:Rlist WHERE student_id = :ID";
        Query query = session.createQuery(hql);
        query.setParameter("Rlist", register);
        query.setParameter("ID", id);

        Student student = session.get(Student.class,id);

        if (student.getStudent_id().equals(id)){
            b = true;
        }else {
            b = false;
        }

        transaction.commit();
        session.close();

        return b;
    }

    @Override
    public int countStudent() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "FROM Student ";
        Query query = session.createQuery(hql);

        List<Student> list = query.list();

        int count = 0;

        for (Student course : list) {
            count = count+1;
        }

        transaction.commit();
        session.close();

        return count;
    }


    @Override
    public boolean add(Student student) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Serializable save = session.save(student);

        transaction.commit();
        session.close();

        if (save != null) {
            return true;
        } else {
            return false;
        }
    }


    @Override
    public boolean delete(String s) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Student course = session.load(Student.class,s);
        session.delete(course);

        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public boolean update(Student student) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(student);

        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public Student search(String s) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Student course = session.load(Student.class, s);

        transaction.commit();
        session.close();

        return course;
    }


    @Override
    public ArrayList<Student> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "FROM Student";
        Query query = session.createQuery(hql);

        List<Student> list = query.list();
        ArrayList<Student> courses = new ArrayList<>();

        for (Student course : list) {
            courses.add(course);
        }

        transaction.commit();
        session.close();

        return courses;
    }
}
