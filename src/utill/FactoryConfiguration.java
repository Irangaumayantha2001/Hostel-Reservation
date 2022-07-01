package utill;

import entity.Login;
import entity.Reserve;
import entity.Room;
import entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfigeration;
    private SessionFactory sessionFactory;

    private FactoryConfiguration(){
        Properties p = new Properties();

        try {
            p.load(ClassLoader.getSystemClassLoader().getResourceAsStream("resourses/hibernate.properties"));
        } catch (Exception e) {
        }
        Configuration configuration = new Configuration();
        configuration.mergeProperties(p)
                .addAnnotatedClass(Login.class)
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Room.class)
                .addAnnotatedClass(Reserve.class);


        sessionFactory=configuration.buildSessionFactory();

    }
    public static FactoryConfiguration getInstance(){
        return (factoryConfigeration==null)? factoryConfigeration=new FactoryConfiguration() : factoryConfigeration;
    }

    public Session getSession(){
        return sessionFactory.openSession();
    }
}
