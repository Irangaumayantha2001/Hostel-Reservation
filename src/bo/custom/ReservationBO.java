package bo.custom;

import dto.ReservationDTO;
import dto.RoomDTO;

import java.util.ArrayList;

public interface ReservationBO extends SuperBO {


    boolean saveReserv(ReservationDTO registerDTO);

    ArrayList<ReservationDTO> getAllReservations();

    String generateNewReservId();

    boolean updateReservation(ReservationDTO registerDTO);

    ReservationDTO searchReserv(String id);

    boolean deleteReserv(String id);

}
