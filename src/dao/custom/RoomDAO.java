package dao.custom;

import dao.CrudDAO;
import entity.Room;


import java.util.ArrayList;

public interface RoomDAO extends CrudDAO<Room,String> {

    String generateRoomID();
    ArrayList<Room> getRoomDetails(String name);

}
