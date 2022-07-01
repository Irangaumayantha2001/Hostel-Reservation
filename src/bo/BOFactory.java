package bo;

import bo.custom.SuperBO;
import bo.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getBOFactory() {
        if (boFactory == null) {
            boFactory = new BOFactory();
        }
        return boFactory;
    }

    public SuperBO getBO(BoTypes types) {
        switch (types) {
            case ROOM:
                return new RoomBOImpl();
            case STUDENT:
                return new StudentBOImpl();
            case RESERVATION_ROOM:
                return new ReservationBOImpl();

            case LOGIN:
                return new LoginBOImpl();

            case QUERY:
                return new QueryBoImpl();
            default:
                return null;
        }
    }

    public enum BoTypes {
        LOGIN,ROOM,STUDENT,RESERVATION_ROOM,QUERY
    }
}
