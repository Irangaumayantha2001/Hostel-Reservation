package view.TM;

import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class ReservationTM {
    private String reservationId;
    private String room_id;
    private  String type;
    private double monthly_rent;
    private int qty;
    private  String date;
    private JFXButton btnupdate;
    private JFXButton btndelete;

    public ReservationTM(String res_id, String room_id, String type, double monthly_rent, String status, JFXButton buttonUpdate, JFXButton buttonDelete) {




    }
}
