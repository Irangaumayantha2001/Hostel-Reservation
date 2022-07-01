package bo.custom;

import dto.ReservationDTO;
import dto.RoomDTO;


import java.util.ArrayList;

public interface RoomBO extends SuperBO {
    ArrayList<RoomDTO> getAllRooms() ;

    boolean deleteRoom(String code) ;

    boolean saveRoom(RoomDTO dto) ;

    boolean updateRoom(RoomDTO dto) ;

    String generateRoomID();

    ArrayList<RoomDTO> getRoomDetails(String name);

    RoomDTO searchRoom(String id);

    boolean saveRegisterDetails(ReservationDTO register, String id);

}
