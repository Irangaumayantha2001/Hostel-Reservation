package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity

public class Room {

    @Id
    private String room_id;
    private  String type;
    private double monthly_rent;
    private  int qty;

    @OneToMany(mappedBy = "room",fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    private List<Reserve> reserveList=new ArrayList<>();

    public Room(){}

    public Room(String room_id, String type, double monthly_rent, int qty) {
        this.room_id = room_id;
        this.type = type;
        this.monthly_rent = monthly_rent;
        this.qty = qty;
    }

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getMonthly_rent() {
        return monthly_rent;
    }

    public void setMonthly_rent(double monthly_rent) {
        this.monthly_rent = monthly_rent;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public List<Reserve> getReserveList() {
        return reserveList;
    }

    public void setReserveList(List<Reserve> reserveList) {
        this.reserveList = reserveList;
    }


    @Override
    public String toString() {
        return "Room{" +
                "room_id='" + room_id + '\'' +
                ", type='" + type + '\'' +
                ", monthly_rent=" + monthly_rent +
                ", qty=" + qty +
                '}';
    }
}
