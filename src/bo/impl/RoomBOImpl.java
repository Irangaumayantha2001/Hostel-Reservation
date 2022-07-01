package bo.impl;

import bo.custom.RoomBO;
import dao.DAOFactory;
import dao.custom.RoomDAO;
import dto.ReservationDTO;
import dto.RoomDTO;
import entity.Room;


import java.util.ArrayList;

public class RoomBOImpl implements RoomBO {

    private final RoomDAO roomDAO = (RoomDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ROOM);


    @Override
    public ArrayList<RoomDTO> getAllRooms() {
        ArrayList<Room> all = roomDAO.getAll();
        ArrayList<RoomDTO> allDetails = new ArrayList<>();
        for (Room course : all) {
            allDetails.add(new RoomDTO(course.getRoom_id(),course.getType(),course.getMonthly_rent(),course.getQty()));
        }
        return allDetails;
    }

    @Override
    public boolean deleteRoom(String code) {
        return roomDAO.delete(code);
    }

    @Override
    public boolean saveRoom(RoomDTO dto) {
        return roomDAO.add(new Room(dto.getRoom_id(),dto.getType(),dto.getMonthly_rent(), dto.getQty()));
    }

    @Override
    public boolean updateRoom(RoomDTO dto) {
        return roomDAO.update(new Room(dto.getRoom_id(),dto.getType(),dto.getMonthly_rent(), dto.getQty()));
    }

    @Override
    public String generateRoomID() {
        String ids = roomDAO.generateRoomID();
        return ids;
    }

    @Override
    public ArrayList<RoomDTO> getRoomDetails(String name) {
        ArrayList<Room> courseDetails = roomDAO.getRoomDetails(name);
        ArrayList<RoomDTO> allDetails = new ArrayList<>();
        for (Room course : courseDetails) {
            allDetails.add(new RoomDTO(course.getRoom_id(),course.getType(),course.getMonthly_rent(),course.getQty()));
        }
        return allDetails;
    }

    @Override
    public RoomDTO searchRoom(String id) {
        Room course = roomDAO.search(id);
        return new RoomDTO(course.getRoom_id(),course.getType(),course.getMonthly_rent(),course.getQty());
    }

    @Override
    public boolean saveRegisterDetails(ReservationDTO register, String id) {
        return false;
    }
}
