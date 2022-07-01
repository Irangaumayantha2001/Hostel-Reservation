package controller;

import bo.BOFactory;
import bo.custom.QueryBO;
import com.jfoenix.controls.JFXComboBox;
import dto.ReservationDetailDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.TM.ReservationDetailTM;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AllListFormController implements Initializable {

    public TableView<ReservationDetailTM> tblDetails;

    public JFXComboBox<String> cmbReserID;
    public AnchorPane AllDetailContext;

    private QueryBO queryBO = (QueryBO) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.QUERY);

    public void initialize(URL location, ResourceBundle resources) {
        tblDetails.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("res_id"));
        tblDetails.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("date"));
        tblDetails.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("room_id"));
        tblDetails.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("roomName"));
        tblDetails.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("student_id"));
        tblDetails.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("StudentName"));
        tblDetails.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("status"));
        tblDetails.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("price"));

        ArrayList<ReservationDetailDTO> details = queryBO.getDetails();
        ObservableList<ReservationDetailTM> obList = FXCollections.observableArrayList();
        for (ReservationDetailDTO detail : details) {
            obList.add(new ReservationDetailTM(detail.getRes_id(),detail.getDate(),detail.getRoom_id(),detail.getRoomName(),detail.getStudent_id(),detail.getStudentName(),detail.getStatus(),detail.getPrice()));
        }
        tblDetails.setItems(obList);
    }


    public void clearReservationByReserID (MouseEvent mouseEvent){
        cmbReserID.getSelectionModel().clearSelection();
        ObservableList<ReservationDetailTM> items = tblDetails.getItems();
        items.clear();
        tblDetails.refresh();
    }

    public void moveToHome(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../view/MainForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) AllDetailContext.getScene().getWindow();
        window.setScene(new Scene(load));

    }

    public void SearchReservationByReserID(MouseEvent mouseEvent) {

    }
}

