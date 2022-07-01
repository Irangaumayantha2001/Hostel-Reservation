package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Reserve {
    @Id
    private String res_id;
    private String date;
    private String status;
    private String payment;

    @ManyToOne
    private Student student;
    @ManyToOne
    private  Room room;

    @Override
    public String toString() {
        return "Reserve{" +
                "res_id='" + res_id + '\'' +
                ", date='" + date + '\'' +
                ", status='" + status + '\'' +
                ", student=" + student +
                ", room=" + room +
                '}';
    }
}
