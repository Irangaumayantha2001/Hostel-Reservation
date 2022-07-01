package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoomDTO {

    private String room_id;
    private  String type;
    private double monthly_rent;
    private  int qty;
}
