package dao.impl;

//import dto.CustomDTO;


import dao.QueryDAO;
import dto.ReservationDetailDTO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import utill.FactoryConfiguration;

import java.util.ArrayList;
import java.util.List;

public class QueryDAOImpl implements QueryDAO {


    @Override
    public ArrayList<ReservationDetailDTO> getDetails() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "SELECT r.res_id,r.date,c.PID,c.courseName,c.duration,c.fee FROM Room c LEFT JOIN Reserve r ON c.room_id = r.room.room_id";
        Query query = session.createQuery(hql);
        ArrayList<ReservationDetailDTO> details = new ArrayList<>();

        List<Object[]> list = query.list();
        for (Object[] objects : list) {
            details.add(new ReservationDetailDTO(
                    (String) objects[0],
                    (String) objects[1],
                    (String) objects[2],
                    (String) objects[3],
                    (String) objects[4],
                    (String) objects[5],
                    (String) objects[6],
                    (String) objects[7],
                    (double) objects[8]
            ));
        }

        transaction.commit();
        session.close();

        return details;
    }
}
