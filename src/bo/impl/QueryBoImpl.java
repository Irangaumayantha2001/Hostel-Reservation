package bo.impl;

import bo.custom.QueryBO;
import dao.DAOFactory;
import dao.QueryDAO;

import dto.ReservationDetailDTO;

import java.util.ArrayList;

public class QueryBoImpl implements QueryBO {

    QueryDAO queryDAO= (QueryDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.QUERYDAO);


    @Override
    public ArrayList<ReservationDetailDTO> getDetails() {
        ArrayList<ReservationDetailDTO> details = queryDAO.getDetails();
        return details;
    }
}
