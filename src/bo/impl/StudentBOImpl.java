package bo.impl;

import bo.custom.StudentBO;
import dao.DAOFactory;
import dao.custom.StudentDAO;
import dto.ReservationDTO;
import dto.StudentDTO;
import entity.Reserve;
import entity.Room;
import entity.Student;

import java.util.ArrayList;

public class StudentBOImpl implements StudentBO {

     StudentDAO studentDAO = (StudentDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.STUDENT);


    @Override
    public ArrayList<StudentDTO> getAllStudents() {
        ArrayList<Student> all = studentDAO.getAll();
        ArrayList<StudentDTO> allDetails = new ArrayList<>();
        for (Student student : all) {
            allDetails.add(new StudentDTO(student.getStudent_id(),student.getName(),
                    student.getAddress(),student.getContact_no(),student.getDob(),student.getGender()));
        }
        return allDetails;
    }

    @Override
    public boolean saveStudent(StudentDTO studentDTO) {
        return studentDAO.add(new Student(studentDTO.getStudent_id(),studentDTO.getName(),
                studentDTO.getAddress(),studentDTO.getContact_no(),studentDTO.getDob(),studentDTO.getGender()));
    }

    @Override
    public boolean updateStudent(StudentDTO studentDTO) {
        return studentDAO.update(new Student(studentDTO.getStudent_id(),studentDTO.getName(),
                studentDTO.getAddress(),studentDTO.getContact_no(),studentDTO.getDob(),studentDTO.getGender()));
    }

    @Override
    public boolean deleteStudent(String id) {
        return studentDAO.delete(id);
    }

    @Override
    public StudentDTO searchStudent(String id) {
        Student student = studentDAO.search(id);
        return new StudentDTO(student.getStudent_id(),student.getName(),
                student.getAddress(),student.getContact_no(),student.getDob(),student.getGender());
    }


    @Override
    public boolean saveRegisterDetails(ReservationDTO register, String id) {
        Student student = new Student(register.getStudent().getStudent_id(),register.getStudent().getName(),register.getStudent().getAddress(),register.getStudent().getContact_no(),register.getStudent().getDob(),register.getStudent().getGender(),register.getStudent().getRegisterList());
        Room room = new Room(register.getRoom().getRoom_id(),register.getRoom().getType(),register.getRoom().getMonthly_rent(),register.getRoom().getQty());
        Reserve reserve = new Reserve(register.getRes_id(),register.getDate(),register.getStatus(),register.getPayment(),student,room);
        return  studentDAO.saveRegisterDetails(reserve,id);
    }

    @Override
    public String generateStudentID() {
        String ids = studentDAO.generateStudentID();
        return ids;
    }

    @Override
    public int countStudent() {
        int count = studentDAO.countStudent();
        return count;
    }
}
