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
public class StudentTM  {

    private String student_id;
    private  String name;
    private  String address;
    private  String contact_no;
    private  String dob;
    private  String gender;
    private JFXButton btnupdate;
    private JFXButton btndelete;

}
