package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


public class ReservationDTO {
    private String res_id;
    private String date;
    private String status;
    private String payment;
    private StudentDTO student;
    private RoomDTO room;

    public ReservationDTO(){}

    public ReservationDTO(String res_id, String date, String status, String payment) {
        this.res_id = res_id;
        this.date = date;
        this.status = status;
        this.payment = payment;
    }

    public ReservationDTO(String res_id, String date, String status, String payment, StudentDTO student, RoomDTO room) {
        this.res_id = res_id;
        this.date = date;
        this.status = status;
        this.payment = payment;
        this.setStudent(student);
        this.setRoom(room);
    }

    public ReservationDTO(String text, String valueOf, String payment, StudentDTO student, RoomDTO course) {


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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public StudentDTO getStudent() {
        return student;
    }

    public void setStudent(StudentDTO student) {
        this.student = student;
    }

    public RoomDTO getRoom() {
        return room;
    }

    public void setRoom(RoomDTO room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "ReservationDTO{" +
                "res_id='" + res_id + '\'' +
                ", date='" + date + '\'' +
                ", status='" + status + '\'' +
                ", payment='" + payment + '\'' +
                ", student=" + student +
                ", room=" + room +
                '}';
    }
}
