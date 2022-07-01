package bo.custom;

import dto.ReservationDetailDTO;

import java.util.ArrayList;

public interface QueryBO extends SuperBO {
    ArrayList<ReservationDetailDTO> getDetails();
}
