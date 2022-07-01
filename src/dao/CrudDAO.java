package dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T,ID> extends SuperDAO {
    boolean add(T t) ;

    boolean delete(ID id) ;

    boolean update(T t) ;

    T search(ID id);

    ArrayList<T> getAll() ;

}
