package entity;

import com.sun.tracing.dtrace.FunctionAttributes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Student {

    @Id
    private String student_id;
    private  String name;
    private  String address;
    private  String contact_no;
    private  String dob;
    private  String gender;

    @OneToMany(mappedBy = "student",fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    private List<Reserve>reserveList=new ArrayList<>();

    public Student(String student_id, String name, String address, String contact_no, String dob, String gender) {
        this.student_id = student_id;
        this.name = name;
        this.address = address;
        this.contact_no = contact_no;
        this.dob = dob;
        this.gender = gender;
    }
}
