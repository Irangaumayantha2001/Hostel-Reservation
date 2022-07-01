package dao;

import dao.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDAOFactory() {
        if (daoFactory == null) {
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    public SuperDAO getDAO(DAOTypes types) {
        switch (types) {
            case STUDENT:
                return new StudentDAOImpl();
            case ROOM:
                return new RoomDAOImpl();
            case RESERVATION:
                return new ReservationDAOImpl() ;

            case LOGIN:
                return new LoginDAOImpl();

            case QUERYDAO:
                return new QueryDAOImpl();
            default:
                return null;
        }
    }

    public enum DAOTypes {
        LOGIN,STUDENT, ROOM , RESERVATION ,QUERYDAO
    }
}
