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
public class RoomTM  {

    private String room_id;
    private  String type;
    private double monthly_rent;
    private  int qty;
    private JFXButton btnupdate;
    private JFXButton btndelete;


}
