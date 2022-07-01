package dao.custom;

import dao.SuperDAO;

import dto.StudentDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface QueryDAO extends SuperDAO {
    ArrayList<StudentDTO> getOrderDetailsFromOrderID(String oid) throws SQLException, ClassNotFoundException;

}
