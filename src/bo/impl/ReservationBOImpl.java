package bo.impl;

import bo.custom.ReservationBO;
import dao.DAOFactory;
import dao.custom.ReservationDAO;
import dao.custom.RoomDAO;
import dao.custom.StudentDAO;
import dto.ReservationDTO;
import dto.RoomDTO;
import dto.StudentDTO;
import entity.Reserve;
import entity.Room;
import entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;

public class ReservationBOImpl implements ReservationBO {


    ReservationDAO reservationDAO= (ReservationDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.RESERVATION);


    @Override
    public boolean saveReserv(ReservationDTO registerDTO) {

        Student student = new Student(registerDTO.getStudent().getStudent_id(),registerDTO.getStudent().getDob(),registerDTO.getStudent().getAddress(),registerDTO.getStudent().getGender(),registerDTO.getStudent().getAddress(),registerDTO.getStudent().getContact_no(),registerDTO.getStudent().getRegisterList());
        Room room = new Room(registerDTO.getRoom().getRoom_id(),registerDTO.getRoom().getType(),registerDTO.getRoom().getMonthly_rent(),registerDTO.getRoom().getQty());
        return reservationDAO.add(new Reserve(registerDTO.getRes_id(),registerDTO.getDate(),registerDTO.getStatus(),registerDTO.getPayment(),student,room));
    }

    @Override
    public ArrayList<ReservationDTO> getAllReservations() {
        ArrayList<Reserve> all = reservationDAO.getAll();
        ArrayList<ReservationDTO> allDetails = new ArrayList<>();
        for (Reserve register : all) {
            StudentDTO studentDTO = new StudentDTO(register.getStudent().getStudent_id(),register.getStudent().getName(),register.getStudent().getDob(),register.getStudent().getGender(),register.getStudent().getAddress(),register.getStudent().getContact_no());
            RoomDTO courseDTO = new RoomDTO(register.getRoom().getRoom_id(),register.getRoom().getType(),register.getRoom().getMonthly_rent(),register.getRoom().getQty());
            allDetails.add(new ReservationDTO(register.getRes_id(),register.getDate(),register.getStatus(),register.getPayment(),studentDTO,courseDTO));
        }
        return allDetails;
    }

    @Override
    public String generateNewReservId() {
        String ids = reservationDAO.generateNewReservId();
        return ids;
    }

    @Override
    public boolean updateReservation(ReservationDTO registerDTO) {
        Student student = new Student(registerDTO.getStudent().getStudent_id(),registerDTO.getStudent().getName()
                ,registerDTO.getStudent().getAddress(),registerDTO.getStudent().getGender(),registerDTO.getStudent().getAddress(),registerDTO.getStudent().getContact_no(),registerDTO.getStudent().getRegisterList());
        Room course = new Room(registerDTO.getRoom().getRoom_id(),registerDTO.getRoom().getType(),registerDTO.getRoom().getMonthly_rent(),registerDTO.getRoom().getQty());
        return reservationDAO.update(new Reserve(registerDTO.getRes_id(),registerDTO.getDate(),registerDTO.getStatus(),registerDTO.getPayment(),student,course));
    }

    @Override
    public ReservationDTO searchReserv(String id) {
        Reserve register = reservationDAO.search(id);
        StudentDTO studentDTO = new StudentDTO(register.getStudent().getStudent_id(),register.getStudent().getName()
                ,register.getStudent().getAddress(),register.getStudent().getGender(),register.getStudent().getAddress(),register.getStudent().getContact_no());
        RoomDTO courseDTO = new RoomDTO(register.getRoom().getRoom_id(),register.getRoom().getType(),register.getRoom().getMonthly_rent(),register.getRoom().getQty());
        return new ReservationDTO(register.getRes_id(),register.getDate(),register.getStatus(),studentDTO,courseDTO);
    }

    @Override
    public boolean deleteReserv(String id) {
        return reservationDAO.delete(id);
    }
}
