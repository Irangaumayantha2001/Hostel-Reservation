package bo.custom;

import dto.ReservationDTO;
import dto.StudentDTO;

import java.util.ArrayList;

public interface StudentBO extends SuperBO {
    ArrayList<StudentDTO> getAllStudents() ;

    boolean saveStudent(StudentDTO studentDTO) ;

    boolean updateStudent(StudentDTO studentDTO) ;

    boolean deleteStudent(String id) ;

    StudentDTO searchStudent(String id);

    boolean saveRegisterDetails(ReservationDTO register, String id);

    String generateStudentID() ;

    int countStudent();
}
