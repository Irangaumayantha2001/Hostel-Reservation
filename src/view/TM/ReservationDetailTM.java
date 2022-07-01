package view.TM;


import java.util.Date;


public class ReservationDetailTM {
    private String res_id;
    private String date;
    private String room_id;
    private  String roomName;
    private String student_id;
    private  String StudentName;
    private String status;
    private double price;

    public ReservationDetailTM(){}

    public ReservationDetailTM(String res_id, String date, String room_id, String roomName, String student_id, String studentName, String status, double price) {
        this.res_id = res_id;
        this.date = date;
        this.room_id = room_id;
        this.roomName = roomName;
        this.student_id = student_id;
        StudentName = studentName;
        this.status = status;
        this.price = price;
    }

    public String getRes_id() {
        return res_id;
    }

    public void setRes_id(String res_id) {
        this.res_id = res_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String studentName) {
        StudentName = studentName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ReservationDetailTM{" +
                "res_id='" + res_id + '\'' +
                ", date='" + date + '\'' +
                ", room_id='" + room_id + '\'' +
                ", roomName='" + roomName + '\'' +
                ", student_id='" + student_id + '\'' +
                ", StudentName='" + StudentName + '\'' +
                ", status='" + status + '\'' +
                ", price=" + price +
                '}';
    }
}


