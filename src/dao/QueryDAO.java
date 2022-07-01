package dao;

import dto.ReservationDetailDTO;

import java.util.ArrayList;

public interface QueryDAO extends SuperDAO {
    ArrayList<ReservationDetailDTO> getDetails();
}
