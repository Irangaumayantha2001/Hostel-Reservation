package dao.custom;


import dao.CrudDAO;
import entity.Reserve;
import entity.Student;

public interface ReservationDAO extends CrudDAO<Reserve,String> {
    String generateNewReservId();
    Reserve searchRegisterObject(Student student);

}
