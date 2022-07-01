package dao.custom;

import dao.CrudDAO;

import entity.Reserve;
import entity.Student;


import java.util.ArrayList;

public interface StudentDAO extends CrudDAO<Student,String> {
    String generateStudentID() ;
    boolean saveRegisterDetails(Reserve register, String id);
    int countStudent() ;
}
